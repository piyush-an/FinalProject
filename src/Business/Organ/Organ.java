/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organ;

/**
 *
 * @author Bhabani
 */
public class Organ {
    private String organ_type;
    private String blood_type;
    private boolean available;
    private String Network;

    public Organ(String organ_type, String blood_type) {
        this.organ_type = organ_type;
        this.blood_type = blood_type;
        this.available =  true;
        
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getOrgan_type() {
        return organ_type;
    }

    public void setOrgan_type(String organ_type) {
        this.organ_type = organ_type;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }
      @Override
    public String toString(){
        return this.organ_type;
    }
    
    
    
    
}
