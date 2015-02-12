package se.kth.ict.iv1201.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import se.kth.ict.iv1201.model.Verification;
import se.kth.ict.iv1201.model.dao.AccountDAO;
import se.kth.ict.iv1201.model.dto.AccountDTO;
import se.kth.ict.iv1201.model.dto.Response;

/**
 * AccountController is an enterprise bean designed to take calls from the
 * AccountView managed bean and handle all the sub calls required to handle the
 * request.
 *
 * @author Wilhelm
 */
@Stateless
public class AccountController {

    private Verification verification;
    @EJB
    private AccountDAO accountDAO;

    @PostConstruct
    public void startUp() {
        verification = new Verification();
    }

    /**
     * newAccount makes all required calls to the model for verifying and saving
     * the the data entered by the user for their new account.
     *
     * @param data The data the user entered for the new account.
     * @return AccountResponse Containing a string with information about the
     * request that has been done and a boolean that is true if the request went
     * well.
     */
    public Response newAccount(AccountDTO data) {
        Response verify = verification.verifyAccount(data);
        if (!verify.isSuccess()) {
            return verify;
        }
        String exists = accountDAO.VerifyUniqueAccount(data);
        if (exists != null){
            return new Response(false, "Creating account faild.", "The selected " + exists + " has been taken.");
        }
        accountDAO.NewAccount(data);
        return new Response(true,"Account \"" + data.getUsername() + "\" has been created.");
    }
}
