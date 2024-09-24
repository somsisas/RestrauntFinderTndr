# Trestraunt App

**Trestraunt** is an Android application designed to help users discover nearby restaurants with a swipe-based interface inspired by Tinder. Users can view restaurant details, explore up to 10 high-quality images, and swipe to either dismiss or save restaurants for future reference. The app leverages Google Places API for nearby restaurant searches and integrates Google Maps Intents for seamless navigation.

## Features

- **Restaurant Discovery**: Search for nearby restaurants based on the user's location.
- **Swipe-Based Interface**: Dismiss or like restaurants by swiping left or right, similar to Tinder’s UI.
- **High-Quality Images**: Display up to 10 photos of each restaurant using Glide.
- **Like List**: Save restaurants swiped right and view them later on a separate "like list" page.
- **Seamless Navigation**: Navigate to restaurants via Google Maps with just a tap.
- **User Authentication**: Sign-up and login functionality using Firebase.
- **Persistent Data**: User likes are stored in Firebase, allowing them to view saved restaurants even after logging out and back in.

## Technologies Used

- **Java**: Primary language for Android development.
- **Android Studio**: Development environment.
- **Google Places API**: To retrieve nearby restaurant data, including details and images.
- **Google Maps API**: For map integration and navigation.
- **Firebase**: For user authentication and real-time database storage of liked restaurants.
- **Glide**: For loading and displaying restaurant images.

## Project Structure

- **MainActivity**: Entry point for the app, hosting the restaurant swiping feature.
- **SignUpActivity*: Handles user sign-up.
- **LogInActivity**: Handles user login.
- **Firebase**: Integrated for:
  - **Authentication**: User registration and login.
  - **Database**: Storing the user's "liked" restaurants.

## Setup and Installation

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/your-username/trestraunt-app.git
    ```
   
2. **Open in Android Studio**:
    - Open the project in Android Studio.
   
3. **Configure Firebase**:
    - Go to [Firebase Console](https://console.firebase.google.com/) and create a new project.
    - Add your Android app to Firebase.
    - Download the `google-services.json` file and place it in the `app` directory.
    - Enable Firebase Authentication and Realtime Database in the Firebase Console.
   
4. **Configure Google Places API**:
    - Go to [Google Cloud Console](https://console.cloud.google.com/).
    - Create a new project and enable the **Places API** and **Maps API**.
    - Generate an API key and add it to your project under `res/values/strings.xml` as:
    ```xml
    <string name="google_places_api_key">YOUR_API_KEY</string>
    ```

5. **Build and Run**:
    - Sync your project and run it on an Android emulator or physical device.

## Future Features

- **Restaurant Reviews**: Display user reviews for each restaurant.
- **Filter Options**: Add filters like cuisine type, price range, and distance.
- **Notification Reminders**: Notify users of new restaurants in their liked area.
- **User Profile Management**: Allow users to manage their profile and preferences.

## Screenshots

| Main Screen | Like List Screen | Restaurant Details on Maps |
|-------------|------------------|--------------------|
| ![Main Screen](Screenshots/main.jpg) | ![Like List](Screenshots/liked.jpg) | ![Details](Screenshots/details.jpg) |

