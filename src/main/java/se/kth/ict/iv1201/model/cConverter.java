package se.kth.ict.iv1201.model;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import se.kth.ict.iv1201.model.dto.CompetenceDTO;
import se.kth.ict.iv1201.util.log.Log;


/**
 * Class used buy primefaces for converting objects to string an back for
 * competences.
 * 
 * @author Wilhelm
 */
@Log
@FacesConverter(value = "cConverter")
public class cConverter implements Converter {

    /**
     * Converts a string to a CompetenceDTO object.
     * @param fc
     * @param uic
     * @param string String representation of a CompetenceDTO.
     * @return 
     */
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return new CompetenceDTO(string, 0);
    }

    /**
     * Converts a CompetenceDTO to a string representation.
     * @param fc
     * @param uic
     * @param o a CompetenceDTO.
     * @return 
     */
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        CompetenceDTO temp = (CompetenceDTO) o;
        return temp.toString();
    }

}
