/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Employee.EmployeeDirectory;
import Business.Role.Role;
import Business.UserAccount.UserAccountDirectory;
import Business.WorkQueue.WorkQueue;
import java.util.ArrayList;

/**
 *
 * @author Bhabani
 */
public abstract class Organization {

    private String name;
    private WorkQueue workQueue;
    private EmployeeDirectory employeeDirectory;
    private UserAccountDirectory userAccountDirectory;
    private int organizationID;
    private String typeof;
    private static int counter=0;
    
    public enum Type{
        Admin("Admin Organization"), 
        Doctor("Doctor Organization"), 
        Lab("Lab Organization"),
        OrganTissueDonationOrganization("Organ Tissue Donation Organization "),
        DonorOrganization("Donor Organization"),
        AwarnessEventManagementOrganization("AwarnessEventManagementOrganization"),
        Patient("Patient Organization"),
        EmotionalOrganization("Emotional Organization"),
        HealthCampOrganization("Health Camp Organization"),
        LegalOrganization("Legal Organization");
        
        private String value;
        private Type(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    public Organization(String name) {
        this.name = name;
        workQueue = new WorkQueue();
        employeeDirectory = new EmployeeDirectory();
        userAccountDirectory = new UserAccountDirectory();
//        this.organizationID = organizationID+1;
//        organizationID = newID;
//        ++counter;
    }

    public abstract ArrayList<Role> getSupportedRole();

    public String getTypeof() {
        return typeof;
    }

    public void setTypeof(String typeof) {
        this.typeof = typeof;
    }
    
    public UserAccountDirectory getUserAccountDirectory() {
        return userAccountDirectory;
    }

    public int getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(int organizationID) {
        this.organizationID = organizationID;
    }

    public EmployeeDirectory getEmployeeDirectory() {
        return employeeDirectory;
    }
    
    public String getName() {
        return name;
    }

    public WorkQueue getWorkQueue() {
        return workQueue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorkQueue(WorkQueue workQueue) {
        this.workQueue = workQueue;
    }
    
    @Override
    public String toString() {
    return name;
    }
 
    
}
