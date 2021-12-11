/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author piyush
 */
public class DonorFamilySupport extends Enterprise {
    
    public DonorFamilySupport(String name){
        super(name,Enterprise.EnterpriseType.DonorFamilySupport);
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        return null;
    }
    
}
