
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 */
package RegisterLoginFeature;

import java.util.Scanner;

public class Login {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username (must contain an underscore and be no more than 5 characters):");
        String username = scanner.nextLine();

        System.out.println("Enter password (must be at least 8 characters long, contain a capital letter, a number, and a special character):");
        String password = scanner.nextLine();

        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();
        
        
// I modified the code I found on Stack Overflow
// https://stackoverflow.com/questions/47703302/java-jdbc-user-registration-login
// Shadow
//https://stackoverflow.com/users/5389997/shadow

        UserAccount user = new UserAccount(username, password, firstName, lastName);

        if (user.registerUser().equals("User registered successfully!")) {
            System.out.println("Account created successfully!");
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);
        } else {
            System.out.println("Account creation failed.");
        }

        if (user.loginUser(username, password)) {
            System.out.println(user.returnLoginStatus(true));
        } else {
            System.out.println(user.returnLoginStatus(false));
        }
    }

    public static class UserAccount {

        private String username;
        private String password;
        public String firstName;
        public String lastName;
        public boolean isRegistered;

        public UserAccount(String username, String password, String firstName, String lastName) {
            this.username = username;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
            this.isRegistered = false;
        }

        public boolean checkUserName() {
            if (username.contains("_") && username.length() <= 5) {
                return true;
            } else {

                return false;
            }
        }

        public boolean checkPasswordComplexity() {
            boolean hasValidLength = password.length() >= 8;
            boolean hasUpperCase = false;
            boolean hasNumber = false;
            boolean hasSpecialChar = false;

            String specialChars = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

            for (char c : password.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    hasUpperCase = true;
                } else if (Character.isDigit(c)) {
                    hasNumber = true;
                } else if (specialChars.indexOf(c) != -1) {
                    hasSpecialChar = true;
                }
            }

            return hasValidLength && hasUpperCase && hasNumber && hasSpecialChar;
        }

        public String registerUser() {
            if (!checkUserName()) {
                return "Username is incorrectly formatted. Please ensure it contains an underscore and is no more than 5 characters long.";
            }

            if (!checkPasswordComplexity()) {
                return "Password does not meet complexity requirements. Please ensure it is at least 8 characters long, contains a capital letter, and a number.";
            }
            this.isRegistered = true;
            if (checkPasswordComplexity()) {
                return "Password successfully captured";
            }

            return "User registered successfully!";
        }

        public boolean loginUser(String enteredUsername, String enteredPassword) {
            if (isRegistered && enteredUsername.equals(username) && enteredPassword.equals(password)) {
                return true;
            } else {
                return false;
            }
        }

        public String returnLoginStatus(boolean isLoggedIn) {
            if (isLoggedIn) {
                return "Welcome " + firstName + " " + lastName + ". great to see you.";
            } else {
                return "Username or password incorrect, please try again.";
            }
        }
    }
}
