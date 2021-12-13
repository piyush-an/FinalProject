package Business;

import Business.DB4OUtil.DB4OUtil;
import Business.Employee.Employee;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organ.Organ;
import Business.Organization.Organization;
import Business.Role.AdminRole;
import Business.Role.CampOrganizerRole;
import Business.Role.CounselorRole;
import Business.Role.DoctorRole;
import Business.Role.EventOrganizer;
import Business.Role.LabAssistantRole;
import Business.Role.LegalAdvisorRole;
import Business.Role.MedicalDirector;
import Business.Role.PatientRole;
import Business.Role.SystemAdminRole;
import Business.UserAccount.UserAccount;
import com.github.javafaker.Faker;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rrheg
 */
public class ConfigureASystem {

    public static EcoSystem configure() {
        DB4OUtil dB4OUtil = DB4OUtil.getInstance();
        EcoSystem system = EcoSystem.getInstance();

        //Create a network
        Network network = system.createAndAddNetwork();
        network.setName("Los Angeles");

        //create an enterprise
        // Hospital
        Enterprise enterpriseLAGeneralHospital = network.getEnterpriseDirectory().createAndAddEnterprise("LA General Hospital", Enterprise.EnterpriseType.Hospital);
        Organization PatientOrganizationPathology = enterpriseLAGeneralHospital.getOrganizationDirectory().createOrganization(Organization.Type.Patient, "OPD", 0);
        Organization DoctorOrganizationPhysician = enterpriseLAGeneralHospital.getOrganizationDirectory().createOrganization(Organization.Type.Doctor, "Physician", 1);
        Organization LabOrganizationPathology = enterpriseLAGeneralHospital.getOrganizationDirectory().createOrganization(Organization.Type.Lab, "Pathology", 2);

        Employee employee1 = enterpriseLAGeneralHospital.getEmployeeDirectory().createEmployee("LA Hospital Admin", 0);
        UserAccount account1 = enterpriseLAGeneralHospital.getUserAccountDirectory().createUserAccount("lahosadmin@gmail.com", "Covid@2021", employee1, new AdminRole());

        Employee employee1a = PatientOrganizationPathology.getEmployeeDirectory().createEmployee("Roy", 0);
        Employee employee1b = PatientOrganizationPathology.getEmployeeDirectory().createEmployee("Joy", 1);

        PatientOrganizationPathology.getUserAccountDirectory().createUserAccount("roy@gmail.com", "Covid@2021", "1234567890", "Boylston St", "Student", "M", true, 22, null, employee1a, new PatientRole(), true);
        PatientOrganizationPathology.getUserAccountDirectory().createUserAccount("joy@gmail.com", "Covid@2021", "0987654321", "Park Dr", "IT Analyst", "F", true, 20, null, employee1b, new PatientRole(), true);

        Employee employee1c = DoctorOrganizationPhysician.getEmployeeDirectory().createEmployee("Raju", 0);
        Employee employee1d = DoctorOrganizationPhysician.getEmployeeDirectory().createEmployee("Sheetal", 1);

        DoctorOrganizationPhysician.getUserAccountDirectory().createUserAccount("raju@gmail.com", "Covid@2021", "1234567890", "Roxbury St", "MBBS", "M", true, 26, null, employee1c, new DoctorRole(), true);
        DoctorOrganizationPhysician.getUserAccountDirectory().createUserAccount("sheetal@gmail.com", "Covid@2021", "0987654321", "Mass Ave", "MBBS", "F", true, 24, null, employee1d, new DoctorRole(), true);

        Employee employee1e = LabOrganizationPathology.getEmployeeDirectory().createEmployee("Trigun", 0);
        Employee employee1f = LabOrganizationPathology.getEmployeeDirectory().createEmployee("Priti", 1);

        LabOrganizationPathology.getUserAccountDirectory().createUserAccount("trigun@gmail.com", "Covid@2021", "1234567890", "Riverway St", "Pathologists", "M", true, 25, null, employee1e, new LabAssistantRole(), true);
        LabOrganizationPathology.getUserAccountDirectory().createUserAccount("priti@gmail.com", "Covid@2021", "0987654321", "Medford Ave", "Pathologists", "F", true, 23, null, employee1f, new LabAssistantRole(), true);

        // Organ Bank
        Enterprise enterpriseLAOrganBank = network.getEnterpriseDirectory().createAndAddEnterprise("LA Organ Bank", Enterprise.EnterpriseType.OrganBank);
        Organization organBankEnterpriseDirector = enterpriseLAOrganBank.getOrganizationDirectory().createOrganization(Organization.Type.OrganTissueDonationOrganization, "CMO", 0);

        Employee employee2 = enterpriseLAOrganBank.getEmployeeDirectory().createEmployee("LA Organ Bank Admin", 0);
        UserAccount account2 = enterpriseLAOrganBank.getUserAccountDirectory().createUserAccount("laorgadmin@gmail.com", "Covid@2021", employee2, new AdminRole());

        Employee employee2a = organBankEnterpriseDirector.getEmployeeDirectory().createEmployee("Anish", 0);
        Employee employee2b = organBankEnterpriseDirector.getEmployeeDirectory().createEmployee("Madhu", 1);

        organBankEnterpriseDirector.getUserAccountDirectory().createUserAccount("anish@gmail.com", "Covid@2021", "1234567890", "Fenway St", "Director", "M", true, 25, null, employee2a, new MedicalDirector(), true);
        organBankEnterpriseDirector.getUserAccountDirectory().createUserAccount("madhu@gmail.com", "Covid@2021", "0987654321", "Quency", "Director", "M", true, 23, null, employee2b, new MedicalDirector(), true);

        // Donor Family Support
        Enterprise enterpriseLAFamilySupport = network.getEnterpriseDirectory().createAndAddEnterprise("LA Family Support", Enterprise.EnterpriseType.DonorFamilySupport);
        Organization DonorFamilySupportEnterpriseEmotional = enterpriseLAFamilySupport.getOrganizationDirectory().createOrganization(Organization.Type.EmotionalOrganization, "Therapy", 0);
        Organization DonorFamilySupportEnterpriseLegal = enterpriseLAFamilySupport.getOrganizationDirectory().createOrganization(Organization.Type.LegalOrganization, "Lawyer", 1);

        Employee employee3 = enterpriseLAFamilySupport.getEmployeeDirectory().createEmployee("LA Family Support Admin", 0);
        UserAccount account3 = enterpriseLAFamilySupport.getUserAccountDirectory().createUserAccount("lafamilycareadmin@gmail.com", "Covid@2021", employee3, new AdminRole());

        Employee employee3a = DonorFamilySupportEnterpriseEmotional.getEmployeeDirectory().createEmployee("Pranoy", 0);
        Employee employee3b = DonorFamilySupportEnterpriseEmotional.getEmployeeDirectory().createEmployee("Kunal", 1);

        DonorFamilySupportEnterpriseEmotional.getUserAccountDirectory().createUserAccount("pranoy@gmail.com", "Covid@2021", "1234567890", "Time Sq", "Therapists", "M", true, 25, null, employee3a, new CounselorRole(), true);
        DonorFamilySupportEnterpriseEmotional.getUserAccountDirectory().createUserAccount("kunal@gmail.com", "Covid@2021", "0987654321", "New England", "Therapists", "M", true, 23, null, employee3b, new CounselorRole(), true);

        Employee employee3c = DonorFamilySupportEnterpriseLegal.getEmployeeDirectory().createEmployee("Vinay", 0);
        Employee employee3d = DonorFamilySupportEnterpriseLegal.getEmployeeDirectory().createEmployee("Nikkhil", 1);

        DonorFamilySupportEnterpriseLegal.getUserAccountDirectory().createUserAccount("vinay@gmail.com", "Covid@2021", "1234567890", "Trump St", "Advocate", "M", true, 25, null, employee3c, new LegalAdvisorRole(), true);
        DonorFamilySupportEnterpriseLegal.getUserAccountDirectory().createUserAccount("nikkhil@gmail.com", "Covid@2021", "0987654321", "Modi St", "Advocate", "M", true, 23, null, employee3c, new LegalAdvisorRole(), true);

        // Camp
        Enterprise enterpriseLAPublicService = network.getEnterpriseDirectory().createAndAddEnterprise("LA Public Service", Enterprise.EnterpriseType.AwarenessCamp);
        Organization AwarenessCampEnterpriseAwarness = enterpriseLAPublicService.getOrganizationDirectory().createOrganization(Organization.Type.AwarnessEventManagementOrganization, "Awarness Event", 0);
        Organization AwarenessCampEnterpriseHealthCamp = enterpriseLAPublicService.getOrganizationDirectory().createOrganization(Organization.Type.HealthCampOrganization, "Field Camp", 1);

        Employee employee4 = enterpriseLAPublicService.getEmployeeDirectory().createEmployee("LA Public Service Admin", 0);
        UserAccount account5 = enterpriseLAPublicService.getUserAccountDirectory().createUserAccount("lapublicadmin@gmail.com", "Covid@2021", employee4, new AdminRole());

        Employee employee4a = AwarenessCampEnterpriseAwarness.getEmployeeDirectory().createEmployee("Romita", 0);
        Employee employee4b = AwarenessCampEnterpriseAwarness.getEmployeeDirectory().createEmployee("Rahul", 1);

        AwarenessCampEnterpriseAwarness.getUserAccountDirectory().createUserAccount("romita@gmail.com", "Covid@2021", "1234567890", "Robin St", "Officer", "F", true, 25, null, employee4a, new EventOrganizer(), true);
        AwarenessCampEnterpriseAwarness.getUserAccountDirectory().createUserAccount("rahul@gmail.com", "Covid@2021", "0987654321", "Needham", "Officer", "M", true, 23, null, employee4b, new EventOrganizer(), true);

        Employee employee4c = AwarenessCampEnterpriseHealthCamp.getEmployeeDirectory().createEmployee("Vikash", 0);
        Employee employee4d = AwarenessCampEnterpriseHealthCamp.getEmployeeDirectory().createEmployee("Pooja", 1);

        AwarenessCampEnterpriseHealthCamp.getUserAccountDirectory().createUserAccount("vikash@gmail.com", "Covid@2021", "1234567890", "Charles St", "Officer", "M", true, 25, null, employee4c, new CampOrganizerRole(), true);
        AwarenessCampEnterpriseHealthCamp.getUserAccountDirectory().createUserAccount("pooja@gmail.com", "Covid@2021", "0987654321", "Newton", "Officer", "F", true, 23, null, employee4d, new CampOrganizerRole(), true);

        //have some employees 
        Faker faker = new Faker();
        ArrayList<String> bloodFroup = new ArrayList<String>();
        ArrayList<String> organs1 = new ArrayList<String>();
        ArrayList<String> organs2 = new ArrayList<String>();
        ArrayList<String> organs3 = new ArrayList<String>();
        ArrayList<String> organs4 = new ArrayList<String>();
        ArrayList<String> organs5 = new ArrayList<String>();

        java.util.List<String> organList = new ArrayList<String>();

        bloodFroup.add("B Positive");
        bloodFroup.add("B Positive");
        bloodFroup.add("B Negative");
        bloodFroup.add("A Positive");
        bloodFroup.add("A Negative");
        bloodFroup.add("O Positive");
        bloodFroup.add("O Negative");

        organs1.add("Heart");
        organs2.add("Lungs");
        organs3.add("Kidneys");
        organs4.add("Liver");
        organs5.add("Pancreas");

        ArrayList<ArrayList<String>> finalList = new ArrayList<>();
        finalList.add(organs1);
        finalList.add(organs2);
        finalList.add(organs3);
        finalList.add(organs4);
        finalList.add(organs5);

        for (int i = 1; i < 51; i++) {
            String name = faker.name().fullName();
            int age = faker.number().numberBetween(30, 51);
            String sex = faker.number().numberBetween(1, 2) == 1 ? "M" : "F";

            String bloodGroup = bloodFroup.get(faker.number().numberBetween(0, 5));

            String contactNum = faker.phoneNumber().cellPhone();
            String address = faker.address().fullAddress();
            String sign = name.toUpperCase();
            String emailAdd = faker.internet().emailAddress();
            String emergencyPOC = faker.name().fullName();
            String emergencyPOC_Num = faker.phoneNumber().cellPhone();
            boolean isOrganAvaiNow = faker.number().numberBetween(1, 2) == 1 ? true : false;
            java.util.List<String> organsToSend = new ArrayList<String>(finalList.get(faker.number().numberBetween(0, 4)));

            system.createDonor(name, age, sex, bloodGroup, contactNum, address, sign, emailAdd, emergencyPOC, emergencyPOC_Num, isOrganAvaiNow, organsToSend);

        }

        //create user account
        Employee employee = system.getEmployeeDirectory().createEmployee("sysadmin", 1);
        UserAccount ua = system.getUserAccountDirectory().createUserAccount("sysadmin", "sysadmin", employee, new SystemAdminRole());
        return system;
    }

    private static class List<T> {

        public List() {
        }
    }

}
