package se.kth.ict.iv1201.controller;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import se.kth.ict.iv1201.model.Verification;
import se.kth.ict.iv1201.model.dao.AccountDAO;
import se.kth.ict.iv1201.model.dto.AccountDTO;
import se.kth.ict.iv1201.model.dto.ApplicationDTO;
import se.kth.ict.iv1201.model.dto.CompetenceDTO;
import se.kth.ict.iv1201.model.dto.ResponseDTO;
import se.kth.ict.iv1201.util.log.Log;

/**
 * AccountController is an enterprise bean designed to take calls from the
 * AccountView managed bean and handle all the sub calls required to handle the
 * request.
 *
 * @author Wilhelm
 */
@Stateless
@Log
@TransactionManagement(TransactionManagementType.CONTAINER)
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
     * @return ResponseDTO containing a string with information about the
     * request that has been done and a boolean that is true if the request went
     * well.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ResponseDTO newAccount(AccountDTO data) {
        ResponseDTO verify = verification.verifyAccount(data);
        if (!verify.isSuccess()) {
            return verify;
        }
        String exists = accountDAO.VerifyUniqueAccount(data);
        if (exists != null) {
            return new ResponseDTO(false, "newAccountFailed", exists + "Used");
        }
        accountDAO.NewAccount(data);
        return new ResponseDTO(true, "accountCreated");
    }

    /**
     * Sends a request to the accontDAO to get all the competences for the
     * selected language, and returns the answer.
     *
     * @param langCode The selected language for the view.
     * @return
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ArrayList<CompetenceDTO> getCompetences(String langCode) {
        return accountDAO.getCompetences(langCode);
    }

    /**
     * Makes all required calls to the model for verifying and saving the the
     * data entered by the user for their new application.
     *
     * @param data Application data to be handled
     * @return ResponseDTO Containing a string with information about the
     * request that has been done and a boolean that is true if the request went
     * well.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ResponseDTO addApplication(ApplicationDTO data) {
        ResponseDTO response = verification.verifyApplication(data);
        if (!response.isSuccess()) {
            return response;
        }
        if(accountDAO.addApplication(data)){
            return new ResponseDTO(true, "applicationCreated");
        }
        return new ResponseDTO(false, "applicationFailed", "applicationExists");
    }
}
