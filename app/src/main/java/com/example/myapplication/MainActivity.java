package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;

import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private CardStackLayoutManager manager;
    private CardStackAdapter adapter;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;

    private String userLatitude;
    private String userLongitude;

    private List<MyModel> restraunts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize CardStackView
        CardStackView cardStackView = findViewById(R.id.card_stack_view);
        manager = new CardStackLayoutManager(this, new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {
                System.out.println("here" + manager.getTopPosition());
                if (manager.getTopPosition() == adapter.getItemCount() - 1) {
                    System.out.println("here mate");
                    showEndOfProfilesMessage();
                }
            }

            @Override
            public void onCardSwiped(Direction direction) {
                // Handle swipe
                if (direction == Direction.Right) {
                    System.out.println("asda");
                } else if (direction == Direction.Left) {
                    // Handle left swipe
                }
                // Add logic for when the stack is empty, like adding more items or resetting the stack
            }

            @Override
            public void onCardRewound() {
                // Handle rewind
            }

            @Override
            public void onCardCanceled() {
                // Handle canceled
            }

            @Override
            public void onCardAppeared(View view, int position) {
                // Handle card appeared
            }

            @Override
            public void onCardDisappeared(View view, int position) {
                // Handle card disappeared
            }
        });

        // Set the stack orientation, top view, etc.
        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.HORIZONTAL);
        manager.setCanScrollHorizontal(true);
        manager.setCanScrollVertical(true);
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual);
        manager.setOverlayInterpolator(new LinearInterpolator());

        cardStackView.setLayoutManager(manager);
        cardStackView.setItemAnimator(new DefaultItemAnimator());

        // Prepare and set the adapter

        adapter = new CardStackAdapter(addItems());
        cardStackView.setAdapter(adapter);
        Button bt1 = findViewById(R.id.swipeButton);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (manager.getTopPosition() == adapter.getItemCount() - 1) {
                    System.out.println("here mate");
                    showEndOfProfilesMessage();
                }
                cardStackView.swipe();
            }
        });

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Request the missing permissions
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Permissions are already granted, get the location
            getLastKnownLocation();

        }

//        System.out.println("Asda " + userLatitude + userLongitude);
        //new getRestrauntInfo().execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?keyword=restraunt&location=32.88156181108086%2C-117.23314332318097&radius=1500&type=restaurant&key=AIzaSyCZmjKrhNqC47ZeCEonIsOUiceAi9wDT7Y");

    }

    private void getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            // Got last known location, display the coordinates
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            Log.d("Location", "Lat: " + latitude + ", Long: " + longitude);
//                            System.out.println("Here mate " +  latitude + longitude);
                            userLatitude = String.valueOf(latitude);
                            userLongitude = String.valueOf(longitude);
                            useLocationData();
                        } else {
                            // Location is null, request a new location update
//                            requestNewLocationData();
                        }
                    }
                });
//        return curLat[0] + "@" + curLong[0];
    }

//    private void requestNewLocationData() {
//        LocationRequest locationRequest = LocationRequest.create();
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        locationRequest.setInterval(10000); // 10 seconds
//        locationRequest.setFastestInterval(5000); // 5 seconds
//
//        locationCallback = new LocationCallback() {
//            @Override
//            public void onLocationResult(LocationResult locationResult) {
//                if (locationResult == null) {
//                    return;
//                }
//                for (Location location : locationResult.getLocations()) {
//                    // Update your UI with the new location data
//                    double latitude = location.getLatitude();
//                    double longitude = location.getLongitude();
//                    userLatitude = String.valueOf(latitude);
//                    userLongitude = String.valueOf(longitude);
//
//                    System.out.println("Location " + "Latamata: " + latitude + ", Long: " + longitude);
//
//                    useLocationData();
//                }
//            }
//        };
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
//    }

    private void useLocationData() {
        // This method is called once the location data is available
        System.out.println("in here");
        System.out.println("Asda "+"Using Lat: " + userLatitude + ", Long: " + userLongitude);


        // You can now safely use userLatitude and userLongitude here
        // For example, initiate your network request here:

        new getRestrauntInfo().execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?keyword=restraunt&location=" + userLatitude + "%2C" + userLongitude + "&radius=30500&type=restaurant&opennow=true&key=AIzaSyCZmjKrhNqC47ZeCEonIsOUiceAi9wDT7Y");

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, get the location
                getLastKnownLocation();
            } else {
                // Permission denied, show a message to the user
                Log.d("Location", "Permission denied");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Clean up location updates
        if (locationCallback != null) {
            fusedLocationClient.removeLocationUpdates(locationCallback);
        }
    }


    private List<MyModel> addItems() {

        ArrayList<String> l1 = new ArrayList<>();
        ArrayList<String> l2 = new ArrayList<>();
        ArrayList<String> l3 = new ArrayList<>();

        l1.add("https://logos-world.net/wp-content/uploads/2020/06/Liverpool-Logo.png");
        l1.add("https://logos-world.net/wp-content/uploads/2020/06/Real-Madrid-Logo-700x394.png");
        l2.add("https://logos-world.net/wp-content/uploads/2020/06/Liverpool-Logo.png");
        l2.add("https://logos-world.net/wp-content/uploads/2020/06/Real-Madrid-Logo-700x394.png");
        l3.add("https://logos-world.net/wp-content/uploads/2020/06/Real-Madrid-Logo-700x394.png");
        l3.add("https://logos-world.net/wp-content/uploads/2020/06/Liverpool-Logo.png");


        List<MyModel> items = new ArrayList<>();

        items.add(new MyModel("Alice", 25, l1));
        items.add(new MyModel("Bob", 30, l2));
        items.add(new MyModel("Charlie", 28, l3));
        // Add more items here
        return items;
    }

    private void showEndOfProfilesMessage() {
        // Show a Toast message

        // OR Show an AlertDialog
        new AlertDialog.Builder(this)
                .setTitle("No More Profiles")
                .setMessage("You've viewed all the present profiles, and I don't have any more to show you.")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private static class getRestrauntInfo extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                // Create a URL object from the passed URL string
                URL url = new URL(urls[0]);

                // Open a connection to the URL
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                // Set the request method (GET, POST, etc.)
                urlConnection.setRequestMethod("GET");

                // Get the response code
                int responseCode = urlConnection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
                    // Read the response from the input stream
                    BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String inputLine;
                    StringBuilder content = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }

                    // Close the input stream
                    in.close();

                    // Return the response content as a String
                    return content.toString();
                } else {
                    // Handle non-200 HTTP responses
                    return "Error: " + responseCode;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // Handle the result of the network request here (e.g., update UI)
            super.onPostExecute(result);
            // For example, you can log the result
            try {
                JSONObject jsonObject = new JSONObject(result);
                System.out.println(jsonObject);
                if(jsonObject.has("next_page_token")){
                    System.out.println("you have a next page fam");
                }

                JSONArray resultsArray = jsonObject.getJSONArray("results");


                for (int i = 0; i < resultsArray.length(); i++) {
                    JSONObject resultObject = resultsArray.getJSONObject(i);

                    // Get the name and place_id from the resultObject
                    String name = resultObject.getString("name");
                    String placeId = resultObject.getString("place_id");

                    // Print or use the extracted data
                    System.out.println("Place Name: " + name);
                    System.out.println("Place ID: " + placeId);
                }

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

    }


}

