/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.Person.Donor;

/**
 *
 * @author piyush
 */
public class LegalWorkRequest extends WorkRequest {
    
    
    private String patient_name;
    private Donor donor;
    private String serviceOpted;
    private Boolean serviceOne;
    private Boolean serviceTwo;

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public String getServiceOpted() {
        return serviceOpted;
    }

    public void setServiceOpted(String serviceOpted) {
        this.serviceOpted = serviceOpted;
    }

    public Boolean getServiceOne() {
        return serviceOne;
    }

    public void setServiceOne(Boolean serviceOne) {
        this.serviceOne = serviceOne;
    }

    public Boolean getServiceTwo() {
        return serviceTwo;
    }

    public void setServiceTwo(Boolean serviceTwo) {
        this.serviceTwo = serviceTwo;
    }
    
}
