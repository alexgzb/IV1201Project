package se.kth.ict.iv1201.controller;

import javax.ejb.Stateless;
import se.kth.ict.iv1201.model.dto.AccountDTO;
import se.kth.ict.iv1201.model.dto.AccountResponse;

/**
 *
 * @author iv1201
 */
@Stateless
public class AccountController {

    public AccountResponse newAccount(AccountDTO data){
        return new AccountResponse(true, "Test");   
    }
}
