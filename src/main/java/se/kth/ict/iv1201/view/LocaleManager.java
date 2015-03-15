package se.kth.ict.iv1201.view;

import java.io.Serializable;
import java.util.Locale;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import se.kth.ict.iv1201.util.log.Log;

/**
 * Sets the correct locale for the session.
 */
@Log
@Named(value="locale")
@SessionScoped
public class LocaleManager implements Serializable {
    
    private Locale locale;
    
    public void changeLocale() {
        locale = new Locale(getLanguageCode());
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

    private String getLanguageCode() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("languageCode");
    }
    
    public Locale getLocale(){
        return locale;
    }
    
}
