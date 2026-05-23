package com.mycompany.chatapp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author One eyed King
 */
import java.util.Random;
import java.util.Scanner;
import org.json.JSONObject;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException; 


public class Message {
    
    private String messageID;       
    private int    messageNumber;  
    private String recipientphone;       
    private String messageText;  
    private String messageHash;
    int iTotalMessagessent;
       Scanner input = new Scanner(System.in);
       JSONObject obj = new JSONObject(); 
       
  
    
     public boolean checkMessageID(String MessageID) {
         Random random = new Random();
         
         MessageID = String.valueOf(random.nextInt(10));
         
      return MessageID != null && MessageID.length()== 10;
     }
     
     public String checkRecipientCell(String phonenumber) {
         
         String regex = "^(\\+27|0)[6-8]\\d{8}$";
         this.recipientphone = phonenumber;
    if(recipientphone.matches(regex)){
        return "Cell phone number successfully captured";
        
    }else
        return "Cell phone number is incorrectly formatted or does not contain an international code.Please correct the number and try again";
     }
     public String createMessageHash(){
         
    String ID = messageID.substring(0, 2);  
    String[] words = messageText.split(" "); 
    String firstWord = words[0];              
    String lastWord  = words[words.length - 1]; 
    String hash = ""; 
    return hash.toUpperCase();  
     }
     public String sentMessage(String message){
         messageText = input.nextLine();
         if(messageText!= null&&messageText.length()>250){
         int overCharacters = messageText.length() - 250;
         System.out.println("Message exceeds 250 characters by"+overCharacters+"please reduce the size.");
     }else{  
         System.out.println("What would you like to do with this message?");
         System.out.println("1) Send Message");
         System.out.println("2) Disregard ");
         System.out.println("3) Store Message to send later");
         iTotalMessagessent = 0;
         int Choice = input.nextInt();
         input.nextLine();
         switch(Choice){
             case 1:
         System.out.println("Message has been sent");
         iTotalMessagessent  = iTotalMessagessent +1;
         break;
            case 2:
                System.out.println(messageText);
                System.out.println("Press 0 to Discard message");
                switch(input.nextInt()){
                    case 0:
                        messageText = null;
                        System.out.println("Message has been deleted");
                        break;
                    case 3:
                        storeMessages();
                        System.out.println("Message successfully stored");
                        break;
                    default :
                        System.out.println("Feature not available");
                }    
         }
         
         }
         return "Invalid option please retry";
     }
     public String printMessages(){
         
        return messageText;
     }
     public int returnTotalMessages(){
      return iTotalMessagessent;
     }
     public String storeMessages(){
         obj.put("messageID", messageID); 
           obj.put("recipient", recipientphone); 
              obj.put("message",  messageText); 
        try (FileWriter fw = new FileWriter("messages.json", true)) { 
            fw.write(obj.toString());    
     } catch (IOException e){
         e.printStackTrace();
         
     }  
        return "Message successfully stored";
     }
}
