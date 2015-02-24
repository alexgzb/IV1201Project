
package se.kth.ict.iv1201.view;


import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import se.kth.ict.iv1201.controller.SessionController;

/**
 * Session bean that handles login and logout protocols. Directs users to their respective resources
 * depending on what security group they belong to.
 * @author Christian Schreil
 */
@Named("sessionView")
@RequestScoped
public class SessionView {

    private String username;
    private String password;
    @Inject
    private SessionController controller;
    
    /**
     * 
     * @return
     */
    public String getUsername() {
        return this.username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return this.password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Provides the basic protocol for handling user authorization and authentication
     * @return URL to the resource that belongs to the user's specific user group
     */
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(this.username, this.password);
        } catch (ServletException ex) {
            //Fall through
        }

        return getUserResourceURL(controller.getUserRole(this.username));
    }

    /**
     * Ends the session with the user by releasing the context.
     * @return URL to index
     */
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException e) {
        }
        
        return "/index.xhtml";
    }
    
    private String getUserResourceURL(String pointer){
        
        String location;
        
        if(pointer.equals("Applicant")){
            location = "Applicant/applicantstart";
        } else {
            location = "Recruiter/recruiterstart";
        }
        
        return location;
    }
}
