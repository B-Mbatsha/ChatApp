/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.chatapp;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author One eyed King
 */
import org.junit.jupiter.api.BeforeEach; 
import static org.junit.jupiter.api.Assertions.*;
public class MessageTest {
    Message mesg = new Message();
    private Message message1;
    private Message message2;
    
    @BeforeEach
    public void setUp(){
        message1= new Message();
        message1.checkRecipientCell("0787654325");
        message2 = new Message();
        message2.printMessages();
       
    }
    
    @Test
    public void testValidMessage_returnscorrectReciepient(){
     String Recipient = "0867893457";
     String result = message1.checkRecipientCell(Recipient);
     assertEquals("Cell phone number successfully captured",result);
    }
    @Test
    public void testValidMessage_returnsCorrectMessageText(){
        String Message = "";
        String result = message2.printMessages();
        assertEquals("",result);
        
    }public void testMessagenumber_returnEachMessagenumbers_areUnique(){
       
    }
    @Test
    public void testCheckMessageLength_validMessage_returnSuccess(){
    }
    @Test
    public void testCheckMessageLength_over250chars_returnFailureWithCount(){
       
    }
    @Test
    public void testCheckMessageLength_exactlyAtLimit_returnsSuccess (){
    }
    
    @Test
    public void testCheckMessageLength_oneOver_returnsFailureWithCountOf1 (){
    }
    @Test
    public void testCheckRecipientCell_validNumber_returnsSuccess(){
       
        
    }
    @Test
    public void testCheckRecipientCell_invalidNumber_returnsFailure(){
         
    }
    @Test
    public void testCreateMessageHash_correctFormat_endsWithExpectedWords(){
        
    }
    @Test 
    public void testCreateMessageHash_isUppercase(){
        
    }
    @Test
    public void testCreateMessageHash_multipleMessages_loopTest(){
        
    } 
    @Test 
    public void testCheckMessageID_generatedID_isNotNull (){
        
    }
    @Test
    public void testCheckMessageID_generatedID_isExactly10Chars (){
        
    }
    @Test
    public void testSentMessage_userSelectsSend_returnsCorrectString (){
        
    }
    @Test
    public void testSentMessage_userSelectsDisregard_returnsCorrectString (){
        
    }
     @Test
    public void testSentMessage_userSelectsStore_returnsCorrectString(){
        
    }
}