/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.MedicalDirectorRole;

import Business.DB4OUtil.DB4OUtil;
import Business.Person.DonorDirectory;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.AwarnessEventManagementOrganization;
import Business.Organization.DoctorOrganization;
import Business.Organization.LabOrganization;
import Business.Organization.OrganTissueDonationOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.*;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import userinterface.DoctorRole.DoctorWorkAreaJPanel;
import userinterface.LabAssistantRole.ProcessWorkRequestJPanel;

/**
 *
 * @author piyush
 */
public class MedicalDirectorWorkAreaJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private EcoSystem business;
    private UserAccount userAccount;
    private EcoSystem system;
    private OrganTissueDonationOrganization organTissueDonationOrganization;
    private DB4OUtil dB4OUtil = DB4OUtil.getInstance();
    private UserAccount docAcc;
    private DonorDirectory donorDirectory;
    private Network network;

    /**
     * Creates new form MedicalDirectorWorkAreaJPanel
     */
    public MedicalDirectorWorkAreaJPanel(JPanel userProcessContainer, UserAccount account, Organization organization, EcoSystem business, DonorDirectory donorDirectory, Network network) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.userAccount = account;
        this.business = business;
        this.organTissueDonationOrganization = (OrganTissueDonationOrganization) organization;
        system = dB4OUtil.retrieveSystem();
        this.donorDirectory = donorDirectory;
        this.network = network;
        //System.out.print("Testing"+this.organTissueDonationOrganization);
        //pushwork();
        profileName.setText(userAccount.getUsername());
        quals.setText(userAccount.getQualifications());
        populateTable();
        //profilepic();
        upcomingevents();
    }

    public void upcomingevents() {
        for (Network network : business.getNetworkList()) {
            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                    System.err.println(organization);
                    //organization.getWorkQueue().getWorkRequestList().add(request);

                    if (organization instanceof AwarnessEventManagementOrganization) {

                        for (WorkRequest request : organization.getWorkQueue().getWorkRequestList()) {
                            if (request instanceof EventWorkRequest) {
                                if (jLabel7.getText().equals("No Upcoming Events")) {
                                    jLabel7.setText(request.getMessage());
                                } else if (jLabel8.getText().equals("No Upcoming Events")) {
                                    jLabel8.setText(request.getMessage());
                                } else if (jLabel9.getText().equals("No Upcoming Events")) {
                                    jLabel9.setText(request.getMessage());
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

    public void pushwork() {
        for (Network network : system.getNetworkList()) {

            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                for (Organization organizationd : enterprise.getOrganizationDirectory().getOrganizationList()) {
//                    System.out.println("checking for doctor organization" + organizationd);
                    if (organizationd instanceof DoctorOrganization) {
                        //organization.getWorkQueue().getWorkRequestList();
//                        System.out.println("Inside pushwork");

                        for (WorkRequest request : organizationd.getWorkQueue().getWorkRequestList()) {

                            if (request instanceof TrailWorkRequest) {

                                organTissueDonationOrganization.getWorkQueue().getWorkRequestList().add(request);

                            }

                        }

                    }
                }
            }
        }
    }

    public void populateTable() {
        //System.out.println("Inside Medical Director "+organTissueDonationOrganization.getWorkQueue().getWorkRequestList());
        DefaultTableModel model = (DefaultTableModel) workRequestJTable.getModel();

        model.setRowCount(0);
        // System.out.print("Testing"+this.organTissueDonationOrganization);
        for (WorkRequest request : organTissueDonationOrganization.getWorkQueue().getWorkRequestList()) {
//            System.err.print("Inside workloop meddir" + request);
            Object[] row = new Object[6];
            row[0] = request;
            row[1] = request.getSender().getEmployee().getName();
            row[2] = request.getReceiver() == null ? null : request.getReceiver().getEmployee().getName();
            row[3] = request.getStatus();
            row[4] = ((TrailWorkRequest) request).getPatient_name();
            row[5] = ((TrailWorkRequest) request).getSeverity();

            model.addRow(row);
        }
    }
//    DefaultTableModel model = (DefaultTableModel)workRequestJTable.getModel();

    //model.setRowCount(0);
    // for(WorkRequest request : labOrganization.getWorkQueue().getWorkRequestList()){
//            Object[] row = new Object[4];
//            row[0] = request;
//            row[1] = request.getSender().getEmployee().getName();
//            row[2] = request.getReceiver() == null ? null : request.getReceiver().getEmployee().getName();
//            row[3] = request.getStatus();
//            
//            model.addRow(row);
//        }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        refreshJButton = new javax.swing.JButton();
        assignJButton = new javax.swing.JButton();
        processJButton = new javax.swing.JButton();
        profileName = new javax.swing.JLabel();
        quals = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        workRequestJTable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 65, 96));
        jLabel1.setText("This is Director work area");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 300, -1));

        refreshJButton.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        refreshJButton.setForeground(new java.awt.Color(4, 65, 96));
        refreshJButton.setText("Refresh");
        refreshJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshJButtonActionPerformed(evt);
            }
        });
        add(refreshJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, 140, -1));

        assignJButton.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        assignJButton.setForeground(new java.awt.Color(4, 65, 96));
        assignJButton.setText("Assign to me");
        assignJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignJButtonActionPerformed(evt);
            }
        });
        add(assignJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, 180, -1));

        processJButton.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        processJButton.setForeground(new java.awt.Color(4, 65, 96));
        processJButton.setText("Process");
        processJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processJButtonActionPerformed(evt);
            }
        });
        add(processJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 270, 140, -1));

        profileName.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        profileName.setForeground(new java.awt.Color(4, 65, 96));
        profileName.setText("UserName");
        add(profileName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 180, -1));

        quals.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        quals.setForeground(new java.awt.Color(4, 65, 96));
        quals.setText("quals");
        add(quals, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 150, -1));

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(4, 65, 96));
        jLabel6.setText("Upcoming Events");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 200, -1));

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(4, 65, 96));
        jLabel7.setText("No Upcoming Events");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 220, -1));

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(4, 65, 96));
        jLabel8.setText("No Upcoming Events");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 220, -1));

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(4, 65, 96));
        jLabel9.setText("No Upcoming Events");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 220, -1));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 35, 542));

        workRequestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Message", "Sender", "Receiver", "Status", "PatientName", "Severity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 80, 650, 180));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/MedicalDirectorRole/MD.png"))); // NOI18N
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 265, 580, 410));
    }// </editor-fold>//GEN-END:initComponents

    private void refreshJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshJButtonActionPerformed
        populateTable();
    }//GEN-LAST:event_refreshJButtonActionPerformed

    private void assignJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignJButtonActionPerformed
        
        int selectedRow = workRequestJTable.getSelectedRow();

        if (selectedRow < 0) {
            return;
        }

        WorkRequest request = (WorkRequest) workRequestJTable.getValueAt(selectedRow, 0);
           if(request.getStatus().equalsIgnoreCase("New Request")){
             request.setReceiver(userAccount);
        request.setStatus("Pending");
        populateTable();
          
        }
        else{
            JOptionPane.showMessageDialog(null, "This request is already being handled");
            return;
        }
       

    }//GEN-LAST:event_assignJButtonActionPerformed

    private void processJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processJButtonActionPerformed
        int selectedRow = workRequestJTable.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "No Row Selected, Please select one");
            return;
        }

        TrailWorkRequest request = (TrailWorkRequest) workRequestJTable.getValueAt(selectedRow, 0);
        

        if(request.getReceiver().getUsername().equals(userAccount.getUsername()) && !(request.getStatus().equalsIgnoreCase("Match Found")) ){
            request.setStatus("Processing");
            request.setReceiver(userAccount);
        DirectorProcessWorkRequesJPanel directorProcessWorkRequestJPanel = new DirectorProcessWorkRequesJPanel(userProcessContainer, request, donorDirectory, business, network, userAccount);
        userProcessContainer.add("processWorkRequestJPanel", directorProcessWorkRequestJPanel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
        }
        else {
            JOptionPane.showMessageDialog(null, "Request not assigned to you");
        }
    }//GEN-LAST:event_processJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton assignJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton processJButton;
    private javax.swing.JLabel profileName;
    private javax.swing.JLabel quals;
    private javax.swing.JButton refreshJButton;
    private javax.swing.JTable workRequestJTable;
    // End of variables declaration//GEN-END:variables
}
