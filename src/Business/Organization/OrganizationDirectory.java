/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Organization.Organization.Type;
import java.util.ArrayList;

/**
 *
 * @author Bhabani
 */
public class OrganizationDirectory {
    
    private ArrayList<Organization> organizationList;

    public OrganizationDirectory() {
        organizationList = new ArrayList();
    }

    public ArrayList<Organization> getOrganizationList() {
        return organizationList;
    }
    
    public Organization createOrganization(Type type, String name, int newID){
        Organization organization = null;
        
        if (type.getValue().equals(Type.Doctor.getValue())){
            organization = new DoctorOrganization();
            organization.setTypeof(type.toString());
            organization.setName(name);
            organization.setOrganizationID(newID+1);
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Type.Lab.getValue())){
            organization = new LabOrganization();
            organization.setTypeof(type.toString());
            organization.setName(name);
            organization.setOrganizationID(newID+1);
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Type.OrganTissueDonationOrganization.getValue())){
            organization = new OrganTissueDonationOrganization();
            organization.setTypeof(type.toString());
            organization.setName(name);
            organization.setOrganizationID(newID+1);
            organizationList.add(organization);
        }
           else if (type.getValue().equals(Type.DonorOrganization.getValue())){
            organization = new OrganTissueDonationOrganization();
            organization.setTypeof(type.toString());
            organization.setName(name);
            organization.setOrganizationID(newID+1);
            organizationList.add(organization);
        }
        else if(type.getValue().equals(Type.AwarnessEventManagementOrganization.getValue())){
             organization = new AwarnessEventManagementOrganization();
             organization.setTypeof(type.toString());
             organization.setName(name);
             organization.setOrganizationID(newID+1);
            organizationList.add(organization);
        
        }
        else if(type.getValue().equals(Type.Patient.getValue())){
             organization = new PatientOrganization();
             organization.setTypeof(type.toString());
             organization.setName(name);
             organization.setOrganizationID(newID+1);
            organizationList.add(organization);
        
        }

//         Piyush - Added
        else if(type.getValue().equals(Type.EmotionalOrganization.getValue())){
             organization = new EmotionalOrganization();
             organization.setTypeof(type.toString());
             organization.setName(name);
             organization.setOrganizationID(newID+1);
            organizationList.add(organization);
        
        }
        
        else if(type.getValue().equals(Type.LegalOrganization.getValue())){
             organization = new LegalOrganization();
             organization.setTypeof(type.toString());
             organization.setName(name);
             organization.setOrganizationID(newID+1);
            organizationList.add(organization);
        
        }
        
        else if(type.getValue().equals(Type.HealthCampOrganization.getValue())){
             organization = new HealthCampOrganization();
             organization.setTypeof(type.toString());
             organization.setName(name);
             organization.setOrganizationID(newID+1);
            organizationList.add(organization);
        
        }
        
        return organization;
    }
}
