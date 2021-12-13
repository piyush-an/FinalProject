/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.CampOrganizerRole;

import userinterface.CounselorRole.*;
import Business.DB4OUtil.DB4OUtil;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import static Business.Enterprise.Enterprise.EnterpriseType.Hospital;
import Business.Enterprise.HospitalEnterprise;
import Business.Network.Network;
import Business.Organization.DoctorOrganization;
import Business.Organization.HealthCampOrganization;
import Business.Organization.OrganTissueDonationOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.MedicalFieldCampWorkRequest;
import Business.WorkQueue.TrailWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author piyush
 */
public class CampOrganizerRoleJPanel extends javax.swing.JPanel {

    /**
     * Creates new form CounselorJPanel
     */
    private JPanel userProcessContainer;
    private UserAccount userAccount;
    private HealthCampOrganization HealthCampOrganization;
    private Organization organization;
    private Enterprise enterprise;
    private EcoSystem business;
    private EcoSystem system;
    private Network network;
    private DB4OUtil dB4OUtil = DB4OUtil.getInstance();

    public CampOrganizerRoleJPanel(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business, Network network) {
        initComponents();

        this.userProcessContainer = userProcessContainer;
        this.userAccount = account;
        this.HealthCampOrganization = (HealthCampOrganization) organization;
        this.enterprise = enterprise;
        this.business = business;
        this.network = network;
        system = dB4OUtil.retrieveSystem();

        // User function
        populateTable();

    }

    public void populateTable() {
        DefaultTableModel model = (DefaultTableModel) CampOrganizationJTable.getModel();
        model.setRowCount(0);

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
                                row[3] = ((MedicalFieldCampWorkRequest) request).getStartDate();
                                row[4] = ((MedicalFieldCampWorkRequest) request).getEndDate();
                                row[5] = request.getStatus();
                                row[6] = request.getReceiver();
                                model.addRow(row);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
        }

//        try {
//            for (Network network : system.getNetworkList()) {
//                for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
//                    for (Organization org : enterprise.getOrganizationDirectory().getOrganizationList()) {
//                        for (WorkRequest request : org.getWorkQueue().getWorkRequestList()) {
//                            Object[] row = new Object[8];
//                            row[0] = request;
//                            row[1] = request.getStatus();
//                            row[2] = request.getSender();
//                            row[3] = request.getRequestDate();
//                            row[4] = request.getResolveDate();
//                            row[5] = request.getMessage();
//                            row[6] = network;
//                            row[7] = org;
//                            model.addRow(row);
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//        }
//        Organization org = null;
//        try {
//            for (Network networkEach : system.getNetworkList()) {
//                    try {
////            for (Network network : system.getNetworkList()) {
//
////                HospitalEnterprise enterpriseloop1 = (HospitalEnterprise) network.getEnterpriseDirectory().getEnterpriseList().get(1);
//            for (Enterprise enterpriseloop : network.getEnterpriseDirectory().getEnterpriseList()) {
//                if (enterpriseloop.getEnterpriseType().equals(Hospital)) {
//                    for (Organization org : enterpriseloop.getOrganizationDirectory().getOrganizationList()) {
//                        for (WorkRequest request : org.getWorkQueue().getWorkRequestList()) {
//                            if (request instanceof MedicalFieldCampWorkRequest) {
//                                Object[] row = new Object[7];
//                                row[0] = request;
//                                row[1] = ((MedicalFieldCampWorkRequest) request).getFieldCampName();
//                                row[2] = ((MedicalFieldCampWorkRequest) request).getVenue();
//                                row[3] = ((MedicalFieldCampWorkRequest) request).getStartDate();
//                                row[4] = ((MedicalFieldCampWorkRequest) request).getEndDate();
//                                row[5] = request.getStatus();
//                                row[6] = request.getReceiver();
//                                String result = ((TrailWorkRequest) request).getMatchResult();
////                row[4] = result == null ? "Waiting" : result;
//                                model.addRow(row);
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//        }
//            for (Enterprise enterpriseLoop : network.getEnterpriseDirectory().getEnterpriseList()) {
//                System.out.println("enterpriseLoop :" + enterpriseLoop);
//                if (enterpriseLoop.getTypeof().equalsIgnoreCase("Hospital")) {
//                    for (Organization OrganizationLoop : enterpriseLoop.getOrganizationDirectory().getOrganizationList()) {
//                        if (OrganizationLoop.getTypeof().equalsIgnoreCase("DoctorOrganization")) {
//                            for (WorkRequest request : OrganizationLoop.getWorkQueue().getWorkRequestList()) {
//                                Object[] row = new Object[7];
//                                row[0] = request;
//                                row[1] = ((MedicalFieldCampWorkRequest) request).getFieldCampName();
//                                row[2] = ((MedicalFieldCampWorkRequest) request).getVenue();
//                                row[3] = ((MedicalFieldCampWorkRequest) request).getStartDate();
//                                row[4] = ((MedicalFieldCampWorkRequest) request).getEndDate();
//                                row[5] = request.getStatus();
//                                row[6] = request.getReceiver();
//                                model.addRow(row);
//                            }
//                        }
//                    }
//                }
//
//            }
//            }
//        } catch (Exception e) {
//            
//
//        }
//        for (Organization organizationEach : enterprise.getOrganizationDirectory().getOrganizationList()) {
//            if (organizationEach instanceof DoctorOrganization) {
//                org = organizationEach;
//                break;
//            }
//        }
//
//        try {
//            if (org.getWorkQueue().getWorkRequestList().isEmpty()) {
//                return;
//            }
//        } catch (Exception e) {
//            return;
//        }
//        for (WorkRequest request : org.getWorkQueue().getWorkRequestList()) {
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        therapyTypes = new javax.swing.ButtonGroup();
        btnHome = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        CampOrganizationJTable = new javax.swing.JTable();
        btnCreateCampEvent = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHome.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        btnHome.setForeground(new java.awt.Color(60, 63, 65));
        btnHome.setText("HOME");
        btnHome.setEnabled(false);
        add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 61, 160, -1));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(60, 63, 65));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Medical Field Camps");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 888, 33));

        CampOrganizationJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "WorkID", "Camp Name", "Venue", "Start Date", "End Date", "Status", "Ownership"
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
        jScrollPane1.setViewportView(CampOrganizationJTable);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 108, 888, 201));

        btnCreateCampEvent.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        btnCreateCampEvent.setForeground(new java.awt.Color(60, 63, 65));
        btnCreateCampEvent.setText("CREATE CAMP");
        btnCreateCampEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateCampEventActionPerformed(evt);
            }
        });
        add(btnCreateCampEvent, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 319, 190, 37));

        jLabel2.setFont(new java.awt.Font("Shree Devanagari 714", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/CampOrganizerRole/Medical-Explainer-min.gif"))); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 360, 390, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateCampEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateCampEventActionPerformed
        // TODO add your handling code here:

        MedicalFieldCampWorkRequestJPanel MedicalFieldCampWorkRequestJPanel = new MedicalFieldCampWorkRequestJPanel(userProcessContainer, business, network, userAccount, HealthCampOrganization);
        userProcessContainer.add("processWorkRequestJPanel", MedicalFieldCampWorkRequestJPanel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);

//        int selectedRow = CampOrganizationJTable.getSelectedRow();
//        if (selectedRow < 0) {
//            JOptionPane.showMessageDialog(null, "No Row Selected, Please select one");
//            return;
//        }
//        WorkRequest request = (WorkRequest) CampOrganizationJTable.getValueAt(selectedRow, 0);
//
//        if (request.getStatus().equalsIgnoreCase("New Request")) {
//            for (WorkRequest requestCheck : HealthCampOrganization.getWorkQueue().getWorkRequestList()) {
//
//                if(requestCheck.getReceiver() != null && requestCheck.getReceiver().equals(userAccount) && (requestCheck.getStatus().equalsIgnoreCase("Assigned") || requestCheck.getStatus().equalsIgnoreCase("Work In Progress"))){
//                    JOptionPane.showMessageDialog(null, "User already working on a work request");
//                    return;
//                }
//            }
//            request.setReceiver(userAccount);
//            request.setStatus("Assigned");
//            populateTable();
//        } else {
//            JOptionPane.showMessageDialog(null, "This request is already being handled");
//        }
//        populateTable();
    }//GEN-LAST:event_btnCreateCampEventActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CampOrganizationJTable;
    private javax.swing.JButton btnCreateCampEvent;
    private javax.swing.JButton btnHome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.ButtonGroup therapyTypes;
    // End of variables declaration//GEN-END:variables
}
