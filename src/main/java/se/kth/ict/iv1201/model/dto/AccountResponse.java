package se.kth.ict.iv1201.model.dto;

/**
 * AccountResponse is a form of data transport object that contains all the
 * necessary information about how a request to the AccountController went. If
 * it succeeded, and if not what went wrong.
 *
 * @author iv1201
 */
public class AccountResponse {

    private boolean success;
    private String statusMessage;
    private String errorMessage;

    public AccountResponse(boolean succeeded, String message) {
        this.success = succeeded;
        this.statusMessage = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSucceeded(boolean success) {
        this.success = success;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String message) {
        this.statusMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
