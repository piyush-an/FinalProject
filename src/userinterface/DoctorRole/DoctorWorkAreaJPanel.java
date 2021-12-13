/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.DoctorRole;

import Business.EcoSystem;
import Business.Employee.Employee;
import Business.Enterprise.*;
import static Business.Enterprise.Enterprise.EnterpriseType.Hospital;
import static Business.Enterprise.Enterprise.EnterpriseType.OrganBank;
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
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
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
        //profilepic();
        upcomingevents();
        populatePatientComboBox(enterprise);
        populateCampOrganizationJTable();
//        lblAgeValue.setVisible(false);
//        lblAge.setVisible(false);
//        lblName1.setVisible(false);
//        lblNameValue1.setVisible(false);
//        lblSexValue.setVisible(false);
//        lblSex.setVisible(false);
        resetFields();
//        

    }

    public void resetFields() {
        cboAvailablePatients.setSelectedIndex(-1);
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

//    public void profilepic() {
//        ImageIcon image_path = new ImageIcon(userAccount.getProfile());
//        profilePicture.setIcon(image_path);
//
//    }

    public void populateOrganRequestTable() {

        DefaultTableModel model = (DefaultTableModel) organRequestJTable.getModel();
        model.setRowCount(0);
//        Organization org = null;

        try {
//            for (Network network : system.getNetworkList()) {

//                HospitalEnterprise enterpriseloop1 = (HospitalEnterprise) network.getEnterpriseDirectory().getEnterpriseList().get(1);
            for (Enterprise enterpriseloop : network.getEnterpriseDirectory().getEnterpriseList()) {
                if (enterpriseloop.getEnterpriseType().equals(OrganBank)) {
                    for (Organization org : enterpriseloop.getOrganizationDirectory().getOrganizationList()) {
                        for (WorkRequest request : org.getWorkQueue().getWorkRequestList()) {
                            if (request instanceof TrailWorkRequest) {
                                Object[] row = new Object[5];
                                row[0] = request;
                                row[1] = ((TrailWorkRequest) request).getPatient_name();
                                row[2] = request.getStatus();
                                row[3] = request.getReceiver();
                                row[4] = ((TrailWorkRequest) request).getOrgan();
//                                String result = ((TrailWorkRequest) request).getMatchResult();
//                row[4] = result == null ? "Waiting" : result;
                                model.addRow(row);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            // return;
        }

//        for (Organization organizationEach : enterprise.getOrganizationDirectory().getOrganizationList()) {
//            if (organizationEach instanceof OrganTissueDonationOrganization) {
//                org = organizationEach;
//                break;
//            }
//        }
////        org.getWorkQueue().get
//
//        try {
//            if (org.getWorkQueue().getWorkRequestList().isEmpty()) {
//                return;
//            }
//        } catch (Exception e) {
//            return;
//        }
//        for (WorkRequest request : org.getWorkQueue().getWorkRequestList()) {
//            if (request instanceof TrailWorkRequest) {
//                Object[] row = new Object[5];
//                row[0] = request;
//                row[1] = ((TrailWorkRequest) request).getPatient_name();
//                row[2] = request.getStatus();
//                row[3] = request.getReceiver();
//                String result = ((TrailWorkRequest) request).getMatchResult();
////                row[4] = result == null ? "Waiting" : result;
//                model.addRow(row);
//            }
//        }
    }

    public void populateCampOrganizationJTable() {
        DefaultTableModel model = (DefaultTableModel) CampOrganizationJTable.getModel();
        model.setRowCount(0);

        SimpleDateFormat datemMonth = new SimpleDateFormat("MMM dd, yyyy");

        try {
//            for (Network network : system.getNetworkList()) {

//                HospitalEnterprise enterpriseloop1 = (HospitalEnterprise) network.getEnterpriseDirectory().getEnterpriseList().get(1);
            for (Enterprise enterpriseloop : network.getEnterpriseDirectory().getEnterpriseList()) {
                if (enterpriseloop.getEnterpriseType().equals(Hospital)) {
                    for (Organization org : enterpriseloop.getOrganizationDirectory().getOrganizationList()) {
                        for (WorkRequest request : org.getWorkQueue().getWorkRequestList()) {
                            if (request instanceof MedicalFieldCampWorkRequest) {
                                Object[] row = new Object[7];
                                row[0] = request;
                                row[1] = ((MedicalFieldCampWorkRequest) request).getFieldCampName();
                                row[2] = ((MedicalFieldCampWorkRequest) request).getVenue();
                                row[3] = datemMonth.format(((MedicalFieldCampWorkRequest) request).getStartDate());
                                row[4] = datemMonth.format(((MedicalFieldCampWorkRequest) request).getEndDate());
                                row[5] = request.getStatus();
                                row[6] = request.getReceiver();
                                model.addRow(row);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            // return;
        }
//        try {
//            if (organization.getWorkQueue().getWorkRequestList().isEmpty()) {
//                return;
//            }
//        } catch (Exception e) {
//            return;
//        }
//        for (WorkRequest request : organization.getWorkQueue().getWorkRequestList()) {
//            Object[] row = new Object[7];
//            row[0] = request;
//            row[1] = ((MedicalFieldCampWorkRequest) request).getFieldCampName();
//            row[2] = ((MedicalFieldCampWorkRequest) request).getVenue();
//            row[3] = ((MedicalFieldCampWorkRequest) request).getStartDate();
//            row[4] = ((MedicalFieldCampWorkRequest) request).getEndDate();
//            row[5] = request.getStatus();
//            row[6] = request.getReceiver();
//            model.addRow(row);
//        }
    }

    public void populateRequestTable() {
        DefaultTableModel model = (DefaultTableModel) workRequestJTable.getModel();
        model.setRowCount(0);

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
                if (request.getStatus().equalsIgnoreCase("Completed")){
                    row[5] = true ;
                } else {
                    row[5] = false ;
                }
//                String result = ((LabTestWorkRequest) request).getTestResult();
//                row[5] = result == null ? "Waiting" : result;
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
        lblPatient2 = new javax.swing.JLabel();
        lblPatient4 = new javax.swing.JLabel();
        lblPatient5 = new javax.swing.JLabel();

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

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);

        workRequestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Patient Name", "Status", "Owner", "Message", "Result"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(workRequestJTable);

        add(jScrollPane1);
        jScrollPane1.setBounds(226, 215, 860, 130);

        requestTestJButton.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        requestTestJButton.setForeground(new java.awt.Color(4, 65, 96));
        requestTestJButton.setText("Request Test");
        requestTestJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestTestJButtonActionPerformed(evt);
            }
        });
        add(requestTestJButton);
        requestTestJButton.setBounds(230, 150, 178, 33);

        refreshTestJButton.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        refreshTestJButton.setForeground(new java.awt.Color(4, 65, 96));
        refreshTestJButton.setText("Refresh");
        refreshTestJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshTestJButtonActionPerformed(evt);
            }
        });
        add(refreshTestJButton);
        refreshTestJButton.setBounds(950, 80, 125, 33);

        enterpriseLabel.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        enterpriseLabel.setForeground(new java.awt.Color(0, 0, 0));
        enterpriseLabel.setText("Enterprise :");
        add(enterpriseLabel);
        enterpriseLabel.setBounds(213, 24, 140, 30);

        valueLabel.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        valueLabel.setForeground(new java.awt.Color(0, 0, 0));
        valueLabel.setText("<value>");
        add(valueLabel);
        valueLabel.setBounds(330, 30, 170, 26);

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

        add(jScrollPane2);
        jScrollPane2.setBounds(230, 370, 860, 130);

        requestOrganJButton.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        requestOrganJButton.setForeground(new java.awt.Color(4, 65, 96));
        requestOrganJButton.setText("Request Organ");
        requestOrganJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestOrganJButtonActionPerformed(evt);
            }
        });
        add(requestOrganJButton);
        requestOrganJButton.setBounds(410, 150, 197, 33);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator1);
        jSeparator1.setBounds(201, 6, 19, 744);

        profileName.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        profileName.setForeground(new java.awt.Color(0, 0, 0));
        profileName.setText("UserName");
        add(profileName);
        profileName.setBounds(20, 30, 170, 18);

        quals.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        quals.setForeground(new java.awt.Color(0, 0, 0));
        quals.setText("quals");
        add(quals);
        quals.setBounds(20, 60, 50, 18);

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("No Upcoming Events");
        add(jLabel1);
        jLabel1.setBounds(10, 570, 180, 18);

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("No Upcoming Events");
        add(jLabel2);
        jLabel2.setBounds(10, 610, 180, 18);

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("No Upcoming Events");
        add(jLabel3);
        jLabel3.setBounds(10, 650, 180, 18);

        lblPatient.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblPatient.setForeground(new java.awt.Color(0, 0, 0));
        lblPatient.setText("Field Camp Requests");
        add(lblPatient);
        lblPatient.setBounds(230, 510, 190, 18);

        cboAvailablePatients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAvailablePatientsActionPerformed(evt);
            }
        });
        add(cboAvailablePatients);
        cboAvailablePatients.setBounds(330, 70, 180, 35);

        lblAge.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblAge.setForeground(new java.awt.Color(0, 0, 0));
        lblAge.setText("Age:");
        add(lblAge);
        lblAge.setBounds(250, 120, 50, 18);

        lblSex.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblSex.setForeground(new java.awt.Color(0, 0, 0));
        lblSex.setText("Sex:");
        add(lblSex);
        lblSex.setBounds(380, 120, 40, 18);

        lblAgeValue.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblAgeValue.setForeground(new java.awt.Color(0, 0, 0));
        lblAgeValue.setText("Age");
        add(lblAgeValue);
        lblAgeValue.setBounds(300, 120, 50, 18);

        lblSexValue.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblSexValue.setForeground(new java.awt.Color(0, 0, 0));
        lblSexValue.setText("Sex");
        add(lblSexValue);
        lblSexValue.setBounds(430, 120, 50, 18);

        lblName1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblName1.setForeground(new java.awt.Color(0, 0, 0));
        lblName1.setText("Name:");
        add(lblName1);
        lblName1.setBounds(520, 120, 60, 18);

        lblNameValue1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblNameValue1.setForeground(new java.awt.Color(0, 0, 0));
        lblNameValue1.setText("name");
        add(lblNameValue1);
        lblNameValue1.setBounds(580, 120, 100, 20);

        viewbtn.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        viewbtn.setForeground(new java.awt.Color(4, 65, 96));
        viewbtn.setText("View Report");
        viewbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewbtnActionPerformed(evt);
            }
        });
        add(viewbtn);
        viewbtn.setBounds(920, 150, 170, 33);

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

        add(jScrollPane4);
        jScrollPane4.setBounds(226, 529, 870, 150);

        btnAssign.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        btnAssign.setForeground(new java.awt.Color(4, 65, 96));
        btnAssign.setText("Assign to Self");
        btnAssign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssignActionPerformed(evt);
            }
        });
        add(btnAssign);
        btnAssign.setBounds(230, 690, 185, 33);

        lblPatient2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblPatient2.setForeground(new java.awt.Color(0, 0, 0));
        lblPatient2.setText("Patient:");
        add(lblPatient2);
        lblPatient2.setBounds(220, 80, 70, 18);

        lblPatient4.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblPatient4.setForeground(new java.awt.Color(0, 0, 0));
        lblPatient4.setText("Test Requests");
        add(lblPatient4);
        lblPatient4.setBounds(230, 190, 120, 18);

        lblPatient5.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblPatient5.setForeground(new java.awt.Color(0, 0, 0));
        lblPatient5.setText("Organ Requests");
        add(lblPatient5);
        lblPatient5.setBounds(230, 350, 150, 18);
    }// </editor-fold>//GEN-END:initComponents

    private void requestTestJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestTestJButtonActionPerformed
//        int selectedRow = workRequestJTable.getSelectedRow();
//        if (selectedRow < 0) {
//            JOptionPane.showMessageDialog(null,"Please select the patient for which you have to request test.");
//        }
//        
//        LabTestWorkRequest testRequest = (LabTestWorkRequest) workRequestJTable.getValueAt(selectedRow,0);

//        
        if (cboAvailablePatients.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Please select a Patient from drodown");
            return;
        }
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
        if (cboAvailablePatients.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Please select a Patient from drodown");
            return;
        }
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
            JOptionPane.showMessageDialog(null, "No Row Selected, Please select one");
            return;
        } else {
            
            
            
            JFrame frame = new JFrame("Swing Tester");
//            JButton button = new JButton("Click Me!");
//            final JLabel label = new JLabel();
            
            LabTestWorkRequest request = (LabTestWorkRequest) workRequestJTable.getValueAt(selectedRow, 0);
            
            if(!request.getStatus().equalsIgnoreCase("Completed")){
                JOptionPane.showMessageDialog(null, "Work Request not completed yet !");
                return;
            }
//StringBuilder detailedReported = (StringBuilder) workRequestPatientJTable.getValueAt(selectedRow, 3);
//            int result = JOptionPane.showConfirmDialog(frame, detailedReported , "",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
//            if (result == JOptionPane.YES_OPTION) {
//                
//            }
//JOptionPane.show
            JOptionPane.showMessageDialog(null, request.getDetailedReported() , workRequestJTable.getValueAt(selectedRow, 0).toString() + " * REPORT * ", JOptionPane.OK_OPTION);           
        }
        
        populateRequestTable();

//        int selectedRow = workRequestJTable.getSelectedRow();

//        if (selectedRow < 0) {
//
//            JOptionPane.showMessageDialog(null, "Please select a LabTest from table !");
//            return;
//        }

//        WorkRequest request = (WorkRequest) workRequestJTable.getValueAt(selectedRow, 0);
//
//        String repo_name = ((LabTestWorkRequest) request).getFile_name();
//        if (!repo_name.equals("")) {
//            try {
//                Desktop.getDesktop().open(new java.io.File(repo_name));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }

    }//GEN-LAST:event_viewbtnActionPerformed

    private void btnAssignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssignActionPerformed
        // TODO add your handling code here:

        int selectedRow = CampOrganizationJTable.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
        WorkRequest request = (WorkRequest) CampOrganizationJTable.getValueAt(selectedRow, 0);
        if (request.getStatus().equalsIgnoreCase("New Request") || request.getStatus().equalsIgnoreCase("Declined")) {
//            request.setReceiver(userAccount);
//            request.setStatus("Pending Confirmation");
            JFrame frame = new JFrame("Please Confirm your availability");
            int result = JOptionPane.showConfirmDialog(frame, "Please select Accept or Decline.", "Please Confirm your availability",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                request.setReceiver(userAccount);
                request.setStatus("Accepted");
                JOptionPane.showMessageDialog(null, "You have Accepted the Field Camp");
            }else if (result == JOptionPane.NO_OPTION) {
                request.setStatus("Declined");
                JOptionPane.showMessageDialog(null, "You have Declined the Field Camp");
            } else {
//               request.setReceiver(null);
//               request.setStatus("New Request");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Can only Assign a 'New Request' or 'Declined' !");
        }
        populateCampOrganizationJTable();
    }//GEN-LAST:event_btnAssignActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CampOrganizationJTable;
    private javax.swing.JButton btnAssign;
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
    private javax.swing.JLabel lblPatient2;
    private javax.swing.JLabel lblPatient4;
    private javax.swing.JLabel lblPatient5;
    private javax.swing.JLabel lblSex;
    private javax.swing.JLabel lblSexValue;
    private javax.swing.JTable organRequestJTable;
    private javax.swing.JLabel profileName;
    private javax.swing.JLabel quals;
    private javax.swing.JButton refreshTestJButton;
    private javax.swing.JButton requestOrganJButton;
    private javax.swing.JButton requestTestJButton;
    private javax.swing.JLabel valueLabel;
    private javax.swing.JButton viewbtn;
    private javax.swing.JTable workRequestJTable;
    // End of variables declaration//GEN-END:variables
}
