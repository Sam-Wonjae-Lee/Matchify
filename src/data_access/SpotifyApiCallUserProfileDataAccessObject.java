package data_access;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URI;
import java.net.HttpURLConnection;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import org.apache.hc.core5.http.ParseException;
import org.json.JSONObject;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.exceptions.detailed.InternalServerErrorException;
import se.michaelthelin.spotify.model_objects.specification.User;
import se.michaelthelin.spotify.requests.data.users_profile.GetUsersProfileRequest;


/*
 * SpotifyApiCallArtistGenresDataAccessObject contains the function to retrieve the "get user's profile" response.
 * */

public class SpotifyApiCallUserProfileDataAccessObject implements SpotifyApiCallInterface{

    // ClientId, ClientSecret, RedirectURI - necessary info for using API.
    private final String CLIENT_ID = SpotifyApiCallInterface.CLIENT_ID;
    private final String CLIENT_SECRET = SpotifyApiCallInterface.CLIENT_SECRET;
    private final String REDIRECT_URI = SpotifyApiCallInterface.REDIRECT_URI;


    /**
    * Check if a Spotify user ID exists.
    *
    * @param accessToken A string containing the temporary access token.
    * @param userId     A string containing the Spotify artist ID.
    * @return true if the user ID exists, false or error otherwise.
    */
    public boolean checkUserExists(String accessToken, String userId){
        try {
            // Initialize the Spotify API object
            SpotifyApi spotifyApi = new SpotifyApi.Builder()
                    .setClientId(CLIENT_ID)
                    .setClientSecret(CLIENT_SECRET)
                    .setRedirectUri(URI.create(REDIRECT_URI))
                    .setAccessToken(accessToken)
                    .build();

            // Use a request
            GetUsersProfileRequest request = spotifyApi
                    .getUsersProfile(userId)
                    .build();

            User userProfile = request.execute();
            return userProfile != null;
        } catch (InternalServerErrorException e) {
//            e.printStackTrace();
            return false;
        } catch (IOException | ParseException | SpotifyWebApiException e) {
//            e.printStackTrace();
            return false;
        }

    }


    /**
    * Get the user's profile. In other words, get detailed profile information about the user.
    * More info is located here: https://developer.spotify.com/documentation/web-api/reference/get-current-users-profile
    *
    * @param accessToken A string containing the temporary access token.
    * @param userId A string containing the Spotify user ID.
    * @return A JSONObject containing the response data for the Spotify user.
    *
    * Example Response:
    * {"images":[{"width":64,"url":"https://i.scdn.co/image/ab67757000003b821e8b5038f93e5ec850728b8d","height":64},
    * {"width":300,"url":"https://i.scdn.co/image/ab6775700000ee851e8b5038f93e5ec850728b8d","height":300}],"followers":{"total":1,"href":null},
    * "href":"https://api.spotify.com/v1/users/o3bv345iz36uo33gj1ncpa8yo","id":"o3bv345iz36uo33gj1ncpa8yo","display_name":"David","type":"user",
    * "external_urls":{"spotify":"https://open.spotify.com/user/o3bv345iz36uo33gj1ncpa8yo"},"uri":"spotify:user:o3bv345iz36uo33gj1ncpa8yo"}
    */
    public JSONObject getUserProfile(String accessToken, String userId) throws IOException {
        // Spotify API endpoint URL for user profile information
        String apiUrl = "https://api.spotify.com/v1/users/" + userId;

        // Create a URL object with the Spotify API endpoint
        URL url = new URL(apiUrl);

        // Open a connection to the Spotify API
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the request method to GET
        connection.setRequestMethod("GET");

        // Set the Authorization header with the user's access token
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);

        // Get the HTTP response code
        int responseCode = connection.getResponseCode();

        // Initialize a JSONObject to store response
        JSONObject responseData = null;

        // Check if the request was successful (HTTP status code 200)
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Read and print the response from the Spotify API
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }

            // Close the reader
            bufferedReader.close();

            connection.disconnect();

            // Define response
            responseData = new JSONObject(response.toString());

        } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            connection.disconnect();

            // Print error if response code not found
            System.out.println("Error 404");
        } else {
            connection.disconnect();

            // Throws exception
            throw new IOException("Response Code: " + responseCode);
        }

        // Prints response
//        System.out.println(responseData);
        // Returns response
        return responseData;
    }


}
