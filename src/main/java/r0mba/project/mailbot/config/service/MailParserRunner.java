package r0mba.project.mailbot.config.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class MailParserRunner implements CommandLineRunner {

    private final MailParser mailParser;

    public MailParserRunner(MailParser mailParser) {
        this.mailParser = mailParser;
    }

    @Override
    public void run(String... args) throws Exception {
        mailParser.connect();
    }
}

