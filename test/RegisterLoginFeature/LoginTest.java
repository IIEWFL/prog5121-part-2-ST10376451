/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
 /*

 */
package RegisterLoginFeature;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author lab_services_student
 */
public class LoginTest {

    public LoginTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testUsernameCorrectlyFormatted() {
        Login.UserAccount user = new Login.UserAccount("kyl_1", "Password@123", "John", "Doe");
        String expectedMessage = "Welcome John Doe. great to see you.";
        assertEquals(expectedMessage, user.returnLoginStatus(true));
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login.UserAccount user = new Login.UserAccount("kyle!!!!!!!", "Password@123", "John", "Doe");
        String expectedMessage = "Username is incorrectly formatted. Please ensure it contains an underscore and is no more than 5 characters long.";
        assertEquals(expectedMessage, user.registerUser());
    }

    @Test
    public void testPasswordMeetsComplexityRequirements() {
        Login.UserAccount user = new Login.UserAccount("kyl_1", "Ch&&sec@ke99!", "John", "Doe");
        String expectedMessage = "Password successfully captured";
        assertEquals(expectedMessage, user.registerUser());
    }

    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {
        Login.UserAccount user = new Login.UserAccount("kyl_1", "password", "John", "Doe");
        String expectedMessage = "Password does not meet complexity requirements. Please ensure it is at least 8 characters long, contains a capital letter, and a number.";
        assertEquals(expectedMessage, user.registerUser());
    }

    @Test
    public void testLoginUserSuccessful() {
        Login.UserAccount user = new Login.UserAccount("kyl_1", "Password@123", "John", "Doe");

        // Print debugging information
        System.out.println("Is registered before registration: " + user.isRegistered);

        // Register the user
        user.registerUser();

        // Print debugging information after registration
        System.out.println("Is registered after registration: " + user.isRegistered);

        // Attempt to log in with correct username and password
        assertTrue(user.loginUser("kyl_1", "Password@123"));
    }

    @Test
    public void testLoginUserFailed() {
        Login.UserAccount user = new Login.UserAccount("kyl_1", "Password123", "John", "Doe");
        assertFalse(user.loginUser("kyl_1", "wrongpassword"));
    }

    @Test
    public void testReturnLoginStatusSuccessful() {
        Login.UserAccount user = new Login.UserAccount("kyl_1", "Password@123", "John", "Doe");
        assertEquals("Welcome John Doe. great to see you.", user.returnLoginStatus(true));
    }

    @Test
    public void testReturnLoginStatusFailed() {
        Login.UserAccount user = new Login.UserAccount("kyl_1", "Password123", "John", "Doe");
        assertEquals("Username or password incorrect, please try again.", user.returnLoginStatus(false));
    }
}
