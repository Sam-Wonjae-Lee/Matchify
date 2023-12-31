package interface_adapter.accept_invite;

import use_case.accept_invite.AcceptInputBoundary;
import use_case.accept_invite.AcceptInputData;
import use_case.accept_invite.AcceptInteractor;

public class AcceptController {

    private final AcceptInputBoundary acceptInteractor;

    public AcceptController(AcceptInputBoundary acceptInteractor) {
        this.acceptInteractor = acceptInteractor;
    }

    public void execute( String user_id,  String friend_id) {
        AcceptInputData acceptInputData = new AcceptInputData(user_id, friend_id);
        acceptInteractor.execute(acceptInputData);
    }
}
