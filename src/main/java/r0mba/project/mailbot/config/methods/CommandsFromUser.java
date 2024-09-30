package r0mba.project.mailbot.config.methods;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import r0mba.project.mailbot.config.service.MailParser;
import r0mba.project.mailbot.config.service.TelegramBot;

import javax.mail.MessagingException;
import java.lang.reflect.Method;

@Component
public class CommandsFromUser {

    TelegramBot bot;
    MailParser parser;

    public CommandsFromUser(@Lazy TelegramBot bot, MailParser parser) {
        this.bot = bot;
        this.parser = parser;
    }

    public void start(long chatId, String userFirstName){
        String answer = String.format("хахахахах, это кто мне тут написал?? омг да это же %s !! \nИди нюхни смачной беброчки, чуУуУвак ;))", userFirstName);
        bot.sendMessage(chatId, answer);
    }

    public void unknown(long chatId){
        String answer = "неизвестная команда, бро \uD83E\uDD37 \nлучше подумай над своей жизнью и напиши еще раз";
        bot.sendMessage(chatId, answer);
        bot.sendSticker(chatId, "CAACAgQAAxkBAAEMwYxm1uAa-ZUClz55RAVjoBWn51a23QAC9g8AAi21QVEmtp8bGR7s6DUE");
    }

    public void help(long chatId){
        Method[] met = CommandsFromUser.class.getMethods();
        String[] answers = new String[met.length - 8];
        answers[0] = "Вот список доступных тебе команд:";
        for (int i = 0; i < met.length - 9 ; i++){
            answers[i + 1] = met[i].getName() + " \uD83D\uDDEF ";
        }
        String allAnswer = String.join("\n ->  /", answers);

        bot.sendMessage(chatId, allAnswer);
    }

    public void getCountAllInboxMessages(long chatId){
        try {
            String answer = " ✉\uFE0F Общее количество вашех писем на почте равно: " + parser.getCountAllInboxMessages();
            bot.sendMessage(chatId,answer);
        } catch (MessagingException e) {
            String answer = "Ошибка чтения почты, проверьте параметры";
            bot.sendMessage(chatId,answer);
            throw new RuntimeException(e);
        }
    }

    public void getLastMessageName(long chatId){
        try {
            String answer = " \uD83D\uDCC1 Последнее сообщение в почте : \n\uD83D\uDCCD " + parser.getLastMessageName();
            bot.sendMessage(chatId,answer);
        } catch (MessagingException e) {
            bot.sendImageFromUrl(chatId, "https://help-ru.roistat.com/img/Emeiltreking/10.jpg", "Ошибка чтения почты, проверьте параметры. \nСкорее всего ошибка связана с выключенным параметром 'Портальный пароль'.");
        }
    }
}
