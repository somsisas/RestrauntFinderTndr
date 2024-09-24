Trestraunt App

Trestraunt is an Android application designed to help users discover nearby restaurants with a swipe-based interface inspired by Tinder. Users can view restaurant details, explore up to 10 high-quality images, and swipe to either dismiss or save restaurants for future reference. The app leverages Google Places API for nearby restaurant searches and integrates Google Maps Intents for seamless navigation.

Features

Restaurant Discovery: Search for nearby restaurants based on the user's location.
Swipe-Based Interface: Dismiss or like restaurants by swiping left or right, similar to Tinder’s UI.
High-Quality Images: Display up to 10 photos of each restaurant using Glide.
Restaurant Details: View details like name, address, and rating.
Like List: Save restaurants swiped right and view them later on a separate "like list" page.
Seamless Navigation: Navigate to restaurants via Google Maps with just a tap.
User Authentication: Sign-up and login functionality using Firebase.
Persistent Data: User likes are stored in Firebase, allowing them to view saved restaurants even after logging out and back in.
Technologies Used

Java: Primary language for Android development.
Android Studio: Development environment.
Google Places API: To retrieve nearby restaurant data, including details and images.
Google Maps API: For map integration and navigation.
Firebase: For user authentication and real-time database storage of liked restaurants.
Glide: For loading and displaying restaurant images.
Project Structure

MainActivity: Entry point for the app, hosting the restaurant swiping feature.
Fragments: Used for sign-up and login screens, offering a smooth and modern UI.
LoginFragment: Handles user login.
SignUpFragment: Handles user sign-up.
Firebase: Integrated for:
Authentication: User registration and login.
Database: Storing the user's "liked" restaurants.
