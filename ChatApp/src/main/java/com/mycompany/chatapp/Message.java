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
    int iTotalMessagessent =0;
       Scanner input = new Scanner(System.in);
       
  
    
     public boolean checkMessageID(String MessageID) {
         Random random = new Random();
         this.messageID = "";
         for(int  i=0; i< 10;i++){
           int ID = random.nextInt(10);
           this.messageID += String.valueOf(ID);
         }
      return this.messageID != null && this.messageID.length()== 10;
     }
     
     public String checkRecipientCell(String phonenumber) {
         
         String regex = "^(\\+27|0)[6-8]\\d{8}$";
         this.recipientphone = phonenumber;
    if(recipientphone != null && recipientphone.matches(regex)){
        return "Cell phone number successfully captured";
        
    }else
        return "Cell phone number is incorrectly formatted or does not contain an international code.Please correct the number and try again";
     }
     public String createMessageHash(){
         checkMessageID("");
          if (messageID == null || messageID.length() < 2) {
            return "Invalid ID";
        }
        if (messageText == null || messageText.trim().isEmpty()) {
            return "NO MESSAGE";
        }
         
    String ID = messageID.substring(0, 2);  
    String[] words = messageText.split("\\s+"); 
    String firstWord = words[0];              
    String lastWord  = words[words.length - 1]; 
    String hash = ID + ":"+ firstWord + ":" +lastWord;
    this.messageHash = hash.toUpperCase();
    return this.messageHash;  
     }
    public String Messagelength (String message,String recipient){            
         this.messageText = message;
         this.recipientphone = recipient;
     
         if(messageText!= null&&messageText.isEmpty()){
         return "Message cannot be empty.";
        }
    
         if(messageText.length()>250){  
             int OverCharacters = messageText.length() - 250;
             return "Message exceeds 250 characters by"+OverCharacters+"please reduce the size";
            }
         return "Message Sent";
    }
 public String sentMessage(String message,String recipient){
             checkMessageID("");
             createMessageHash();
             
         System.out.println("\nWhat would you like to do with this message?");
         System.out.println("1) Send Message");
         System.out.println("2) Disregard ");
         System.out.println("3) Store Message to send later");
         int Choice = 0;
         Choice = input.nextInt();
         input.nextLine();
         switch(Choice){
             case 1:
             iTotalMessagessent++;

         System.out.println("MessageID:"+messageID);
         System.out.println("Message Hash:"+messageHash);
         System.out.println("RecipientNumber:"+recipientphone);
         System.out.println("Message:"+messageText);
         return "Message successfully sent.";
         
            case 2:
                System.out.println(messageText);
                System.out.println("Press 0 to Discard message");
                int Delete = input.nextInt();
                        if(Delete==0){
                        this.messageText = null;
                        System.out.println("Message has been deleted");
                        }else { 
                        System.out.println("Invalid option press zero to delete message");
                        }
                        break;
                    case 3:
                        String Result = storeMessages();
                         System.out.println("\n--- Message Details ---");
                         System.out.println("Message ID   : " + messageID);
                         System.out.println("Message Hash : " + messageHash);
                         System.out.println("Recipient    : " + recipientphone);
                         System.out.println("Message      : " + messageText);
                        return "Message successfully stored";
                    default :
                        return"Feature not available";
                }
         return "Invalid option please retry";
         }
     public String sentMessage(String message){
       return sentMessage(message,this.recipientphone != null ? this.recipientphone : "unknown"); 
     }    

     public String printMessages(){
         
        return messageText;
     }
     public int returnTotalMessages(){
      return iTotalMessagessent;
     }
     public String storeMessages(){
         JSONObject obj = new JSONObject(); 
       
         obj.put("messageID", messageID); 
           obj.put("recipient", recipientphone); 
              obj.put("message",  messageText); 
        try (FileWriter fw = new FileWriter("messages.json", true)) { 
            fw.write(obj.toString()+ System.lineSeparator());    
     } catch (IOException e){
         e.printStackTrace();
         return"Failed to store message";
     }  
        return "Message successfully stored";
     }
    public String getMessageID(){ 
        return messageID; 
    }
    public String getMessageHash() {
        return messageHash;
    }
    public String getMessageText() { 
        return messageText;
    }

}
