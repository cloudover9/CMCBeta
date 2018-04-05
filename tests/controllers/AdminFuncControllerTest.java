package controllers;
import java.util.ArrayList;

import org.junit.*;
import org.junit.Assert;
import entity.*;
import controller.*;


public class AdminFuncControllerTest {
	private AdminFuncController ad = new AdminFuncController();
	private DBController dbc = new DBController();
	private University u;
	private Account account;
	
	/**
	 * Inserts a test university and account into the database
	 */
	@Before
	public void init() {
		ArrayList<String> emphases = new ArrayList<String>();
		emphases.add("TestForRemoval");
		u = new University("Test", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, emphases);
		dbc.addNewUniversity(u);
		ad.addEmphases(u);
		
		account = new Admin("test", "password", 'Y', "first", "last");
		dbc.addAccount(account);
	}
	
	/**
	 * Deletes the test university and account from the database
	 */
	@After
	public void revert() {
		if(ad.getUniversity(u.getName()) instanceof University) {
			ad.deleteEmphases(u);
			dbc.deleteUniversity(u);
		}
		if(!account.getUsername().equals("DummyUser")) {
			dbc.deleteAccount(account);
		}
	}
	
	// Test delete(University univ) ===========================================================
	/**
	 * Tests delete method in AdminFuncController and
	 * checks if it works.
	 */
	@Test
	public void testDeleteUniversity_Works() {
		ad.delete(u);
		Assert.assertTrue(!(ad.getUniversity(u.getName()) instanceof University));
	}
	/**
	 * Tests delete method in AdminFuncController and
	 * checks for an exception.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDeleteUniversity_Fails() {
		ad.delete(new University("IDoNotExist", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>()));
	}
	
	// Test getUniversity(univName) ===========================================================
	/**
	 * Tests getUniversity method in AdminFuncController and
	 * checks if it works.
	 */
	@Test
	public void testGetUniversity_Works() {
		Assert.assertTrue(ad.getUniversity("Test") instanceof University);
	}
	/**
	 * Tests getUniversity method in AdminFuncController and
	 * checks if it fails.
	 */
	@Test
	public void testGetUniversity_Fails() {
		Assert.assertFalse(ad.getUniversity("IDoNotExist") instanceof University);
	}
	
	// Test saveAccountChanges(univ) ===========================================================
	/**
	 * Tests saveAccountChanges method in AdminFuncController and
	 * checks if it works for the first name.
	 */
	@Test
	public void testSaveAccountChanges_FirstName() {
		account.setFirstName("Sara");
		ad.saveAccountChanges(account);
		Assert.assertTrue(ad.getAccount("test").getFirstName().equals("Sara"));
	}
	/**
	 * Tests saveAccountChanges method in AdminFuncController and
	 * checks if it works for the last name.
	 */
	@Test
	public void testSaveAccountChanges_LastName() {
		account.setLastName("Laufers");
		ad.saveAccountChanges(account);
		Assert.assertTrue(ad.getAccount("test").getLastName().equals("Laufers"));
	}
	/**
	 * Tests saveAccountChanges method in AdminFuncController and
	 * checks if it works for the password.
	 */
	@Test
	public void testSaveAccountChanges_Password() {
		account.setPassword("drowssap");
		ad.saveAccountChanges(account);
		Assert.assertTrue(ad.getAccount("test").getPassword().equals("drowssap"));
	}
	/**
	 * Tests saveAccountChanges method in AdminFuncController and
	 * checks if it works for the type.
	 */
	@Test
	public void testSaveAccountChanges_Type() {
		account.setType('a');
		ad.saveAccountChanges(account);
		Assert.assertTrue(ad.getAccount("test").getType() == 'a');
	}
	/**
	 * Tests saveAccountChanges method in AdminFuncController and
	 * checks if it works for the active.
	 */
	@Test
	public void testSaveAccountChanges_Active() {
		account.setActive('N');
		ad.saveAccountChanges(account);
		Assert.assertTrue(ad.getAccount("test").getActive() == 'N');
	}
	// Test saveUnivChanges() ===========================================================
	/**
	 * Tests saveUnivChanges method in AdminFuncController and
	 * checks if it works for the state.
	 */
	@Test
	public void testSaveUnivChanges_State() {
		u.setState("MINNESOTA");
		ad.saveUnivChanges(u);
		Assert.assertTrue(ad.getUniversity("Test").getState().equals("MINNESOTA"));
	}
	/**
	 * Tests saveUnivChanges method in AdminFuncController and
	 * checks if it works for the location.
	 */
	@Test
	public void testSaveUnivChanges_Location() {
		u.setLocation("URBAN");
		ad.saveUnivChanges(u);
		Assert.assertTrue(ad.getUniversity("Test").getLocation().equals("URBAN"));
	}
	/**
	 * Tests saveUnivChanges method in AdminFuncController and
	 * checks if it works for the control.
	 */
	@Test
	public void testSaveUnivChanges_Control() {
		u.setControl("CITY");
		ad.saveUnivChanges(u);
		Assert.assertTrue(ad.getUniversity("Test").getControl().equals("CITY"));
	}
	/**
	 * Tests saveUnivChanges method in AdminFuncController and
	 * checks if it works for the students.
	 */
	@Test
	public void testSaveUnivChanges_Students() {
		u.setStudents(100);
		ad.saveUnivChanges(u);
		Assert.assertTrue(ad.getUniversity("Test").getStudents() == 100);
	}
	/**
	 * Tests saveUnivChanges method in AdminFuncController and
	 * checks if it works for the female percent.
	 */
	@Test
	public void testSaveUnivChanges_FemPerc() {
		u.setFemPerc(50);
		ad.saveUnivChanges(u);
		Assert.assertTrue(ad.getUniversity("Test").getFemPerc() == 50);
	}
	/**
	 * Tests saveUnivChanges method in AdminFuncController and
	 * checks if it works for the SAT verbal score.
	 */
	@Test
	public void testSaveUnivChanges_SATV() {
		u.setSatV(400);
		ad.saveUnivChanges(u);
		Assert.assertTrue(ad.getUniversity("Test").getSatV() == 400);
	}
	/**
	 * Tests saveUnivChanges method in AdminFuncController and
	 * checks if it works for the SAT math score.
	 */
	@Test
	public void testSaveUnivChanges_SATM() {
		u.setSatM(400);
		ad.saveUnivChanges(u);
		Assert.assertTrue(ad.getUniversity("Test").getSatM() == 400);
	}
	/**
	 * Tests saveUnivChanges method in AdminFuncController and
	 * checks if it works for the cost.
	 */
	@Test
	public void testSaveUnivChanges_Cost() {
		u.setCost(100);
		ad.saveUnivChanges(u);
		Assert.assertTrue(ad.getUniversity("Test").getCost() == 100);
	}
	/**
	 * Tests saveUnivChanges method in AdminFuncController and
	 * checks if it works for the financial aid percent.
	 */
	@Test
	public void testSaveUnivChanges_FinAidPerc() {
		u.setFinAidPerc(50);
		ad.saveUnivChanges(u);
		Assert.assertTrue(ad.getUniversity("Test").getFinAidPerc() == 50);
	}
	/**
	 * Tests saveUnivChanges method in AdminFuncController and
	 * checks if it works for the applicants.
	 */
	@Test
	public void testSaveUnivChanges_Applicants() {
		u.setApplicants(100);
		ad.saveUnivChanges(u);
		Assert.assertTrue(ad.getUniversity("Test").getApplicants() == 100);
	}
	/**
	 * Tests saveUnivChanges method in AdminFuncController and
	 * checks if it works for the admitted percent.
	 */
	@Test
	public void testSaveUnivChanges_Admitted() {
		u.setAdmitted(50);
		ad.saveUnivChanges(u);
		Assert.assertTrue(ad.getUniversity("Test").getAdmitted() == 50);
	}
	/**
	 * Tests saveUnivChanges method in AdminFuncController and
	 * checks if it works for the enrolled percent.
	 */
	@Test
	public void testSaveUnivChanges_Enrolled() {
		u.setEnrolled(50);
		ad.saveUnivChanges(u);
		Assert.assertTrue(ad.getUniversity("Test").getEnrolled() == 50);
	}
	/**
	 * Tests saveUnivChanges method in AdminFuncController and
	 * checks if it works for the academic scale.
	 */
	@Test
	public void testSaveUnivChanges_AcadScale() {
		u.setAcadScale(3);
		ad.saveUnivChanges(u);
		Assert.assertTrue(ad.getUniversity("Test").getAcadScale() == 3);
	}
	/**
	 * Tests saveUnivChanges method in AdminFuncController and
	 * checks if it works for the social scale.
	 */
	@Test
	public void testSaveUnivChanges_SocScale() {
		u.setSocScale(3);
		ad.saveUnivChanges(u);
		Assert.assertTrue(ad.getUniversity("Test").getSocScale() == 3);
	}
	/**
	 * Tests saveUnivChanges method in AdminFuncController and
	 * checks if it works for the quality Scale.
	 */
	@Test
	public void testSaveUnivChanges_QualScale() {
		u.setQualScale(3);
		ad.saveUnivChanges(u);
		Assert.assertTrue(ad.getUniversity("Test").getQualScale() == 3);
	}
	/**
	 * Tests saveUnivChanges method in AdminFuncController and
	 * checks if it works for the emphases.
	 */
	@Test
	public void testSaveUnivChanges_Emphases() {
		u.addEmphases("ART");
		ad.saveUnivChanges(u);
		Assert.assertTrue(ad.getUniversity("Test").getEmphases().contains("ART"));
	}
	
	// Test getAccount(accountName) ===========================================================
	/**
	 * Tests getAccount method in AdminFuncController and
	 * checks if it works.
	 */
	@Test
	public void testGetAccount_UserExists() {
		Assert.assertTrue(!(ad.getAccount("test").getUsername().equals("DummyUser")));
	}
	/**
	 * Tests getAccount method in AdminFuncController and
	 * checks if it fails.
	 */
	@Test
	public void testGetAccount_UserDoesNotExist() {
		Assert.assertTrue(ad.getAccount("IDoNotExist").getUsername().equals("DummyUser"));
	}
	
	// Test addEmphases(univ) ===========================================================
	/**
	 * Tests addEmphases method in AdminFuncController and
	 * checks if it works.
	 */
	@Test
	public void testAddEmphases_Works() {
		u.addEmphases("Another");
		ad.addEmphases(u);
		Assert.assertTrue(ad.getUniversity("Test").getEmphases().contains("Another"));
	}
	/**
	 * Tests addEmphases method in AdminFuncController and
	 * catches and exception for invalid university.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAddEmphases_FailsInvalidUniversity() {
		ad.addEmphases(new University("IDoNotExist", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>()));
	}
	
	// Test deleteEmphases(univ) ===========================================================
	/**
	 * Tests deleteEmphases method in AdminFuncController and
	 * checks if it works.
	 */
	@Test
	public void testDeleteEmphases_Works() {
		u.removeEmphases("TestForRemoval");
		ad.deleteEmphases(u);
		Assert.assertTrue(ad.getUniversity("Test").getEmphases().contains("TestForRemoval"));
	}
	/**
	 * Tests deleteEmphases method in AdminFuncController and
	 * catches and exception for invalid university.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDeleteEmphases_FailsInvalidUniversity() {
		ad.deleteEmphases(new University("IDoNotExist", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>()));
	}
	
	// ===================================================================================================
	//                                      WHITE-BOX TESTING 
	// ===================================================================================================
	/**
	 * Tests homepage method in AdminFuncController and
	 * checks if it works for viewing universities.
	 */
	@Test
	public void testHomepage_ViewUniversities() {
		String message = ad.homepage("1");
		Assert.assertTrue(message != null);
		Assert.assertEquals("*** Going to Manage_Universities page. ***", ad.homepage("1"));
	}
	/**
	 * Tests homepage method in AdminFuncController and
	 * checks if it works for viewing users.
	 */
	@Test
	public void testHomepage_ViewUsers() {
		String message = ad.homepage("2");
		Assert.assertTrue(message != null);
		Assert.assertEquals("*** Going to Manage_Users page. ***", ad.homepage("2"));
		//System.out.println("testHomepage_ViewUsers: " + message);
	}
	/**
	 * Tests homepage method in AdminFuncController and
	 * checks if it works for logging out.
	 */
	@Test
	public void testHomepage_LogOut() {
		String message = ad.homepage("3");
		Assert.assertTrue(message != null);
		Assert.assertEquals("*** Logging out. ***", ad.homepage("3"));
		//System.out.println("testHomepage_LogOut: " + message);
	}
	/**
	 * Tests homepage method in AdminFuncController and
	 * catches an exception for an invalid prompt.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testHomepage_FailsInvalidPrompt() {
		//System.out.println("testHomepage_FailsInvalidPrompt: Now throwing exception");
		String message = ad.homepage("4");
		Assert.assertFalse(message != null);
	}
	
	/**
	 * Test method for editUniversity in AdminFuncController
	 */
	@Test
	public void testEditUniversity_Works() {
		ArrayList<String> emphases = new ArrayList<String>();
		emphases.add("ART");
		emphases.add("BIOLOGY");
		ad.editUniversity("Test", "Minnesota", "URBAN", "CITY", 100, 50, 400, 400, 100, 50, 100, 50, 50, 3, 3, 3, emphases);
		Assert.assertTrue(ad.getUniversity("Test").getState().equals("Minnesota"));
		Assert.assertTrue(ad.getUniversity("Test").getLocation().equals("URBAN"));
		Assert.assertTrue(ad.getUniversity("Test").getControl().equals("CITY"));
		Assert.assertTrue(ad.getUniversity("Test").getStudents() == 100);
		Assert.assertTrue(ad.getUniversity("Test").getFemPerc() == 50);
		Assert.assertTrue(ad.getUniversity("Test").getSatV() == 400);
		Assert.assertTrue(ad.getUniversity("Test").getSatM() == 400);
		Assert.assertTrue(ad.getUniversity("Test").getCost() == 100);
		Assert.assertTrue(ad.getUniversity("Test").getFinAidPerc() == 50);
		Assert.assertTrue(ad.getUniversity("Test").getApplicants() == 100);
		Assert.assertTrue(ad.getUniversity("Test").getAdmitted() == 50);
		Assert.assertTrue(ad.getUniversity("Test").getEnrolled() == 50);
		Assert.assertTrue(ad.getUniversity("Test").getAcadScale() == 3);
		Assert.assertTrue(ad.getUniversity("Test").getSocScale() == 3);
		Assert.assertTrue(ad.getUniversity("Test").getQualScale() == 3);
		Assert.assertTrue(ad.getUniversity("Test").getEmphases().contains("ART"));
		Assert.assertTrue(ad.getUniversity("Test").getEmphases().contains("BIOLOGY"));
	}
	
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an empty state input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsEmptyState() {
		ad.editUniversity("Test", "", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an invalid location input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsInvalidLocation() {
		ad.editUniversity("Test", "0", "invalid", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an invalid control input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsInvalidControl() {
		ad.editUniversity("Test", "0", "", "invalid", 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for a negative number of students input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsNegativeStudents() {
		ad.editUniversity("Test", "0", "", "", -1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (under) female percentage input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeFemalePercentage_Case1() {
		ad.editUniversity("Test", "0", "", "", 0, -1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (over) female percentage input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeFemalePercentage_Case2() {
		ad.editUniversity("Test", "0", "", "", 0, 101, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (under) SAT verbal score input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeSATV_Case1() {
		ad.editUniversity("Test", "0", "", "", 0, 0, -1, 0, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (over) SAT verbal score input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeSATV_Case2() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 801, 0, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (under) SAT math score input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeSATM_Case1() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, -1, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (over) SAT math score input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeSATM_Case2() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 801, 0, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for a negative number of cost input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsNegativeCost() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, -1, 0, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (under) financial aid percentage input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeFinancialAidPercentage_Case1() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, -1, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (over) financial aid percentage input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeFinancialAidPercentage_Case2() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 101, 0, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for a negative number of applicants input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsNegativeApplicants() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 0, -1, 0, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (under) admitted percentage input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeAdmitted_Case1() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 0, 0, -1, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (over) admitted percentage input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeAdmitted_Case2() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 101, 0, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (under) enrolled percentage input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeEnrolled_Case1() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (over) enrolled percentage input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeEnrolled_Case2() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 101, 1, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (under) academic scale input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeAcademicScale_Case1() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (over) academic scale input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeAcademicScale_Case2() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 1, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (under) social scale input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeSocialScale_Case1() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (over) social scale input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeSocialScale_Case2() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 6, 1, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (under) quality scale input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeQualityScale_Case1() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an out of range (over) quality scale input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsRangeQualityScale_Case2() {
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 6, new ArrayList<String>());
	}
	/**
	 * Test method for editUniversity in AdminFuncController.
	 * Catches an Exception for an over-sized (above 5) array of emphases input
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEditUniversity_FailsArraySize() {
		ArrayList<String> emphases = new ArrayList<String>();
		emphases.add("1");
		emphases.add("2");
		emphases.add("3");
		emphases.add("4");
		emphases.add("5");
		emphases.add("6");
		ad.editUniversity("Test", "0", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, emphases);
	}
	@Test
	public void testaddAccoountForUser() {
		ad.addAccount("Test", "Test", "Test", "Test", "u");
		Assert.assertTrue("User account was correctly added", dbc.getUser("Test").getFirstName().equals("Test"));
		dbc.deleteAccount(dbc.getUser("Test"));
	}
	@Test
	public void testaddAccoountForAdmin() {
		ad.addAccount("Test", "Test", "Test", "Test", "a");
		Assert.assertTrue("Admin account was correctly added", dbc.getUser("Test").getFirstName().equals("Test"));
		dbc.deleteAccount(dbc.getUser("Test"));
	}
	@Test(expected = IllegalArgumentException.class)
	public void testaddAccoountFailsForRepeatedUsername() {
		ad.addAccount("juser", "Test", "Test", "Test", "u");
	}
	@Test(expected = IllegalArgumentException.class)
	public void testaddAccoountFailsForInvalidType() {
		ad.addAccount("Test", "Test", "Test", "Test", "q");
	}
			
}
