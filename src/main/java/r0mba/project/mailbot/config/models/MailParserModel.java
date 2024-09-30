package r0mba.project.mailbot.config.models;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@PropertySource("application.properties")
public class MailParserModel {

    @Value("${mail.user.login}")
    private String login;

    @Value("${mail.user.password}")
    private String password;

    @Value("${mail.host.adress}")
    private String host;

    @Value("${mail.host.port}")
    private String port;

}
