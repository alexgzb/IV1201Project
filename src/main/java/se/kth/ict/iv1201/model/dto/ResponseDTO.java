package se.kth.ict.iv1201.model.dto;

/**
 * ResponseDTO is a data transport object designed to hold data
 concerning of how a calls within the system has gone. If contains a boolean
 * regarding if the call was successful and two strings with information 
 * about the calla.
 * 
 * @author Wilhelm
 */
public class ResponseDTO {
    
    private boolean success;
    private String statusMessage;
    private String errorMessage;

    public ResponseDTO(boolean succeeded, String message) {
        this.success = succeeded;
        this.statusMessage = message;
    }
    
    public ResponseDTO(boolean succeeded, String message, String error) {
        this.success = succeeded;
        this.statusMessage = message;
        this.errorMessage = error;
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
