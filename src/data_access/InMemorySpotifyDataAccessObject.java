package data_access;

import entity.CommonUser;
import entity.FriendsList;
import entity.Genre;
import entity.Inbox;
import use_case.login.LoginSpotifyAPIDataAccessInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemorySpotifyDataAccessObject implements LoginSpotifyAPIDataAccessInterface {

    private final Map<String, String> userNames = new HashMap<>();
    private final Map<String, String> profilePictures = new HashMap<>();
    private final Map<String, Boolean> userExists = new HashMap<>();

    FriendsList friendslst = new FriendsList();
    Inbox inbox = new Inbox();
    Genre genre = new Genre();

    @Override
    public String getName(String userID) {
        return userNames.get(userID);
    }

    @Override
    public String getProfilePicture(String userID) {
        return profilePictures.get(userID);
    }

    @Override
    public boolean userExists(String user_id) {
        return userExists.getOrDefault(user_id, false);
    }

    @Override
    public List<String> getGenres(String artistId) {
        return null;
    }

    @Override
    public List<String> getPlaylistIds(String s) {
        return null;
    }

    @Override
    public List<String> getArtistsIds(String playlistId) {
        return null;
    }
}
