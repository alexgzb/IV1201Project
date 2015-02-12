package se.kth.ict.iv1201.model.dto;

/**
 * AccountDTO is a data transport object designed to hold all data necessary for
 * account related requests from the client as well as proper response messages
 * for them.
 *
 * @author Wilhelm
 */
public class AccountDTO {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String Email;
    private String ssn;

    public AccountDTO(String username, String password, String firstname,
            String lastname, String Email, String ssn) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.Email = Email;
        this.ssn = ssn;
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

}
