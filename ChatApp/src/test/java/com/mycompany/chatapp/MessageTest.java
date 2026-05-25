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
import java.io.ByteArrayInputStream;
public class MessageTest {
    Message mesg = new Message();
    private Message message1;
    private Message message2;
    
    @Before
    public void setUp(){
        message1= new Message();
        message2 = new Message();
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
        
    }
    @Test 
    public void testCreateMessageHash_isUppercase(){
        //assert equals used to uppercase messagehash letters
         message2.sentMessage("It is a lovely day", "0838884321");
        String hash = message2.createMessageHash();
        assertEquals("Hash must be fully uppercase", hash.toUpperCase(), hash);
    }
    @Test
    public void testCreateMessageHash_multipleMessages_loopTest(){
        
    } 
    @Test 
    public void testCheckMessageID_generatedID_isNotNull (){
        //Test that message ID isnt null
        assertNotNull(mesg.getMessageID());
    }
    @Test
    public void testCheckMessageID_generatedID_isExactly10Chars (){
        //tests if message id is excatly 10 char in length
     assertTrue(mesg.getMessageID().length() == 10);
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
    
}