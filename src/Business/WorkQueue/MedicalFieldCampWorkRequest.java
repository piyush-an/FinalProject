/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import java.util.Date;

/**
 *
 * @author piyush
 */
public class MedicalFieldCampWorkRequest extends WorkRequest {
    
    private String fieldCampName;
    private String venue;
    private Date startDate;
    private Date endDate;

    public String getFieldCampName() {
        return fieldCampName;
    }

    public void setFieldCampName(String fieldCampName) {
        this.fieldCampName = fieldCampName;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    
}
