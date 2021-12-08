/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organ;

import java.util.ArrayList;

/**
 *
 * @author Bhabani
 */
public class OrganDirectory {

    private ArrayList<Organ> OrganDirectory;

    public OrganDirectory() {
        OrganDirectory = new ArrayList<Organ>();
        Organ org1 = new Organ("Heart", "O positive");
        Organ org2 = new Organ("Liver", "O positive");
        Organ org3 = new Organ("Eyes", "O positive");
        Organ org4 = new Organ("Kidney", "O positive");
        Organ org5 = new Organ("Lungs", "O positive");
        Organ org6 = new Organ("Pancreas", "O positive");
        
        OrganDirectory.add(org1);
        OrganDirectory.add(org2);
        OrganDirectory.add(org3);
        OrganDirectory.add(org4);
        OrganDirectory.add(org5);
        OrganDirectory.add(org6);
        
        

    }

    public ArrayList<Organ> getOrganDirectory() {
        return OrganDirectory;
    }

    public void setOrganDirectory(ArrayList<Organ> OrganDirectory) {
        this.OrganDirectory = OrganDirectory;
    }
    public void addOrgan(Organ o){
        
        OrganDirectory.add(o);
     
    }
    
    public void removeOrgan(Organ o){
        OrganDirectory.remove(o);
        
    }

}