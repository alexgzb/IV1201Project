package se.kth.ict.iv1201.view;

import javax.ejb.Stateful;
import javax.inject.Named;

/**
 * AccountView is a stateful session bean connected to a single user.
 * It handles all calls from the users web interface regarding the users
 * account details and forwards the requests to an AccountController.
 * 
 * @author Wilhelm
 */
@Named("accountView")
@Stateful
public class AccountView {

    
}
