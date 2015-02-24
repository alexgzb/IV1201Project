package se.kth.ict.iv1201.view;

import java.util.Locale;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import se.kth.ict.iv1201.util.log.Log;

/**
 * Sets the correct locale for the entire application.
 */
@Log
@Named(value="locale")
@ApplicationScoped
public class LocaleManager {
    public void changeLocale() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(getLanguageCode()));
    }

    private String getLanguageCode() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("languageCode");
    }
}
