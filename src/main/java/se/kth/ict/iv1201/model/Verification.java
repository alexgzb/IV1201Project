package se.kth.ict.iv1201.model;

import se.kth.ict.iv1201.model.dto.AccountDTO;
import se.kth.ict.iv1201.model.dto.ResponseDTO;

/**
 * Verification is a class containing a number of method for verifying data
 * within the system and make sure that it is valid.
 *
 * @author Wilhelm
 */
public class Verification {

    /**
     *
     * @param data The account data that is to be tested and validated.
     * @return VerificationResponse with a boolean showing if the data is valid
     * and two strings with information about the tests.
     */
    public ResponseDTO verifyAccount(AccountDTO data) {

        if (data.getPassword().length() < 8) {
            return new ResponseDTO(false, "passwordVerFaild", "min8Char");
        }
        return new ResponseDTO(true, "All account data is valid.");
    }

}
