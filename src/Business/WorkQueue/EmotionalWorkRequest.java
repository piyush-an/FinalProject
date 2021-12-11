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
public class EmotionalWorkRequest extends WorkRequest {
    
    private String patient_name;
    private Donor donor;
    private Boolean serviceOne;
    private Boolean serviceTwo;
    private Boolean serviceThree;
    private Boolean serviceFour;

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

    public Boolean getServiceThree() {
        return serviceThree;
    }

    public void setServiceThree(Boolean serviceThree) {
        this.serviceThree = serviceThree;
    }

    public Boolean getServiceFour() {
        return serviceFour;
    }

    public void setServiceFour(Boolean serviceFour) {
        this.serviceFour = serviceFour;
    }
    
    
    
}
