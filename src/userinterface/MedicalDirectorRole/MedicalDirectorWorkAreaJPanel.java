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
        profilepic();
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

    public void profilepic() {
        ImageIcon image_path = new ImageIcon(userAccount.getProfile());
        profilePicture.setIcon(image_path);

    }

    public void pushwork() {
        for (Network network : system.getNetworkList()) {

            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                for (Organization organizationd : enterprise.getOrganizationDirectory().getOrganizationList()) {
                    System.out.println("checking for doctor organization" + organizationd);
                    if (organizationd instanceof DoctorOrganization) {
                        //organization.getWorkQueue().getWorkRequestList();
                        System.out.println("Inside pushwork");

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
            System.err.print("Inside workloop meddir" + request);
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
        profilePicture = new javax.swing.JLabel();
        profileName = new javax.swing.JLabel();
        quals = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        workRequestJTable = new javax.swing.JTable();

        jLabel1.setText("This is Director work area");

        refreshJButton.setText("Refresh");
        refreshJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshJButtonActionPerformed(evt);
            }
        });

        assignJButton.setText("Assign to me");
        assignJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignJButtonActionPerformed(evt);
            }
        });

        processJButton.setText("Process");
        processJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processJButtonActionPerformed(evt);
            }
        });

        profilePicture.setText("User Profile Picture");

        profileName.setText("UserName");

        quals.setText("quals");

        jLabel6.setText("Upcoming Events");

        jLabel7.setText("No Upcoming Events");

        jLabel8.setText("No Upcoming Events");

        jLabel9.setText("No Upcoming Events");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(profilePicture, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(profileName)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6)
                            .addComponent(quals, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(refreshJButton)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(assignJButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(processJButton)))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(profilePicture, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(profileName)
                                .addGap(18, 18, 18)
                                .addComponent(quals)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addGap(0, 93, Short.MAX_VALUE))
                            .addComponent(jSeparator1))
                        .addGap(76, 76, 76))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(refreshJButton)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(processJButton)
                            .addComponent(assignJButton))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
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
           if(request.getStatus().equalsIgnoreCase("sent")){
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
            return;
        }

        TrailWorkRequest request = (TrailWorkRequest) workRequestJTable.getValueAt(selectedRow, 0);
       // if(request.getReceiver().toString().equalsIgnoreCase(userAccount.toString())){
            request.setStatus("Processing");
            request.setReceiver(userAccount);
          
       // }
        //else{
           // JOptionPane.showMessageDialog(null, "Please Assign the request first");
          //  return;
        //}
        

//        OrganWorkRequest request = (OrganWorkRequest)workRequestJTable.getValueAt(selectedRow, 0);
//        
//     
//        request.setStatus("Processing");
        DirectorProcessWorkRequesJPanel directorProcessWorkRequestJPanel = new DirectorProcessWorkRequesJPanel(userProcessContainer, request, donorDirectory, business, network, userAccount);
        userProcessContainer.add("processWorkRequestJPanel", directorProcessWorkRequestJPanel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);

    }//GEN-LAST:event_processJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton assignJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton processJButton;
    private javax.swing.JLabel profileName;
    private javax.swing.JLabel profilePicture;
    private javax.swing.JLabel quals;
    private javax.swing.JButton refreshJButton;
    private javax.swing.JTable workRequestJTable;
    // End of variables declaration//GEN-END:variables
}
