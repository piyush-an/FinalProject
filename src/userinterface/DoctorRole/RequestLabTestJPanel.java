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
        jLabel3 = new javax.swing.JLabel();

        setLayout(null);

        requestTestJButton.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        requestTestJButton.setForeground(new java.awt.Color(4, 65, 96));
        requestTestJButton.setText("Request Tests");
        requestTestJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestTestJButtonActionPerformed(evt);
            }
        });
        add(requestTestJButton);
        requestTestJButton.setBounds(400, 250, 173, 29);
        add(messageJTextField);
        messageJTextField.setBounds(400, 200, 170, 23);

        backJButton.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        backJButton.setForeground(new java.awt.Color(4, 65, 96));
        backJButton.setText("Return");
        backJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButtonActionPerformed(evt);
            }
        });
        add(backJButton);
        backJButton.setBounds(730, 20, 99, 29);

        valueLabel.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        valueLabel.setForeground(new java.awt.Color(0, 51, 51));
        valueLabel.setText("<value>");
        add(valueLabel);
        valueLabel.setBounds(190, 20, 160, 30);

        enterpriseLabel.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        enterpriseLabel.setForeground(new java.awt.Color(0, 51, 51));
        enterpriseLabel.setText("Enterprise :");
        add(enterpriseLabel);
        enterpriseLabel.setBounds(60, 20, 150, 30);

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 51));
        jLabel2.setText("Others Test:");
        add(jLabel2);
        jLabel2.setBounds(250, 150, 130, 30);

        vitamins.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        vitamins.setForeground(new java.awt.Color(0, 51, 51));
        vitamins.setText("Vitamins Tests");
        add(vitamins);
        vitamins.setBounds(550, 90, 220, 27);

        glucose.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        glucose.setForeground(new java.awt.Color(0, 51, 51));
        glucose.setText("Blood Glucose Test");
        add(glucose);
        glucose.setBounds(70, 90, 250, 27);

        fullBlood.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        fullBlood.setForeground(new java.awt.Color(0, 51, 51));
        fullBlood.setText("Full Blood Count");
        add(fullBlood);
        fullBlood.setBounds(327, 90, 210, 27);

        othersJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                othersJTextFieldActionPerformed(evt);
            }
        });
        add(othersJTextField);
        othersJTextField.setBounds(400, 150, 170, 23);

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 51));
        jLabel4.setText("Message:");
        add(jLabel4);
        jLabel4.setBounds(280, 200, 100, 23);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/DoctorRole/reqT.png"))); // NOI18N
        add(jLabel3);
        jLabel3.setBounds(0, -5, 860, 710);
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
            request.getTests().add("Vitamins Test");

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
        dwjp.resetFields();
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField messageJTextField;
    private javax.swing.JTextField othersJTextField;
    private javax.swing.JButton requestTestJButton;
    private javax.swing.JLabel valueLabel;
    private javax.swing.JCheckBox vitamins;
    // End of variables declaration//GEN-END:variables
}
