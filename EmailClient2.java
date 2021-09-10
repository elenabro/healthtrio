import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.EmailUtils;

import javax.mail.Message;
import javax.mail.MessagingException;



public class EmailClient2 {
    private static EmailUtils emailUtils;

    @BeforeClass
    public static void connectToEmail() {
        try {
            emailUtils = new EmailUtils("testbrommer@gmail.com", "qazwsx!@#", "smtp.gmail.com", EmailUtils.EmailFolder.INBOX);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void checkMessageCount() throws MessagingException {
        int checkMessageCount = emailUtils.getNumberOfMessages();
        System.out.println("Total messages: " + checkMessageCount);
    }

    @Test
    public void checkMessageBySubject() throws Exception {
        Message[] messages = emailUtils.getMessagesBySubject("test", false, 15);
        for (int i = 0, n = messages.length; i < n; i++) {
            Message message = messages[i];
            if (message.getSubject().equals("test")) {
                System.out.println("the " + (i + 1) + " message with subject " + message.getSubject());
            }
        }
    }

    @Test
    public void getEmailMessageBySubject() throws MessagingException {
        Message[] messages = emailUtils.getMessages(15);
        for (int i = 0, n = messages.length; i < n; i++) {
            Message message = messages[i];
            if (message.getSubject().equals("test")) {
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());

            }
        }
    }
}

