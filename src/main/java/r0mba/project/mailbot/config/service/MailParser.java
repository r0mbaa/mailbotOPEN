package r0mba.project.mailbot.config.service;

import org.springframework.stereotype.Component;
import r0mba.project.mailbot.config.models.MailParserModel;

import javax.mail.*;
import java.util.Properties;

@Component
public class MailParser {

    private final MailParserModel model;
    private Store store;
    private Folder emailFolder;


    public MailParser(final MailParserModel model) {
        this.model = model;
    }

   public void connect () throws MessagingException {
        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");
        store = Session.getDefaultInstance(props).getStore("imaps");
        store.connect(model.getHost(), model.getLogin(), model.getPassword());
   }

   public int getCountAllInboxMessages() throws MessagingException {
        if (store == null) {
            throw new MessagingException("Store not connected in method getCountAllInboxMessages");
        }
        else{
            emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);
            return emailFolder.getMessageCount();
        }
   }

   public String getLastMessageName() throws MessagingException {
       if (store == null) {
           throw new MessagingException("Store not connected in method getLastMessageName");
       }
       else{
           emailFolder = store.getFolder("INBOX");
           emailFolder.open(Folder.READ_ONLY);
           return emailFolder.getMessage( emailFolder.getMessageCount() ).getSubject();
       }
   }


}
