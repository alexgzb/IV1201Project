package se.kth.ict.iv1201.view;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import se.kth.ict.iv1201.controller.AccountController;
import se.kth.ict.iv1201.model.dto.AccountDTO;
import se.kth.ict.iv1201.model.dto.Response;

/**
 * AccountView is a stateful session bean connected to a single user. It handles
 * all calls from the users web interface regarding the users account details
 * and forwards the requests to an AccountController.
 *
 * @author Wilhelm
 */
@Named("accountView")
@RequestScoped
public class AccountView {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String Email;
    private String ssn;
    private String requestResponse;
    private String errorMessage;
    @Inject
    private AccountController controller;

    /*
     * Only here for testing purpuses and not be deployed to the final product.  
     *
     * @author Wilhelm
     */
    @PostConstruct
    public void test() {
        System.out.println("Post contruct test!");
    }
    
    /*
     * The method newAccount takes the data from the users form and contructes 
     * a DTO containing that information. This information is then passed to the
     * controller for further actions to be taken.
     *
     * @author Wilhelm
     */
    public void newAccount() {
        Response newAccount = controller.newAccount(new AccountDTO(username, password, firstname,
                lastname, Email, ssn));
        requestResponse = newAccount.getStatusMessage();
        if (!newAccount.isSuccess())
        {
            errorMessage = newAccount.getErrorMessage();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getRequestResponse() {
        return requestResponse;
    }

    public void setRequestResponse(String requestResponse) {
        this.requestResponse = requestResponse;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
}
