package se.kth.ict.iv1201.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import se.kth.ict.iv1201.model.dao.AccountDAO;
import se.kth.ict.iv1201.model.dto.AccountDTO;
import se.kth.ict.iv1201.model.dto.AccountResponse;

/**
 *
 * @author iv1201
 */
@Stateless
public class AccountController {

    @EJB
    private AccountDAO accountDAO;

    public AccountResponse newAccount(AccountDTO data) {
        
        System.out.println("Controller!");
        return new AccountResponse(true, accountDAO.test(data));
    }
}
