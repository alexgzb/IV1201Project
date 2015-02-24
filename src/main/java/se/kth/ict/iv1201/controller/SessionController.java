
package se.kth.ict.iv1201.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import se.kth.ict.iv1201.model.dao.AccountDAO;
import se.kth.ict.iv1201.util.log.Log;

/**
 * Session controller that retrieves key data about a user's permission and rights.
 * @author Christian Schreil
 */

@Stateless
@Log
public class SessionController {
    
    @EJB
    private AccountDAO accountDAO;
    
    /**
     * Retrieves a user's specific role
     * @param username Name of the user that is logging in
     * @return Name of the role that the user belongs to
     */
    public String getUserRole(String username){
        
        return accountDAO.getUserRole(username);
        
    }
    
}
