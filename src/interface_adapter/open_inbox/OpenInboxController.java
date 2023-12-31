package interface_adapter.open_inbox;

import use_case.open_inbox.OpenInboxInputData;
import use_case.open_inbox.OpenInboxInputBoundary;

public class OpenInboxController {

    final OpenInboxInputBoundary openInboxUseCaseInteractor;

    public OpenInboxController(OpenInboxInputBoundary openInboxUseCaseInteractor) {
        this.openInboxUseCaseInteractor = openInboxUseCaseInteractor;
    }

    public void execute(String user_id, String username){
        OpenInboxInputData openInboxInputData= new OpenInboxInputData(user_id, username);

        openInboxUseCaseInteractor.execute(openInboxInputData);
    }
}
