package se.kth.ict.iv1201.model;

import se.kth.ict.iv1201.model.dto.AccountDTO;
import se.kth.ict.iv1201.model.dto.ApplicationDTO;
import se.kth.ict.iv1201.model.dto.ResponseDTO;

/**
 * Verification is a class containing a number of method for verifying data
 * within the system and make sure that it is valid.
 *
 * @author Wilhelm
 */
public class Verification {

    /**
     * The method takes the users input and verifies that all the entered data
     * won't cause any problems when saved in the system.
     *
     * @param data The account data that is to be tested and validated.
     * @return VerificationResponse with a boolean showing if the data is valid
     * and two strings with information about the tests.
     */
    public ResponseDTO verifyAccount(AccountDTO data) {

        if (data.getPassword().length() < 8) {
            return new ResponseDTO(false, "passwordVerFaild", "min8Char");
        }
        return new ResponseDTO(true, "accountDataValid.");
    }

    /**
     * The method takes the users input and verifies that all the entered data
     * won't cause any problems when saved in the system.
     *
     * @param data User application input.
     * @return
     */
    public ResponseDTO verifyApplication(ApplicationDTO data) {

        if (data.getFromDate().length != data.getToDate().length || data.getFromDate().length < 1) {
            return new ResponseDTO(false, "applicationDateFail", "missingDate");
        }
        if(data.getCompetence().length != data.getExperiance().length){
            return new ResponseDTO(false, "applicationDateFail", "missingExperiance");
        }
        for (int i = 0; i < data.getFromDate().length; i++) {
            if (data.getFromDate()[i].compareTo(data.getToDate()[i]) > 0) {
                return new ResponseDTO(false, "applicationDateFail", "dateFromToMiss");
            }
            for (int j = i + 1; j < data.getFromDate().length; j++) {
                if (data.getToDate()[i].compareTo(data.getFromDate()[j]) < 0 || data.getFromDate()[i].compareTo(data.getToDate()[j]) >= 0) {
                    continue;
                }
                return new ResponseDTO(false, "applicationDateFail", "dateOverlap");
            }
        }
        return new ResponseDTO(true, "applicationDataValid");
    }

}
