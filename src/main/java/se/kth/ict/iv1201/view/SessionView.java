package se.kth.ict.iv1201.view;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import se.kth.ict.iv1201.controller.SessionController;
import se.kth.ict.iv1201.util.log.Log;

/**
 * Session bean that handles login and logout protocols. Directs users to their
 * respective resources depending on what security group they belong to.
 *
 * @author Christian Schreil
 */
@Named("sessionView")
@SessionScoped
@Log
public class SessionView implements Serializable {

    private String username;
    private String password;
    private String errorMessage;
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Provides the basic protocol for handling user authorization and
     * authentication
     *
     * @return URL to the resource that belongs to the user's specific user
     * group
     */
    public String login() {
        String resourceURL = "loginerror";
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            request.login(this.username, this.password);
            resourceURL = controller.getUserRole(this.username);
        } catch (ServletException ex) {
            //Fall through
        } catch (Exception e) {
            setErrorMessage("unknownError");
        }

        return resourceURL;
    }

    /**
     * Ends the session with the user by releasing the context.
     *
     * @return URL to index
     */
    public String logout() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            request.logout();
        } catch (ServletException e) {
        } catch (Exception ex) {
            setErrorMessage("unknownEror");
        }

        return "/index.xhtml";
    }

}
