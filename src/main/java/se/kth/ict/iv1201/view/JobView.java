
package se.kth.ict.iv1201.view;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import se.kth.ict.iv1201.controller.JobController;

/**
 * A managed bean that provides an extension in the presentation layer for recruiters wanting to query job related data.
 * Such transactions can be related to retrieving a list of available jobs, adding new jobs and registering applicants as
 * employees.
 * @author Christian Schreil
 */

@Named("jobView")
@SessionScoped
public class JobView implements Serializable {
    
    @Inject
    private JobController controller;
    
    
    
    
}
