/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author One eyed King
 */
/*java import which allows the @test annotation which is used to
identify methods and tests them using the junit engine*/
import org.junit.jupiter.api.Test;

//Staitc import which allows assertion methods to be called directly
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    
    Login login = new Login();
    
    //Tests if a valid username is entered
    @Test
    public void testvalidUsername (){
        assertTrue(login.checkUserName("kyl_1"));
    }
    
    //Tests if an invalid username is entered and wheather it has an underscore
    @Test
    public void testInvalidUsername_noUnderscore(){  
     assertFalse(login.checkUserName("kyle!!!!!!!"));
    }
    
    //Tests if an invalid username is entered and if it's too long
    @Test
    public void testInvalidUsername_TooLong(){
        assertFalse(login.checkUserName("kyle1111111"));
    }
    
    //Tests if a valid password is entered
    @Test
    public void testvalidPassword() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99"));
    }
    
    //Tests if a valid password is entered and if it is too short
    @Test
    public void testInvalidPassword_TooShort(){ 
        assertFalse(login.checkPasswordComplexity("Password"));
    }
    
    //
    @Test
    public void testInvalidPassword_DoesNotHaveSpecialCharacters(){
        assertFalse(login.checkPasswordComplexity("password"));
    }
    
    //
    @Test
    public void testvalidPhoneNumber(){
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }
    
    //
    @Test
    public void testInvalidPhoneNumber_DoesNotHaveSouthAfricanCode(){
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
    
    //
    @Test
    public void testInvalidPhoneNumber_TooShort(){
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
    public void testLoginSuccess(){
        //First register user
        login.registerUser(("kyl_1"), "Ch&&secke99","+27838968976");
        
        //Then attempt login with correct details
        boolean result = login.loginUser("kyl_1", "Ch&&sec@ke99");
        
        assertTrue(result);
            
    }
    @Test
    public void testLoginFail () {
        // First register a user
        login.registerUser(("kyl_1"),"Ch&&sec@ke99","+27838968976");
        
        boolean result = login.loginUser("kyl_1", "Wrong Pass1!");
        
        assertFalse(result);
                
    }
  
}
