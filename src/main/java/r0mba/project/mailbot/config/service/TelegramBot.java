package r0mba.project.mailbot.config.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import r0mba.project.mailbot.config.methods.CommandsFromUser;
import r0mba.project.mailbot.config.models.TelegramBotModel;

import java.util.InputMismatchException;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final TelegramBotModel bot;

    private final CommandsFromUser commandsFromUser;

    // Внедрение зависимостей через конструктор
    public TelegramBot(TelegramBotModel bot,CommandsFromUser commandsFromUser) {
        this.bot = bot;
        this.commandsFromUser = commandsFromUser;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            long chatId = update.getMessage().getChatId();

            switch (update.getMessage().getText()) {
                case "/start":
                    commandsFromUser.start(chatId , update.getMessage().getChat().getFirstName());
                    break;
                case "/help":
                    commandsFromUser.help(chatId);
                    break;
                case "/getCountAllInboxMessages":
                    commandsFromUser.getCountAllInboxMessages(chatId);
                    break;
                case "/getLastMessageName":
                    commandsFromUser.getLastMessageName(chatId);
                    break;
                default:
                    commandsFromUser.unknown(chatId);
                    break;
            }
        }
        else{
            System.out.println("СОСАБА ОЛУХ БЕЗ СООБЩЕНИЯ");
        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public String getBotUsername() {
        return bot.getTelegramBotName();
    }

    @Override
    public String getBotToken() {
        return bot.getTelegramBotApiToken();
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    public void sendMessage(long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendSticker(long chatId, String sticker) {
        SendSticker sendSticker = new SendSticker();
        sendSticker.setChatId(String.valueOf(chatId));
        sendSticker.setSticker(new InputFile(sticker));
        try {
            execute(sendSticker);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendImageFromUrl(long chatId, String pictureUrl, String description) {
        SendPhoto sendPhotoReq = new SendPhoto();
        sendPhotoReq.setChatId(String.valueOf(chatId));
        sendPhotoReq.setCaption(description);
        sendPhotoReq.setPhoto(new InputFile(pictureUrl));
        try {
            execute(sendPhotoReq);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendImageFromUrl(long chatId, String pictureUrl) {
        SendPhoto sendPhotoReq = new SendPhoto();
        sendPhotoReq.setChatId(String.valueOf(chatId));
        sendPhotoReq.setPhoto(new InputFile(pictureUrl));
        try {
            execute(sendPhotoReq);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }




}
