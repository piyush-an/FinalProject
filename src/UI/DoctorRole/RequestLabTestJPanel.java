/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.DoctorRole;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.LabOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.LabTestWorkRequest;
import java.awt.CardLayout;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author raunak
 */
public class RequestLabTestJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private Enterprise enterprise;
    private UserAccount userAccount;
    private UserAccount patientAccount;

    //private LabTestWorkRequest testRequest;
    /**
     * Creates new form RequestLabTestJPanel
     */
    public RequestLabTestJPanel(JPanel userProcessContainer, UserAccount account, UserAccount patientAccount, Enterprise enterprise) {
        initComponents();

        this.userProcessContainer = userProcessContainer;
        this.enterprise = enterprise;
        this.userAccount = account;
        this.patientAccount = patientAccount;
        //  this.testRequest = testRequest;
        valueLabel.setText(enterprise.getName());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        requestTestJButton = new javax.swing.JButton();
        messageJTextField = new javax.swing.JTextField();
        backJButton = new javax.swing.JButton();
        valueLabel = new javax.swing.JLabel();
        enterpriseLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        vitamins = new javax.swing.JCheckBox();
        glucose = new javax.swing.JCheckBox();
        fullBlood = new javax.swing.JCheckBox();
        othersJTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        requestTestJButton.setText("Request Tests");
        requestTestJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestTestJButtonActionPerformed(evt);
            }
        });
        add(requestTestJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 250, -1, -1));
        add(messageJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, 140, -1));

        backJButton.setText("<<Back");
        backJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButtonActionPerformed(evt);
            }
        });
        add(backJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, -1, -1));

        valueLabel.setBackground(new java.awt.Color(204, 0, 51));
        valueLabel.setFont(new java.awt.Font("AppleMyungjo", 1, 30)); // NOI18N
        valueLabel.setForeground(new java.awt.Color(255, 255, 255));
        valueLabel.setText("<value>");
        valueLabel.setOpaque(true);
        add(valueLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 1090, 50));

        enterpriseLabel.setBackground(new java.awt.Color(204, 0, 51));
        enterpriseLabel.setFont(new java.awt.Font("AppleMyungjo", 1, 30)); // NOI18N
        enterpriseLabel.setForeground(new java.awt.Color(255, 255, 255));
        enterpriseLabel.setText("Enterprise:");
        enterpriseLabel.setOpaque(true);
        add(enterpriseLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 200, 50));

        jLabel2.setText("Others");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, -1, -1));

        vitamins.setText("Vitamins Tests");
        add(vitamins, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, -1, -1));

        glucose.setText("Blood Glucose Test");
        add(glucose, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, -1, -1));

        fullBlood.setText("Full Blood Count");
        add(fullBlood, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, -1, -1));

        othersJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                othersJTextFieldActionPerformed(evt);
            }
        });
        add(othersJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, 140, -1));

        jLabel4.setText("Message");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void requestTestJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestTestJButtonActionPerformed

        String message = messageJTextField.getText();

        LabTestWorkRequest request = new LabTestWorkRequest();
        

        if (glucose.isSelected()) {
            request.getTests().add("Blood Glucose Test");
        }
        if (fullBlood.isSelected()) {
            request.getTests().add("Full Blood Count Test");
        }
        if (vitamins.isSelected()) {
            request.getTests().add("Vitamins Tests");

        }
        if (othersJTextField.getText().isEmpty()) {

        } else {
            request.getTests().add(othersJTextField.getText());
        }
        
       
        
        request.setPatientAccount(patientAccount);
        request.setMessage(message);
        request.setSender(userAccount);
        request.setStatus("New Request");

        Organization org = null;
        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
            if (organization instanceof LabOrganization) {
                org = organization;
                break;
            }
        }
        
        /*
            WorkRequest Ids
        
            OrganTissueDonationOrganization + 
            DoctorOrganization + 
            OrganTissueDonationOrganization + 201
            EmotionalOrganization + 401
            HealthCampOrganization + 
            LabOrganization + 101
            LegalOrganization + 301
            PatientOrganization	+ 	
            */
        
        if (org != null) {
            try {
                request.setWorkID(org.getWorkQueue().getWorkRequestList().get(org.getWorkQueue().getWorkRequestList().size() - 1).getWorkID() + 1);
            } catch (Exception e) {
                request.setWorkID(101);
            }
            org.getWorkQueue().getWorkRequestList().add(request);
            userAccount.getWorkQueue().getWorkRequestList().add(request);
            JOptionPane.showMessageDialog(null, "Request Raised successfully !");
        } else {
            JOptionPane.showMessageDialog(null, "Request could not be raised !");
        }
        patientAccount.setIsAvailable(false);
        backJButtonActionPerformed(evt);

    }//GEN-LAST:event_requestTestJButtonActionPerformed

    private void backJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButtonActionPerformed

        userProcessContainer.remove(this);
        Component[] componentArray = userProcessContainer.getComponents();
        Component component = componentArray[componentArray.length - 1];
        DoctorWorkAreaJPanel dwjp = (DoctorWorkAreaJPanel) component;
        dwjp.populateRequestTable();
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);

    }//GEN-LAST:event_backJButtonActionPerformed

    private void othersJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_othersJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_othersJTextFieldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backJButton;
    private javax.swing.JLabel enterpriseLabel;
    private javax.swing.JCheckBox fullBlood;
    private javax.swing.JCheckBox glucose;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField messageJTextField;
    private javax.swing.JTextField othersJTextField;
    private javax.swing.JButton requestTestJButton;
    private javax.swing.JLabel valueLabel;
    private javax.swing.JCheckBox vitamins;
    // End of variables declaration//GEN-END:variables
}
