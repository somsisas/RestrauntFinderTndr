package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    private CardStackLayoutManager manager;
    private CardStackAdapter adapter;

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

    }


    private List<MyModel> addItems() {
        List<MyModel> items = new ArrayList<>();

        items.add(new MyModel("Alice", 25, "https://logos-world.net/wp-content/uploads/2020/06/Liverpool-Logo.png"));
        items.add(new MyModel("Bob", 30, "https://logos-world.net/wp-content/uploads/2020/06/Real-Madrid-Logo-700x394.png"));
        items.add(new MyModel("Charlie", 28, "https://en.wikipedia.org/wiki/Real_Madrid_CF"));
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
            System.out.println(result);
        }

    }


}

