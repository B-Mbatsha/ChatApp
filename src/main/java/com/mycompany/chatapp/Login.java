/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.io.BufferedWriter;

/*A basic tool for writing character
based data to files (text files)*/
import java.io.FileWriter;

/*Class allows code to recognize and handle 
        Specific Input and output errors*/
import java.io.IOException;

public class Login {
        
    // Variable declarations used to store the user's details.
    //This is where the user input shall be saved.
    //==================
    String Username;
    String password;
    String Phonenumber;
   
    //=========================

    public boolean checkUserName (String username){
        
        //username.contains("_") checks for underscores
        // username.length() <=5 ensures or checks if characters fall under range check
        
        
        //returns true if both conditions are true 
        return username.contains("_") && username.length() <=5;
        
    }
    
    //Checks password complexity
    public boolean checkPasswordComplexity(String password){
        
    //Checking if password validation has 
    boolean hasSpecial = false;
    boolean hasNumber = false;
    boolean hasCapital = false;
    
    //Looping through each character in the loop
    for (int i = 0; i < password.length(); i++){
       char c = password.charAt(i);//gets the current character
    
     if (Character.isUpperCase(c)){ // checks if c has an upperacse letter
        hasCapital = true;
        }else if (Character.isDigit(c)){ // checks if c has a digit
            hasNumber = true;
        }else if(!Character.isLetterOrDigit(c)){ //checks if the character is neither a letter or a digit
          hasSpecial = true;  
        }
      }
     return password.length() >= 8 && hasCapital && hasNumber && hasSpecial;
    }
    
    //Validates if a South African is entered
    public boolean checkCellPhoneNumber(String phone){
     return phone.startsWith("+27") && phone.length() <= 12;
    }
    
    //Registers the user by prompting the user to enter thier information 
   public String registerUser(String Username, String password, String Phonenumber){
     if(!checkUserName(Username)){//checks if a user name is invalid or missing
      return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters inlength";   
     }
     
     //Validates PasswordComplexity 
     if(!checkPasswordComplexity(password)){//checks if a password in invalid or missing
         return"Password is not correctly formatted; please ensure that the password contains at least eight characters,a capital letter, a number, and a special character";
     }
     
     //Validate PhoneNumber
     if (!checkCellPhoneNumber(Phonenumber)){//checks if a phonenumber is invalid or missing
       return"Cell phone number incorrectly formatted or does not contain international code";  
     }
     
     //Used to assign a value to a property of the current object 
     this.Username = Username;
     this.password = password;
     this.Phonenumber = Phonenumber;
     
      //Save user data into file (overwrites existing data)
      try (BufferedWriter writer = new BufferedWriter(new FileWriter("user.txt"))) {
          writer.write(Username+","+password + ","+ Phonenumber);
      } catch (IOException e) {
          return "Error saving user :";
      }
     
     return"User registered successfully";
   }
   
   //returns username and password if they are the same 
   public boolean loginUser(String Username, String password){
       return this.Username.equals(Username) && this.password.equals(password);
   }
   
   //Return login status message
   public String returnLoginStatus(boolean sucess){
       if (sucess){
           return "Welcome" + Username + "back";
       }else{
           return"Username or password incorrect, please try agian.";
       }
   }
}  


