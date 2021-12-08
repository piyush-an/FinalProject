/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem1;

import Business.Organization;
import Business.UserAccount.UserAccount;
import userinterface.CustomerRole.CustomerAreaJPanel;
import javax.swing.JPanel;

/**
 *
 * @author raunak
 */
public class CustomerRole extends Role{

    
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, EcoSystem1 business) {
        return new CustomerAreaJPanel(userProcessContainer, account);
    }
    
    
}
