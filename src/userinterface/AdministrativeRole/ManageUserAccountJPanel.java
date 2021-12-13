/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.AdministrativeRole;

import Business.Employee.Employee;
import Business.Enterprise.Enterprise;
import Business.Organization.DoctorOrganization;
import Business.Organization.Organization;
import static Business.Organization.Organization.Type.Patient;
import Business.Organization.PatientOrganization;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import Business.UserAccount.UserAccountDirectory;
import java.awt.CardLayout;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class ManageUserAccountJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ManageUserAccountJPanel
     */
    private JPanel container;
    private Enterprise enterprise;
    private String file_path;
    private UserAccountDirectory accountDir;
    ArrayList <String> userstring = new ArrayList<String>();
    //ArrayList<Employee> docList;
    public ManageUserAccountJPanel(JPanel container, Enterprise enterprise) {
        initComponents();
        this.enterprise = enterprise;
        this.container = container;
        
        //txtAge.setVisible(false);
        //lblAge.setVisible(false);
        //radFemale.setVisible(false);
        //radMale.setVisible(false);
        //lblSex.setVisible(false);
      //  docList = new ArrayList<Employee>();
       // lblAppointmentDate.setVisible(false);
        //appointmentjDateChooser.setVisible(false);
        //lblDocList.setVisible(false);
        //cboDoctorList.setVisible(false);
        popOrganizationComboBox();
       // employeeJComboBox.removeAllItems();
        popData();
          popuser();
    }
    public void popuser(){
        for(UserAccount u:enterprise.getUserAccountDirectory().getUserAccountList()){
            userstring.add(u.toString());
        }
    }

    public void popOrganizationComboBox() {
        organizationJComboBox.removeAllItems();
        //organizationJComboBox

        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
            organizationJComboBox.addItem(organization);
        }
    }
    
//    public void populateAvailableDoctorsComboBox(ArrayList<Employee> docList){
//    
//        cboDoctorList.removeAllItems();
//        for(Employee item : docList){
//            cboDoctorList.addItem(item);
//        }
//        
//    }
    
    public void populateEmployeeComboBox(Organization organization){
        employeeJComboBox.removeAllItems();
        
        for (Employee employee : organization.getEmployeeDirectory().getEmployeeList()){
            employeeJComboBox.addItem(employee);
        }
    }
    
    private void populateRoleComboBox(Organization organization){
        
        roleJComboBox.removeAllItems();
        for (Role role : organization.getSupportedRole()){
            roleJComboBox.addItem(role);
        }
    }
     public boolean isFirstnameValid(String text) {

        return text.matches("^([A-Za-z]+)(\\s[A-Za-z]+)*\\s?$");
        
    }
     private boolean valage(String age){
       Pattern pattern = Pattern.compile("[0-9]+");
       Matcher matcher = pattern.matcher(age);
        boolean bool = matcher.matches();
        if (!bool) {
            JOptionPane.showMessageDialog(null,"Age should only be numbers");
            return false;
        } else {
           
            return true;
        }
     }
    private boolean valPhone(String ph){
      Pattern pattern = Pattern.compile("[0-9]{10}");
        Matcher matcher = pattern.matcher(ph);
        boolean bool = matcher.matches();
        if (!bool) {
            return true;
            //JOptionPane.showMessageDialog(null,"Invalid phone number format");
//            return false;
        } else {
           
            return true;
        }
    }
      private boolean valPass(String password){
       Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
        Matcher matcher = pattern.matcher(password);
        boolean bool = matcher.matches();
        if (!bool) {
            JOptionPane.showMessageDialog(null,"Password should contain a small,large,numeric,special and length of 8");
            return false;
        } else {
           
            return true;
        }
    }
        private boolean usernamePatternCorrect(String username) {
                int f=0;
            
        Pattern pattern = Pattern.compile("(?!_).*[A-Za-z0-9._]+@[A-Za-z0-9._]+\\.[A-Za-z]{2,4}");
        Matcher matcher = pattern.matcher(username);
        boolean bool = matcher.matches();
        if (!bool) {
            JOptionPane.showMessageDialog(null,"Special characters are not allowed other than _, @");
            return false;
        } else {
           
            for(String s:userstring){
                System.out.println("Useraccounts"+s);
                if(s.equalsIgnoreCase(username)){
                    // System.out.println("Username accepted");
                     JOptionPane.showMessageDialog(null, "Username exists please take another username");
                     return false;
                }
            }
            return true;
        }
    }

    public void popData() {

        DefaultTableModel model = (DefaultTableModel) userJTable.getModel();

        model.setRowCount(0);

        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
            for (UserAccount ua : organization.getUserAccountDirectory().getUserAccountList()) {
                Object row[] = new Object[3];
                row[0] = ua;
                row[1] = organization;
                row[2] = ua.getRole();
                
                model.addRow(row);
                userstring.add(ua.toString());
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        createUserJButton = new javax.swing.JButton();
        nameJTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userJTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        passwordJTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        employeeJComboBox = new javax.swing.JComboBox();
        backjButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        organizationJComboBox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        roleJComboBox = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        addressTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        qualTextfield = new javax.swing.JTextField();
        profilebtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        phNumTextField = new javax.swing.JTextField();
        lblAge = new javax.swing.JLabel();
        txtAge = new javax.swing.JTextField();
        lblSex = new javax.swing.JLabel();
        radMale = new javax.swing.JRadioButton();
        radFemale = new javax.swing.JRadioButton();
        btnDelete = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        createUserJButton.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        createUserJButton.setForeground(new java.awt.Color(4, 65, 96));
        createUserJButton.setText("Create");
        createUserJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createUserJButtonActionPerformed(evt);
            }
        });
        add(createUserJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 480, 160, 40));
        add(nameJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 390, 207, -1));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 65, 96));
        jLabel1.setText("User Name");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 390, 120, -1));

        userJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User Name", "Organization", "Role"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(userJTable);
        if (userJTable.getColumnModel().getColumnCount() > 0) {
            userJTable.getColumnModel().getColumn(0).setResizable(false);
            userJTable.getColumnModel().getColumn(2).setResizable(false);
        }

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 960, 190));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(4, 65, 96));
        jLabel2.setText("Password");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, 100, -1));
        add(passwordJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 440, 207, -1));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(4, 65, 96));
        jLabel3.setText("Employee");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 290, 100, -1));

        employeeJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeJComboBoxActionPerformed(evt);
            }
        });
        add(employeeJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 290, 163, -1));

        backjButton1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        backjButton1.setForeground(new java.awt.Color(4, 65, 96));
        backjButton1.setText("HOME");
        backjButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backjButton1ActionPerformed(evt);
            }
        });
        add(backjButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 110, -1));

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(4, 65, 96));
        jLabel5.setText("Organization");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, -1, -1));

        organizationJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                organizationJComboBoxActionPerformed(evt);
            }
        });
        add(organizationJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, 207, -1));

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(4, 65, 96));
        jLabel4.setText("Role");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 50, -1));

        roleJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roleJComboBoxActionPerformed(evt);
            }
        });
        add(roleJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 207, -1));

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(4, 65, 96));
        jLabel6.setText("Address");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 340, 90, -1));
        add(addressTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 340, 163, -1));

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(4, 65, 96));
        jLabel7.setText("Qualifications");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 390, 140, -1));
        add(qualTextfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 390, 163, -1));

        profilebtn.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        profilebtn.setForeground(new java.awt.Color(4, 65, 96));
        profilebtn.setText("Upload Profile");
        profilebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profilebtnActionPerformed(evt);
            }
        });
        add(profilebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 570, -1, -1));

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(4, 65, 96));
        jLabel8.setText("Phone Number");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 490, 150, -1));
        add(phNumTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 490, 207, -1));

        lblAge.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lblAge.setForeground(new java.awt.Color(4, 65, 96));
        lblAge.setText("Age");
        add(lblAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 530, 40, -1));
        add(txtAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 530, 207, -1));

        lblSex.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lblSex.setForeground(new java.awt.Color(4, 65, 96));
        lblSex.setText("Sex");
        add(lblSex, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 440, 40, -1));

        buttonGroup1.add(radMale);
        radMale.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        radMale.setForeground(new java.awt.Color(0, 0, 0));
        radMale.setText("Male");
        add(radMale, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 440, 90, -1));

        buttonGroup1.add(radFemale);
        radFemale.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        radFemale.setForeground(new java.awt.Color(0, 0, 0));
        radFemale.setText("Female");
        add(radFemale, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 440, -1, -1));

        btnDelete.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(4, 65, 96));
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 30, 130, 40));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/AdministrativeRole/MA.png"))); // NOI18N
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 540, 960, 200));
    }// </editor-fold>//GEN-END:initComponents

    private void createUserJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createUserJButtonActionPerformed
        int f=0;
        int newage=0;
        String userName = nameJTextField.getText();
        String password = passwordJTextField.getText();
        String phNum = phNumTextField.getText();
        
        String address = addressTextField.getText();
        String qalString = qualTextfield.getText();
        String sex;
        boolean availability=true;
        boolean organavail = true;
        String age = (txtAge.getText());
        if(radMale.isSelected()){
            sex = "M";
        } 
        else {
            sex = "F";
        }
        //Date date = new Date();
        if(address.equals("")){
            JOptionPane.showMessageDialog(null, "Address can't be empty");
            return;
        }
        if(usernamePatternCorrect(userName)){
            f=1;
          }
        else{
            JOptionPane.showMessageDialog(null, "Invalid Username");
            return;
        }
        if(valPass(password)){
            f=1;
        }
        else{
            //JOptionPane.showMessageDialog(null, "Invalid Password");
            return;
        }
        
        if(valPhone(phNum)){
            f=1;
        }
        else{
            return;
        }
        if(isFirstnameValid(qalString)){
            f=1;
        }
        else{
            return;
        }
        if(valage(age)){
            newage = Integer.parseInt(age);
            if(newage<0 || newage>200){
                JOptionPane.showMessageDialog(null, "Age should be between 1 and 199");
            }
            f=1;
        }
        else{
            return;
        }
        if(f==0){
           JOptionPane.showMessageDialog(null, "Please fill all the details");
        }
        else{
         
            
        Organization organization = (Organization) organizationJComboBox.getSelectedItem();
        Employee employee = (Employee) employeeJComboBox.getSelectedItem();
        Role role = (Role) roleJComboBox.getSelectedItem();
            organization.getUserAccountDirectory().createUserAccount(userName,password,phNum,address,qalString, sex, availability, newage, file_path, employee, role,organavail);
        
        popData();
        employeeJComboBox.removeItem(employee);}
    }//GEN-LAST:event_createUserJButtonActionPerformed

    
    
    private void backjButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backjButton1ActionPerformed
        // TODO add your handling code here:
        container.remove(this);
        CardLayout layout = (CardLayout) container.getLayout();
        layout.previous(container);
    }//GEN-LAST:event_backjButton1ActionPerformed

    private void organizationJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_organizationJComboBoxActionPerformed
        Organization organization = (Organization) organizationJComboBox.getSelectedItem();
        if (organization != null){
            populateEmployeeComboBox(organization);
            populateRoleComboBox(organization);
        }
        
//        if(organization instanceof DoctorOrganization){
//            
//            for (Employee employee : organization.getEmployeeDirectory().getEmployeeList()){
//            docList.add(employee);
//        }
//        }
//        if(organization instanceof PatientOrganization)
//        {
//            lblDocList.setVisible(true);
//            cboDoctorList.setVisible(true);
//         populateAvailableDoctorsComboBox(docList);   
//        }
    }//GEN-LAST:event_organizationJComboBoxActionPerformed

    private void profilebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profilebtnActionPerformed
        // TODO add your handling code here:
                JFileChooser resource = new JFileChooser();
        resource.showOpenDialog(null);
        File path = resource.getSelectedFile();
        file_path = path.getAbsolutePath();

        
    }//GEN-LAST:event_profilebtnActionPerformed

    private void roleJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roleJComboBoxActionPerformed
        // TODO add your handling code here:
        txtAge.setVisible(true);
        lblAge.setVisible(true);
        radFemale.setVisible(true);
        radMale.setVisible(true);
        lblSex.setVisible(true);
//        lblAppointmentDate.setVisible(true);
//        appointmentjDateChooser.setVisible(true);
        
    }//GEN-LAST:event_roleJComboBoxActionPerformed

    private void employeeJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employeeJComboBoxActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        
        int selectedRow = userJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "No Row Selected, Please select one");
            return;
        }
        UserAccount userAccountToDelete = (UserAccount) userJTable.getValueAt(selectedRow, 0);
        Organization organizationtoDelete = (Organization) userJTable.getValueAt(selectedRow, 1);
        
        if(organizationtoDelete.getUserAccountDirectory().getUserAccountList().remove(userAccountToDelete)){
            JOptionPane.showMessageDialog(null, "Employee Deleted");
        }else {
            JOptionPane.showMessageDialog(null, "Employee could NOT be Deleted");
        }
        popData();
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressTextField;
    private javax.swing.JButton backjButton1;
    private javax.swing.JButton btnDelete;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton createUserJButton;
    private javax.swing.JComboBox employeeJComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAge;
    private javax.swing.JLabel lblSex;
    private javax.swing.JTextField nameJTextField;
    private javax.swing.JComboBox organizationJComboBox;
    private javax.swing.JTextField passwordJTextField;
    private javax.swing.JTextField phNumTextField;
    private javax.swing.JButton profilebtn;
    private javax.swing.JTextField qualTextfield;
    private javax.swing.JRadioButton radFemale;
    private javax.swing.JRadioButton radMale;
    private javax.swing.JComboBox roleJComboBox;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTable userJTable;
    // End of variables declaration//GEN-END:variables
}
