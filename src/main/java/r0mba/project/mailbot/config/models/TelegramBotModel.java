package r0mba.project.mailbot.config.models;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import r0mba.project.mailbot.config.interfaces.Bot;

@Configuration
@Data
@PropertySource("application.properties")
public class TelegramBotModel implements Bot {

    @Getter
    @Value("${telegram.bot.api.token}")
    private String TelegramBotApiToken;

    @Getter
    @Value("${telegram.bot.name}")
    private String TelegramBotName;


}
