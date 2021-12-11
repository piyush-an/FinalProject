/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.DoctorRole;

import Business.EcoSystem;
import Business.Employee.Employee;
import Business.Enterprise.*;
import Business.Network.Network;
import Business.Organization.AwarnessEventManagementOrganization;
import Business.Organization.DoctorOrganization;
import Business.Organization.LabOrganization;
import Business.Organization.OrganTissueDonationOrganization;
import Business.Organization.Organization;
import Business.Organization.PatientOrganization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.EventWorkRequest;
import Business.WorkQueue.LabTestWorkRequest;
import Business.WorkQueue.MedicalFieldCampWorkRequest;
import Business.WorkQueue.OrganWorkRequest;
import Business.WorkQueue.TrailWorkRequest;
import Business.WorkQueue.WorkRequest;
import com.db4o.User;
import java.awt.CardLayout;
import java.awt.Desktop;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raunak
 */
public class DoctorWorkAreaJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private DoctorOrganization organization;
    private Enterprise enterprise;
    private UserAccount userAccount;
    private EnterpriseDirectory enterpriseDirectory;
    private EcoSystem ecosystem;
    private Network network;

    /**
     * Creates new form DoctorWorkAreaJPanel
     */
    public DoctorWorkAreaJPanel(JPanel userProcessContainer, UserAccount account, DoctorOrganization organization, Enterprise enterprise, EcoSystem ecosystem, Network network) {
        initComponents();

        this.userProcessContainer = userProcessContainer;
        this.organization = organization;
        this.enterprise = enterprise;
        this.ecosystem = ecosystem;
        this.userAccount = account;
        this.enterpriseDirectory = new EnterpriseDirectory();
        this.network = network;
        valueLabel.setText(enterprise.getName());
        profileName.setText(userAccount.getUsername());
        quals.setText(userAccount.getQualifications());
        populateRequestTable();
        populateOrganRequestTable();
        profilepic();
        upcomingevents();
        populatePatientComboBox(enterprise);
        populateCampOrganizationJTable();
        lblAgeValue.setVisible(false);
        lblAge.setVisible(false);
        lblName1.setVisible(false);
        lblNameValue1.setVisible(false);
        lblSexValue.setVisible(false);
        lblSex.setVisible(false);

    }

    public void populatePatientComboBox(Enterprise enterprise) {
        cboAvailablePatients.removeAllItems();
        for (Organization org : enterprise.getOrganizationDirectory().getOrganizationList()) {
            if (org instanceof PatientOrganization) {
                for (UserAccount user : org.getUserAccountDirectory().getUserAccountList()) {
                    // if(user.isIsAvailable())
                    cboAvailablePatients.addItem(user);
                }

            }
        }
    }

    public void upcomingevents() {
        for (Network eachNetwork : ecosystem.getNetworkList()) {
            for (Enterprise eachEnt : eachNetwork.getEnterpriseDirectory().getEnterpriseList()) {
                for (Organization eachOrg : eachEnt.getOrganizationDirectory().getOrganizationList()) {
//                    System.err.println(eachOrg);
                    //organization.getWorkQueue().getWorkRequestList().add(request);

                    if (eachOrg instanceof AwarnessEventManagementOrganization) {

                        for (WorkRequest request : eachOrg.getWorkQueue().getWorkRequestList()) {
                            if (request instanceof EventWorkRequest) {
                                if (jLabel1.getText().equals("No Upcoming Events")) {
                                    jLabel1.setText(request.getMessage());
                                } else if (jLabel2.getText().equals("No Upcoming Events")) {
                                    jLabel2.setText(request.getMessage());
                                } else if (jLabel3.getText().equals("No Upcoming Events")) {
                                    jLabel3.setText(request.getMessage());
                                }

                            }
                        }

                    }
                }
            }
        }

    }

    public void profilepic() {
        ImageIcon image_path = new ImageIcon(userAccount.getProfile());
        profilePicture.setIcon(image_path);

    }

    public void populateOrganRequestTable() {

        DefaultTableModel model = (DefaultTableModel) organRequestJTable.getModel();
        Organization org = null;
        for (Organization organizationEach : enterprise.getOrganizationDirectory().getOrganizationList()) {
            if (organizationEach instanceof OrganTissueDonationOrganization) {
                org = organizationEach;
                break;
            }
        }
//        org.getWorkQueue().get

        try {
            if (org.getWorkQueue().getWorkRequestList().isEmpty()) {
                return;
            }
        } catch (Exception e) {
            return;
        }
        for (WorkRequest request : org.getWorkQueue().getWorkRequestList()) {
            if (request instanceof TrailWorkRequest) {
                Object[] row = new Object[5];
                row[0] = request;
                row[1] = ((TrailWorkRequest) request).getPatient_name();
                row[2] = request.getStatus();
                row[3] = request.getReceiver();
                String result = ((TrailWorkRequest) request).getMatchResult();
//                row[4] = result == null ? "Waiting" : result;
                model.addRow(row);
            }
        }
    }

    public void populateCampOrganizationJTable() {
        DefaultTableModel model = (DefaultTableModel) CampOrganizationJTable.getModel();

        try {
            if (organization.getWorkQueue().getWorkRequestList().isEmpty()) {
                return;
            }
        } catch (Exception e) {
            return;
        }
        for (WorkRequest request : organization.getWorkQueue().getWorkRequestList()) {
            Object[] row = new Object[7];
            row[0] = request;
            row[1] = ((MedicalFieldCampWorkRequest) request).getFieldCampName();
            row[2] = ((MedicalFieldCampWorkRequest) request).getVenue();
            row[3] = ((MedicalFieldCampWorkRequest) request).getStartDate();
            row[4] = ((MedicalFieldCampWorkRequest) request).getEndDate();
            row[5] = request.getStatus();
            row[6] = request.getReceiver();
            model.addRow(row);
        }
    }

    public void populateRequestTable() {
        DefaultTableModel model = (DefaultTableModel) workRequestJTable.getModel();

        Organization org = null;
        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
            if (organization instanceof LabOrganization) {
                org = organization;
                break;
            }
        }

        model.setRowCount(0);
        try {
            if (org.getWorkQueue().getWorkRequestList().isEmpty()) {
                return;
            }
        } catch (Exception e) {
            return;
        }
        for (WorkRequest request : org.getWorkQueue().getWorkRequestList()) {
            if (request instanceof LabTestWorkRequest) {

                Object[] row = new Object[6];
                row[0] = request;
                row[1] = ((LabTestWorkRequest) request).getPatientAccount().getEmployee().getName();
                row[2] = request.getStatus();
                row[3] = request.getReceiver();
                row[4] = request.getMessage();
                String result = ((LabTestWorkRequest) request).getTestResult();
                row[5] = result == null ? "Waiting" : result;
                model.addRow(row);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        workRequestJTable = new javax.swing.JTable();
        requestTestJButton = new javax.swing.JButton();
        refreshTestJButton = new javax.swing.JButton();
        enterpriseLabel = new javax.swing.JLabel();
        valueLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        organRequestJTable = new javax.swing.JTable();
        requestOrganJButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        profilePicture = new javax.swing.JLabel();
        profileName = new javax.swing.JLabel();
        quals = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblPatient = new javax.swing.JLabel();
        cboAvailablePatients = new javax.swing.JComboBox();
        lblAge = new javax.swing.JLabel();
        lblSex = new javax.swing.JLabel();
        lblAgeValue = new javax.swing.JLabel();
        lblSexValue = new javax.swing.JLabel();
        lblName1 = new javax.swing.JLabel();
        lblNameValue1 = new javax.swing.JLabel();
        viewbtn = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        CampOrganizationJTable = new javax.swing.JTable();
        btnAssign = new javax.swing.JButton();
        btnAccept = new javax.swing.JButton();
        btnDecline = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        workRequestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Patient Name", "Status", "Owner", "Message", "Result"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(workRequestJTable);

        requestTestJButton.setText("Request Test");
        requestTestJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestTestJButtonActionPerformed(evt);
            }
        });

        refreshTestJButton.setText("Refresh");
        refreshTestJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshTestJButtonActionPerformed(evt);
            }
        });

        enterpriseLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        enterpriseLabel.setText("EnterPrise :");

        valueLabel.setText("<value>");

        organRequestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Patient Name", "Status", "Owner"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(organRequestJTable);

        requestOrganJButton.setText("Request Organ");
        requestOrganJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestOrganJButtonActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        profilePicture.setText("User Profile Picture");

        profileName.setText("UserName");

        quals.setText("quals");

        jLabel1.setText("No Upcoming Events");

        jLabel2.setText("No Upcoming Events");

        jLabel3.setText("No Upcoming Events");

        lblPatient.setText("Patient:");

        cboAvailablePatients.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboAvailablePatients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAvailablePatientsActionPerformed(evt);
            }
        });

        lblAge.setText("Age:");

        lblSex.setText("Sex");

        lblAgeValue.setText("Age");

        lblSexValue.setText("Sex");

        lblName1.setText("Name");

        lblNameValue1.setText("name");

        viewbtn.setText("View Report");
        viewbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewbtnActionPerformed(evt);
            }
        });

        CampOrganizationJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Camp Name", "Venue", "Start Date", "End Date", "Status", "Doctor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(CampOrganizationJTable);

        btnAssign.setText("Assign to Self");
        btnAssign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssignActionPerformed(evt);
            }
        });

        btnAccept.setText("Accept");
        btnAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcceptActionPerformed(evt);
            }
        });

        btnDecline.setText("Reject");
        btnDecline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeclineActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(profilePicture, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profileName)
                    .addComponent(quals)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAge)
                                .addGap(19, 19, 19)
                                .addComponent(lblAgeValue)
                                .addGap(82, 82, 82)
                                .addComponent(lblSex)
                                .addGap(19, 19, 19)
                                .addComponent(lblSexValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPatient)
                                .addGap(19, 19, 19)
                                .addComponent(cboAvailablePatients, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(refreshTestJButton))
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblName1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(lblNameValue1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(256, 256, 256))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(requestTestJButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(requestOrganJButton)
                                .addGap(176, 176, 176)
                                .addComponent(viewbtn))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(enterpriseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(valueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAssign)
                                .addGap(27, 27, 27)
                                .addComponent(btnAccept)
                                .addGap(18, 18, 18)
                                .addComponent(btnDecline)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(profilePicture, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(profileName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quals)
                .addGap(85, 85, 85)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addGap(26, 26, 26))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enterpriseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(refreshTestJButton)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPatient)
                    .addComponent(cboAvailablePatients, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAge)
                    .addComponent(lblAgeValue)
                    .addComponent(lblSex)
                    .addComponent(lblSexValue)
                    .addComponent(lblName1)
                    .addComponent(lblNameValue1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(requestTestJButton)
                    .addComponent(requestOrganJButton)
                    .addComponent(viewbtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAssign)
                    .addComponent(btnAccept)
                    .addComponent(btnDecline))
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void requestTestJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestTestJButtonActionPerformed
//        int selectedRow = workRequestJTable.getSelectedRow();
//        if (selectedRow < 0) {
//            JOptionPane.showMessageDialog(null,"Please select the patient for which you have to request test.");
//        }
//        
//        LabTestWorkRequest testRequest = (LabTestWorkRequest) workRequestJTable.getValueAt(selectedRow,0);
//        
        UserAccount patientUserAccount = (UserAccount) cboAvailablePatients.getSelectedItem();

        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        userProcessContainer.add("RequestLabTestJPanel", new RequestLabTestJPanel(userProcessContainer, userAccount, patientUserAccount, enterprise));
        layout.next(userProcessContainer);

    }//GEN-LAST:event_requestTestJButtonActionPerformed

    private void refreshTestJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshTestJButtonActionPerformed

        populateRequestTable();
        populateOrganRequestTable();

    }//GEN-LAST:event_refreshTestJButtonActionPerformed

    private void requestOrganJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestOrganJButtonActionPerformed
        // TODO add your handling code here:
        UserAccount puser = (UserAccount) cboAvailablePatients.getSelectedItem();
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        userProcessContainer.add("RequestOrganJPanel", new RequestOrganJPanel(userProcessContainer, userAccount, enterprise, enterpriseDirectory, ecosystem, network, puser));
        layout.next(userProcessContainer);
    }//GEN-LAST:event_requestOrganJButtonActionPerformed

    private void cboAvailablePatientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAvailablePatientsActionPerformed
        // TODO add your handling code here:
        //UserAccount user;
        // user.getEmployee() = (Employee) cboAvailablePatients.getSelectedItem();
        UserAccount user = (UserAccount) cboAvailablePatients.getSelectedItem();
        if (user != null) {
            lblAgeValue.setVisible(true);
            lblAge.setVisible(true);
            lblName1.setVisible(true);
            lblNameValue1.setVisible(true);
            lblSexValue.setVisible(true);
            lblSex.setVisible(true);
            lblAgeValue.setText(String.valueOf(user.getAge()));
            lblSexValue.setText(user.getSex());
            lblNameValue1.setText(user.getEmployee().getName());

        }
    }//GEN-LAST:event_cboAvailablePatientsActionPerformed

    private void viewbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewbtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = workRequestJTable.getSelectedRow();

        if (selectedRow < 0) {
            return;
        }

        WorkRequest request = (WorkRequest) workRequestJTable.getValueAt(selectedRow, 0);

        String repo_name = ((LabTestWorkRequest) request).getFile_name();
        if (!repo_name.equals("")) {
            try {
                Desktop.getDesktop().open(new java.io.File(repo_name));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_viewbtnActionPerformed

    private void btnAssignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssignActionPerformed
        // TODO add your handling code here:

        int selectedRow = CampOrganizationJTable.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
        WorkRequest request = (WorkRequest) CampOrganizationJTable.getValueAt(selectedRow, 0);
        request.setReceiver(userAccount);
        request.setStatus("Pending Confirmation");
        populateCampOrganizationJTable();


    }//GEN-LAST:event_btnAssignActionPerformed

    private void btnAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcceptActionPerformed
        // TODO add your handling code here:
        int selectedRow = CampOrganizationJTable.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
        WorkRequest request = (WorkRequest) CampOrganizationJTable.getValueAt(selectedRow, 0);
        request.setStatus("Accepted");
        populateCampOrganizationJTable();
    }//GEN-LAST:event_btnAcceptActionPerformed

    private void btnDeclineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeclineActionPerformed
        // TODO add your handling code here:
        int selectedRow = CampOrganizationJTable.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
        WorkRequest request = (WorkRequest) CampOrganizationJTable.getValueAt(selectedRow, 0);
        request.setStatus("Declined");
        populateCampOrganizationJTable();
    }//GEN-LAST:event_btnDeclineActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CampOrganizationJTable;
    private javax.swing.JButton btnAccept;
    private javax.swing.JButton btnAssign;
    private javax.swing.JButton btnDecline;
    private javax.swing.JComboBox cboAvailablePatients;
    private javax.swing.JLabel enterpriseLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblAge;
    private javax.swing.JLabel lblAgeValue;
    private javax.swing.JLabel lblName1;
    private javax.swing.JLabel lblNameValue1;
    private javax.swing.JLabel lblPatient;
    private javax.swing.JLabel lblSex;
    private javax.swing.JLabel lblSexValue;
    private javax.swing.JTable organRequestJTable;
    private javax.swing.JLabel profileName;
    private javax.swing.JLabel profilePicture;
    private javax.swing.JLabel quals;
    private javax.swing.JButton refreshTestJButton;
    private javax.swing.JButton requestOrganJButton;
    private javax.swing.JButton requestTestJButton;
    private javax.swing.JLabel valueLabel;
    private javax.swing.JButton viewbtn;
    private javax.swing.JTable workRequestJTable;
    // End of variables declaration//GEN-END:variables
}
