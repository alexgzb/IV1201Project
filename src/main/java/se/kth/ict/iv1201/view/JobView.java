
package se.kth.ict.iv1201.view;

import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import se.kth.ict.iv1201.controller.JobController;
import se.kth.ict.iv1201.model.dto.JobDTO;

/**
 * A managed bean that provides an extension in the presentation layer for recruiters wanting to query job related data.
 * Such transactions can be related to retrieving a list of available jobs, adding new jobs and registering applicants as
 * employees.
 * @author Christian Schreil
 */

@Named("jobView")
@SessionScoped
public class JobView implements Serializable {
    
    private ArrayList<JobDTO> availableJobs;
    @Inject
    private JobController controller;
    
    /**
     * Retrieves all available jobs in the database, by joining key values with translations in two different tables.
     * @param langaugeCode Specifies what language the jobs should be retrieved in
     */
    public void loadAvailableJobs(String langaugeCode){
        availableJobs = controller.getAvailableJobs(langaugeCode);
    }
    
    /**
     * Retrieve loaded list of jobs to the client layer.
     * @return An <code>ArrayList</code> of <code>JobDTO</code> objects, where each instance of the DTO represents a job
     */
    public ArrayList<JobDTO> getAvailableJobs(){
        return availableJobs;
    }
    
}
