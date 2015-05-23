package bsu.fpmi.chat.dao;

import bsu.fpmi.chat.exception.ModifyException;
import bsu.fpmi.chat.model.Message;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Gennady Trubach on 22.05.2015.
 */
public interface MessageDAO {
    void addMessage(Message message) throws ParseException;

    void updateMessage(Message message) throws ParseException, ModifyException;

    List<Message> getMessages() throws ParseException;

    Message getMessageById(String id) throws ParseException;
}
