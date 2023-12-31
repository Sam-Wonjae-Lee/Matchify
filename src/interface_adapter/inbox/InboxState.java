package interface_adapter.inbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InboxState {

    public List<String> inbox = new ArrayList<>();
    private String username = "";
    private String user_id = "";
    private HashMap<String, String> idToName = new HashMap<>();


    public InboxState(InboxState copy) {
        username = copy.username;
        inbox = copy.inbox;
        user_id = copy.user_id;
        idToName = copy.idToName;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public InboxState() {}

    public String getUsername() {
        return username;
    }

    public String getUser_id() { return user_id; }

    public void setUser_id(String id){
        this.user_id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getInbox(){
        return inbox;
    }

    public void setInbox(List<String> lst){
        this.inbox = lst;
    }

    public HashMap<String, String> getIdToName(){
        return idToName;
    }

    public void setIdToName(HashMap<String, String> idMap){
        this.idToName = idMap;
    }
}
