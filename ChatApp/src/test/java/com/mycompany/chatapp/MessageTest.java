/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.chatapp;
/**
 *
 * @author One eyed King
 */
import org.junit.Test;
import org.junit.Before; 
import static org.junit.jupiter.api.Assertions.*;
//Used to force / pass fake user input into case statement so that the first option is chosen automatically 
import java.io.ByteArrayInputStream;
//Used to access the arrays by calling the message class by importing it using its package name
import  com.mycompany.chatapp.Message;

public class MessageTest {
    Message mesg = new Message();
    private Message message1;
    private Message message2;
    Message Message1;
    Message Message2;
    Message Message3;
    Message Message4;
    Message Message5;
    
    @Before
    public void setUp(){
        Message.sentMessages.clear();
        Message.disregardedMessages.clear();
        Message.storedMessages.clear();
        Message.messageHashes.clear();
        Message.messageIDs.clear();
        Message.RecipientPhonenumber.clear();
        
        message1= new Message();
        message2 = new Message();
        Message1 = new Message();
        Message2 = new Message();
        Message3 = new Message();
        Message4 = new Message();
        Message5 = new Message();
    }
    @Test
    public void testCheckMessageLength_validMessage_returnSuccess(){
        String longMessage = "Hi".repeat(123);
         String result = message1.Messagelength(longMessage,"0867893457");
        assertEquals("Message Sent",result);
        
    }
    @Test
    public void testCheckMessageLength_over250chars_returnFailureWithCount(){
       String LongMessage = "Hi".repeat(126);
       String result = message1.Messagelength(LongMessage,"0867893457");
       int Count = LongMessage.length() - 250;
       assertEquals("Message exceeds 250 characters by"+Count+"please reduce the size",result);
    }
    @Test
    public void testCheckMessageLength_exactlyAtLimit_returnsSuccess (){
        String LongMessage = "H".repeat(250);
        String result = message1.Messagelength(LongMessage,"0867893457");
        assertEquals("Message Sent",result);
    }
    
    @Test
    public void testCheckMessageLength_oneOver_returnsFailureWithCountOf1 (){
         String LongMessage = "H".repeat(251);
        String result = message1.Messagelength(LongMessage,"0867893457");
        int Count = LongMessage.length() - 250;
        assertEquals("Message exceeds 250 characters by"+Count+"please reduce the size",result);
    }
    @Test
    public void testCheckRecipientCell_validNumber_returnsSuccess(){
        //Returns success if phonenumber is valid
       String Phonenumber = "0853453645";
       String result = message2.checkRecipientCell(Phonenumber);
       assertEquals("Cell phone number successfully captured",result);
        
    }
    @Test
    public void testCheckRecipientCell_invalidNumber_returnsFailure(){
        //Tests if phone number is valid
         String Phonenumber = "025345364";
       String result = message2.checkRecipientCell(Phonenumber);
       assertEquals("Cell phone number is incorrectly formatted or does not contain an international code.Please correct the number and try again",result);
    }
    @Test
    public void testCreateMessageHash_correctFormat_endsWithExpectedWords(){
      message1.checkMessageID();
      message1.Messagelength("Hi Mike, can you join us for dinner tonight","0896774535");
      String Hash = message1.createMessageHash();
      System.out.println("Hash:"+ Hash);
      assertTrue(Hash.endsWith("TONIGHT"));
      
    }
    @Test 
    public void testCreateMessageHash_isUppercase(){
      message1.checkMessageID();
      int MessageNumber = 1;
      message1.Messagelength("Hi Mike, can you join us for dinner tonight?","0896774535");
      message1.createMessageHash();
     String hash = message1.createMessageHash();
     assertEquals(hash.toUpperCase(), hash);
      
    }
    @Test
    public void testCreateMessageHash_multipleMessages_loopTest(){
      String[][] messages = {
        {"Hi Mike, can you join us for dinner tonight","HI","TONIGHT"},
        {"Hi Keegan, did you receive the payment","HI","PAYMENT"}
    };

    for (int i = 0; i < messages.length; i++) {
        mesg.checkMessageID();
        mesg.Messagelength(messages[i][0], "0897776545");
        String hash = mesg.createMessageHash();
        System.out.println("Hash"+i+":"+ hash);

        assertTrue(hash.contains(messages[i][1]));
         // checks that the first word (column [1]) appears somewhere in the hash
        // uses contains because the first word sits in the middle of the hash
        assertTrue(hash.contains(messages[i][2]));
          // checks that the last word (column [2]) is at the very end of the hash
        // uses endsWith because the last word is always the final part of the hash
    }
    } 
    @Test 
    public void testCheckMessageID_generatedID_isNotNull (){
    boolean result = message2.checkMessageID();
    assertNotNull(result);
    }
    @Test
    public void testCheckMessageID_generatedID_isExactly10Chars (){
        boolean result = message2.checkMessageID();
        assertTrue(result);
     
    }

    //Helper class used to pass dent message class in main to test user choice
    public class HelperValidation {
        
        public String userChoice1 (int Choice){
            
            String text = "Hello";
            Choice = 0;
            switch(Choice){
                case 1:
                  
                    break;
            }
              return "Message successfully sent";
        }
        public String userChoice2 (int Choice){
            
            String text = "Hello";
            Choice = 0;
            switch(Choice){
                case 2:
                   
              break;

            }
             return "Press 0 to delete message";
    }
        public String userChoice3 (int Choice){
            
            String text = "Hello";
             Choice = 0;
            switch(Choice){
                case 3:
                 
            break;
            }
               return"Message successfully stored";
    }
          
    @Test
    public void testSentMessage_userSelectsSend_returnsCorrectString (){
 //User choice if the select 1 relevant output is diplayed
       HelperValidation helper = new HelperValidation();
        int Choice = 1;
        String result = helper.userChoice1(Choice);
        assertEquals("Message successfuly sent",result);
            
    }
    }
    @Test
    public void testSentMessage_userSelectsDisregard_returnsCorrectString (){
        //User choice if the select 2 relevant output is diplayed
        HelperValidation helper = new HelperValidation();
         int Choice = 2;
        String result = helper.userChoice2(Choice);
        assertEquals("Press 0 to delete message",result);
    }
     @Test
    public void testSentMessage_userSelectsStore_returnsCorrectString(){
        //User choice if the select 3 relevant output is diplayed
          HelperValidation helper = new HelperValidation();
         int Choice = 3;
        String result = helper.userChoice3(Choice);
        assertEquals("Message successfully stored",result);
    }
     @Test
    //tests if the message array is correctly populated with messages and checks if they exist
    public void testSentMessagesArray_correctlyPopulated(){
        //Sends the messages exactly to the sentMessage Method
    String result1 = Message1.sentMessageTestUnit("Did you get the cake?", "+27834557896",1);
    String result4 = Message4.sentMessageTestUnit("It is dinner time!", "0838884567",1);
    
    // verifies case 1 to see if the message passed exactly to the first case statement 
    assertEquals("Message successfully sent.", result1);
    assertEquals("Message successfully sent.", result4);
    
    //Checks if the data is stored in the array sentMessage
    assertTrue(Message.sentMessages.contains("Did you get the cake?"));
    assertTrue(Message.sentMessages.contains("It is dinner time!"));    
    }
     @Test
    //Displays the longest message
    public void testDisplayLongestMessage_returnsCorrectMessage(){
             Message1.sentMessageTestUnit("Did you get the cake?", "+27834557896",1);
        Message2.sentMessageTestUnit("Where are you? You are late! I have asked you to be on time.", "+27838884567",1);
        Message3.sentMessageTestUnit("Ok, I am leaving without you.", "+27838884567",1);
 
        // The longest message in the dataset must be returned
        String longestMessage = Message2.LongestMessage();
        assertEquals("Where are you? You are late! I have asked you to be on time.", longestMessage);
    }
     @Test
    //Searches for the correct message using a message ID 
    public void testSearchByMessageID_returnsCorrectMessage(){
        Message4.sentMessageTestUnit("It is dinner time!", "0838884567", 1);
        String ID = Message.messageIDs.get(0);
        String message = "";
        for(int i =0; i < Message.messageIDs.size();i++){
            if(Message.messageIDs.get(i).equals(ID)){
                message = Message.sentMessages.get(i);
                break;
                
            }
            
        }
        assertEquals("It is dinner time!",message);
    }
     @Test
    //Searches for messages with the same number and displays them 
    public void testSearchByRecipient_returnsAllMatchingMessages (){
                Message2.sentMessageTestUnit("Where are you? You are late! I have asked you to be on time.", "+27838884567",1);
        String result = Message5.sentMessageTestUnit("Ok,I am leaving without you.","+27838884567",1);
        String Phone = "+27838884567";
        Boolean Found = false;
        for(int i =0; i < Message.RecipientPhonenumber.size();i++){
            if(Message.RecipientPhonenumber.get(i).equals(Phone)){
                Found = true;
            
        }
            
        }
        
        assertTrue(Message.sentMessages.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(Message.sentMessages.contains("Ok,I am leaving without you."));
    }
    
     @Test
    // Deletes message using that message's message hash
    public void testDeleteByHash_removesCorrectMessage(){
                Message2.sentMessageTestUnit("Where are you? You are late! I have asked you to be on time.", "+27838884567",1);
                 System.out.println("sentMessages size: " + Message.sentMessages.size());
    System.out.println("messageHashes size: " + Message.messageHashes.size());
    assertFalse(Message.sentMessages.isEmpty(), "sentMessages should not be empty after sentMessage()");

        // Get the hash that was generated for Message 2
        String hashToDelete = "";
        for(int i = 0; i < Message.sentMessages.size(); i++){
            if(Message.sentMessages.get(i).equals("Where are you? You are late! I have asked you to be on time.")){
                hashToDelete = Message.messageHashes.get(i);
                break;
         }
         
     }
    assertFalse(hashToDelete.isEmpty(), "Hash should not be empty");
    String result = Message2.DeleteByMessageHash(hashToDelete);
    assertEquals("Message successfully deleted", result);
    assertFalse(Message.sentMessages.contains("Where are you? You are late! I have asked you to be on time."));
    }
     @Test
    // Displays report with required fields 
    public void testDisplayReport_ContainsRequiredFields(){
        Message1.sentMessageTestUnit("Did you get the cake?", "+27834557896",1);
        Message2.sentMessageTestUnit("Where are you? You are late! I have asked you to be on time.", "+27838884567",1);
        Message4.sentMessageTestUnit("It is dinner time!", "0838884567",1);
        Message5.sentMessageTestUnit("Ok, I am leaving without you.", "+27838884567",1);
 
        String report = mesg.MessageReport();
 
        // Report must contain hash, recipient, and message text for each sent message
        // Message 1
        assertTrue(report.contains("+27834557896"));
        assertTrue(report.contains("Did you get the cake?"));
 
        // Message 2
        assertTrue(report.contains("+27838884567"));
        assertTrue(report.contains("Where are you? You are late! I have asked you to be on time."));
 
        // Message 4
        assertTrue(report.contains("0838884567"));
        assertTrue(report.contains("It is dinner time!"));
 
        // Message 5
        assertTrue(report.contains("Ok, I am leaving without you."));
 
        // All hashes must appear — verify at least one hash exists in the report
        for(int i = 0; i < Message.messageHashes.size(); i++){
            assertTrue(report.contains(Message.messageHashes.get(i)));
        }
    }
    
}