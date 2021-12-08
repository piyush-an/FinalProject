/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Person;

import Business.Organ.Organ;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bhabani
 */
public class Donor {
    private String name;
    
    private int age;
    private String gender;
    private String bloodGroup;
    private String contactNum;
    private String address;
    private String city;
    private String sign;
    private String emailAdd;
    private String date;
    private String network;
    private String pocName;
    private String pocContact;
    private boolean isOrganAvaiNow;
    List<String> organs;
    private ArrayList<Organ> organDirectory = new ArrayList<Organ>();

     public boolean getIsOrganAvaiNow() {
        return isOrganAvaiNow;
    }

    public void setIsOrganAvaiNow(boolean isOrganAvaiNow) {
        this.isOrganAvaiNow = isOrganAvaiNow;
    }
    
    public List<String> getOrgans() {
        return organs;
    }

    public void setOrgans(List<String> organs) {
        this.organs = organs;
    }
    
    /**
     * @return the pocName
     */
    public String getPocName() {
        return pocName;
    }

    /**
     * @param pocName the pocName to set
     */
    public void setPocName(String pocName) {
        this.pocName = pocName;
    }

    /**
     * @return the pocContact
     */
    public String getPocContact() {
        return pocContact;
    }

    /**
     * @param pocContact the pocContact to set
     */
    public void setPocContact(String pocContact) {
        this.pocContact = pocContact;
    }
    
    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }
    
    public String getEmailAdd() {
        return emailAdd;
    }

    public void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
    }

    public String getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the bloodGroup
     */
    public String getBloodGroup() {
        return bloodGroup;
    }

    /**
     * @param bloodGroup the bloodGroup to set
     */
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    /**
     * @return the contactNum
     */
    public String getContactNum() {
        return contactNum;
    }

    /**
     * @param contactNum the contactNum to set
     */
    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the sign
     */
    public String getSign() {
        return sign;
    }

    /**
     * @param sign the sign to set
     */
    public void setSign(String sign) {
        this.sign = sign;
    }
    
    @Override
    public String toString() {
        return this.getName();
    }
}
