/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Scanner;

public class Mainapp {
    public static void main(String[]args){
        
        //Scanner method allows the user to enter thier information
        Scanner input = new Scanner(System.in);
        
        // An object useed for the login class so we call it's methods
        Login login = new Login();{
        
        //Output dislpayed as a heading for user registration
        System.out.println("=== USER REGISTRATION ===");
        
        //Promts user to enter their username
        System.out.println("Enter a username: ");
        String Username = input.nextLine();
        
        //Prompts the user to enter thier password
        System.out.println("Enter a Password: ");
        String password = input.nextLine();
        
        //Promts the user to enter their phone number
        System.out.println("Enter your South African phone number (+27....): ");
        String Phonenumber = input.nextLine();
        
        // Capatures user input for username,password and phoneNumber 
        String response = login.registerUser(Username, password, Phonenumber);
        
        //Dislays the captured user input
        System.out.println(response);
    
        //User login
        System.out.println("\n ===== USER LOGIN =====");
        
        //Promts user to enter their username
        System.out.print("Enter your username");
        String loginUsername = input.nextLine();
        
        //Prompts the user to enter thier password
        System.out.println("Enter your password : ");
        String loginpassword = input.nextLine();
        
        
        //Checks if the details entered by the user match
        boolean loggedIn = login.loginUser(loginUsername, loginpassword);
        
        //Prints or displays the appropriate message 
        String loginMessage = login.returnLoginStatus(loggedIn);
        System.out.println(loginMessage);
        
        
        // --- Part 2: Message(if user is logged in) ---
        
        
         if(loggedIn){
         System.out.println("Welcome to ChatApp");
                 Message MessagesStatus = new Message();

        Boolean Quit = false;
        
        while (Quit) {
            System.out.println("Please enter the phonenumber of the recipient");
             String Recipientsphonenumber = input.nextLine(); 
             String checkcell = MessagesStatus.checkRecipientCell(Recipientsphonenumber);
             System.out.println(checkcell);
             if(!checkcell.equals("Cell phone number seccessfully captured")){
                 
             }
            System.out.println("Please enter your message");
            String Message = input.nextLine();
            
            System.out.println("1) Send Message");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            
            int Choice = 0;
            
                switch(Choice){
                    case 1:
                        System.out.println("How many messages do you want to send");
                        int Numberofmessages = input.nextInt();
                       for(int i=0;i<Numberofmessages;i++){
                int MessageNumber = i+1;
                System.out.println(MessageNumber+")"+Message);
                System.out.println("Message sent to"+Recipientsphonenumber);
                break;
                }
                    case 2:
                        System.out.println("Coming soon.(Feature not built yet)");
                    break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Option not available please retry");
        }  
        }
    }else{
             System.out.println("Incorrect login info please retry");
         }  
        
    }
    
}
}

