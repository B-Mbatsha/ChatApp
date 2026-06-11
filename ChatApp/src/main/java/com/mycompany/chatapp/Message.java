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
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import java.io.File;


public class Message {
    
    private String messageID;       
    private int    messageNumber;  
    private String recipientphone;       
    private String messageText;  
    private String messageHash;
    int iTotalMessagessent =0;
    int StoredMessages =0;
    public static List<String> sentMessages = new ArrayList<>(); 
    public static List<String> disregardedMessages = new ArrayList<>(); 
    public static List<String> storedMessages = new ArrayList<>(); 
    public static List<String> messageHashes = new ArrayList<>(); 
    public static List<String> messageIDs = new ArrayList<>(); 
    public static List<String> RecipientPhonenumber = new ArrayList<>();
       Scanner input = new Scanner(System.in);
  
  
    
     public boolean checkMessageID() {
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
        public String sentMessages(String message,String recipient){
            this.messageText = message;
            this.recipientphone = recipient;
             checkMessageID();
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
             RecipientPhonenumber.add(recipientphone);
             sentMessages.add(messageText);
             messageHashes.add(messageHash);
             messageIDs.add(messageID);
         System.out.println("MessageID:"+messageID);
         System.out.println("Message Hash:"+messageHash);
         System.out.println("RecipientNumber:"+recipientphone);
         System.out.println("Message:"+messageText);
         return "Message successfully sent.";
         
            case 2:
                disregardedMessages.add(messageText);
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
                         StoredMessages++;
                         sentMessages.add(messageText);
                         RecipientPhonenumber.add(recipientphone);
                         messageIDs.add(messageID);
                         messageHashes.add(messageHash);
                         storeMessages();
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
      
  
     public String printMessages(){
         
        return messageText;
     }
     public int returnTotalMessages(){
      return iTotalMessagessent;
     }
     public String storeMessages(){      
        JSONObject obj = new JSONObject(); 
         obj.put("messageID", messageID);
          obj.put("message",  messageText);
           obj.put("recipient", recipientphone); 
          
        try (FileWriter fw = new FileWriter("messages.json",true)) { 
            fw.write(obj.toString() +System.lineSeparator());
            }catch (IOException e){
         e.printStackTrace();
         return"Failed to store message";
         }
        return "Message successfully stored";  
     }
     
     
     public static void loadStoredMessages(){
         int messagecount = 0;
         try{
            List<String> lines = Files.readAllLines(Paths.get("messages.json"));
        
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                JSONObject obj = new JSONObject(line);
                String message = obj.getString("message");
                String recipient = obj.getString("recipient");
                messagecount = messagecount+1;
                System.out.println("Message"+ messagecount + ":" + message+ "," + recipient);
                storedMessages.add(message);
            }
          }
        
     }catch (Exception e) {
        e.printStackTrace();
     }
 }
public String LongestMessage(){
    if(sentMessages.isEmpty()){
        return "No stored messages found";
    }
    
    String longestMessage = "";
    
    for(String message : sentMessages){
        if(message.length() > longestMessage.length()){
            longestMessage = message;
        }
    }
    
    return longestMessage.isEmpty() ? "No message found" : longestMessage;
}
     public String SearchbyMessageID (String MessageID){
        Boolean Found = false;
        for(int i =0; i <messageIDs.size();i++){
           if(messageIDs.get(i).equalsIgnoreCase(MessageID)){
               System.out.println("ID found :"+messageIDs.get(i));
               System.out.println("Message:"+sentMessages.get(i));
               Found = true;
            }
            
        }
        return "";
     }
     public String SearchbyRecipient(String RecipientPhonenumber){
          File file = new File("messages.json");
         if(!file.exists()){
         return "No stored messages found";
         }
         StringBuilder results = new StringBuilder();
         try{
             List<String> lines = Files.readAllLines(Paths.get("messages.json"));
             for(String line : lines){
                 if(!line.trim().isEmpty()){
                     JSONObject obj = new JSONObject(line);
                     String message = obj.getString("message");
                     String recipient = obj.getString("recipient");
                     if(recipient.contains(RecipientPhonenumber)){
                       results.append("Message :"+ message+ "," + RecipientPhonenumber);
                       results.append("\n");
                     }
                     
                 }
               
             }
             if(results.length() == 0){
                 return "No messages found for:" + RecipientPhonenumber;
             }
         }catch(IOException e){
             e.printStackTrace();
         }
         return results.toString();
     }
     public String DeleteByMessageHash(String MessageHash){
         Boolean Found = false;
         for(int i =0; i<messageHashes.size();i++){
             if(messageHashes.get(i).equalsIgnoreCase(MessageHash)){
                 messageHashes.remove(i);
                 messageIDs.remove(i);
                 sentMessages.remove(i);
                 
                 if (i < storedMessages.size()) {
                     storedMessages.remove(i);
                 }
                 Found = true;
                 break;
             } 
         }
         if(Found){
            try{
                File file = new File ("message.json");
                if(file.exists()){
                    List<String> lines = Files.readAllLines(Paths.get("message.json"));
                    List<String> NewData = new ArrayList<>();
                    for(String Line:lines){
                        if(!Line.trim().isEmpty()){
                        JSONObject obj = new JSONObject(Line);
                        String messageID = obj.optString("messageID","");
                        String message = obj.optString("message","");
                        String recipient = obj.optString("recipient","");
                        
                        if(messageID.length() >= 2 && !message.trim().isEmpty()){
                            String [] Words = message.split("\\s");
                            String FirstWord = Words[0];
                            String LastWord = Words[Words.length - 1];
                            String LineHash = (messageID.substring(0,2)+ ":" + FirstWord+ ":" + LastWord).toUpperCase();
                            
                            if(!LineHash.equalsIgnoreCase(MessageHash)){
                              NewData.add(Line);      
                            }
                            
                        }else{
                            NewData.add(Line);
                        }
                            
                        }
                        
                    }
                    try(FileWriter fw = new FileWriter("message.json", false)){
                        for(String Line : NewData){
                            fw.write(Line + System.lineSeparator());
                        }
                        
                    }
                    
                }
            }catch(IOException e){
                e.printStackTrace();
            } 
         }
         return Found ? "Message successfully deleted" :"Hash not Found";
     }
     public String MessageReport(){
          StringBuilder report = new StringBuilder();
          report.append("=== Message Report ===\n");
    
    for (int i = 0; i < sentMessages.size(); i++) {
        report.append("Hash: " + messageHashes.get(i)+"\n");
        report.append("Recipient: " + RecipientPhonenumber.get(i)+"\n");
        report.append("Message: " + sentMessages.get(i)+"\n");
        
    }
    if (sentMessages.isEmpty()) {
        report.append("No messages to display.\n");
        report.append("Send a message first.\n");
    }
    return report.toString();
     
}
     public int getNumberOFStoredMessages(){
         System.out.println("The number of stored messages is :"+StoredMessages);
         return StoredMessages;
     }
       public String sentMessageTestUnit(String message, String recipient, int Choice) {
        this.messageText = message;
        this.recipientphone = recipient;
        checkMessageID();
        createMessageHash();
        switch (Choice) {
            case 1:
                iTotalMessagessent++;
                RecipientPhonenumber.add(recipientphone);
                sentMessages.add(messageText);
                messageHashes.add(messageHash);
                messageIDs.add(messageID);
                return "Message successfully sent.";
            case 2:
                disregardedMessages.add(messageText);
                return "Press 0 to delete message";
            case 3:
                StoredMessages++;
                sentMessages.add(messageText);
                RecipientPhonenumber.add(recipientphone);
                messageIDs.add(messageID);
                messageHashes.add(messageHash);
                storeMessages();
                return "Message successfully stored";
            default:
                return "Feature not available";
        }
        }
      
}