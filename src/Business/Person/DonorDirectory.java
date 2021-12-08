/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Person;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bhabani
 */
public class DonorDirectory {
    
    private ArrayList<Donor> donorList;

    public DonorDirectory() {
        donorList = new ArrayList();
        ArrayList <String> organs = new ArrayList<String>();
        organs.add("Heart");
        organs.add("Lungs");
        organs.add("Kidney");
        organs.add("Liver");
        createDonor("Krishna", 20, "M", "O postive", "959595955", "15C corenila", "Gopi Krishna", "gopimanchu@gmail.com", "Sakshi", "8572727337", true, organs);
    }

    public ArrayList<Donor> getDonorList() {
        return donorList;
    }
    
    public Donor createDonor(String name, int age, String sex, String bloodGroup, String contactNum, String address, String sign,
                String emailAdd, String emergencyPOC, String emergencyPOC_Num, boolean isOrganAvaiNow, List<String >organs){
        Donor donor = new Donor();
        donor.setName(name);
        donor.setAge(age);
        donor.setGender(sex);
        donor.setBloodGroup(bloodGroup);
        donor.setContactNum(contactNum);
        donor.setAddress(address);
        donor.setSign(sign);
        donor.setEmailAdd(emailAdd);
        donor.setPocName(emergencyPOC);
        donor.setPocContact(emergencyPOC_Num);
        donor.setIsOrganAvaiNow(isOrganAvaiNow);
        donor.setOrgans(organs);
        donorList.add(donor);
        return donor;
    }
}
