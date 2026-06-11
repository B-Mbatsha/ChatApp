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
        
        //Checks if the details entered by the user match
        boolean loggedIn = login.loginUser();
        
        //Prints or displays the appropriate message 
        String loginMessage = login.returnLoginStatus(loggedIn);
        System.out.println(loginMessage);
        
        
        // --- Part 2: Message(if user is logged in) ---
         if (loggedIn) {
            System.out.println("\nWelcome to ChatApp!");
            
            Message message = new Message();{
            boolean running = true;
 
            while (running) {
                System.out.println("\n");
                System.out.println("=================================");
                System.out.println(message.getNumberOFStoredMessages());
               
                // Step 2.1: Display the three-option menu
                System.out.println("        CHATAPP MENU          ");
                System.out.println("==============================");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit"); 
                System.out.println("4) Stored Messages");
                System.out.println("==============================");
                System.out.print("Enter your choice: ");
 
                // Read the user's menu choice inside the loop
                int choice = 0;
                choice = input.nextInt();
                input.nextLine();
                switch (choice) {

                    case 1:
                        // Ask how many messages the user wants to send
                        System.out.println("How many messages would you like to send?");
                        int numberOfMessages = 0;
                        numberOfMessages = input.nextInt();
                        input.nextLine();
                        if(numberOfMessages<=0){
                            System.out.println("Invalid input try again");
                        }else{
                        // For loop — runs once per message
                        String recipientPhone = "";
                            System.out.println("Enter the recipient's phone number: ");
                                recipientPhone = input.nextLine();
                                String cellCheck = message.checkRecipientCell(recipientPhone);
                                System.out.println(cellCheck);
                        for (int i = 0; i < numberOfMessages; i++) {
                            System.out.println("\n--- Message " + (i + 1) + " of " + numberOfMessages + " ---");
                            while (true) {
                                   System.out.println("Enter your message: ");
                            String MessageText = input.nextLine();
                            String result = message.sentMessages(MessageText, recipientPhone);
                            System.out.println(result);
                                if(cellCheck.equals("Cell phone number successfully captured")) {
                                    break; // valid — move on
                                }
                                
                                // invalid — loop back and ask again
                            }
                         
                        }
                        }
                        System.out.println("Total messages sent: " + message.returnTotalMessages());

                        break;
                        case 2:
                        System.out.println("Coming Soon.");
                        break;
                    case 3:
                        running = false;
                        System.out.println("Thank you for using ChatApp. Goodbye!");
                        break;
                    case 4:
                        
                        System.out.println("a)Display all stored messages");
                        System.out.println("b)Display longest message");
                        System.out.println("c)Search by message ID");
                        System.out.println("d)Search by recipient");
                        System.out.println("e)Delete by message hash");
                        System.out.println("f)Dislpay full report");
                         String Choice = input.nextLine();
                        if(Choice.equalsIgnoreCase("a")){
                           message.loadStoredMessages();
                        }else if(Choice.equalsIgnoreCase("b")){
                            System.out.print(message.LongestMessage());
                        }else if(Choice.equalsIgnoreCase("c")){
                            System.out.println("Enter your message ID");
                            String Search = input.nextLine();
                            System.out.println(message.SearchbyMessageID(Search));
                        }else if(Choice.equalsIgnoreCase("d")){
                           System.out.println("Please enter the recipients phone number");
                           String recipientSearch = input.nextLine();
                           System.out.println(message.SearchbyRecipient(recipientSearch));
                        }else if(Choice.equalsIgnoreCase("e")){
                        System.out.println("Please enter message Hash");
                        String MessageHash = input.nextLine();
                       System.out.println(message.DeleteByMessageHash(MessageHash));   
                        }else if(Choice.equalsIgnoreCase("f")){
                         System.out.println(message.MessageReport());
                        }
                        break;
                   default:
                        System.out.println("Invalid option. Please enter 1, 2, 3, or 4.");
                }
            }
 
        }
        }else {
            System.out.println("Incorrect login info. Please retry.");
         } 
    }input.close();
        
        
}
}

