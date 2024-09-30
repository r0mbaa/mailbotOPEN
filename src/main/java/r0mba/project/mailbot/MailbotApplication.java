package r0mba.project.mailbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import r0mba.project.mailbot.config.models.TelegramBotModel;


@SpringBootApplication
public class MailbotApplication {
    public static void main(String[] args) {
        //SpringApplication.run(MailbotApplication.class, args);
        // Запускаем приложение и получаем контекст Spring
        ApplicationContext context = SpringApplication.run(MailbotApplication.class, args);
    
        // Получаем бин TelegramBotModel из контекста
        TelegramBotModel telegramBotModel = context.getBean(TelegramBotModel.class);

    }

}
