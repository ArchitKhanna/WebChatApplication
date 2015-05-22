package bsu.fpmi.chat.dao;

import bsu.fpmi.chat.model.Message;

import java.util.List;

/**
 * Created by Gennady Trubach on 22.05.2015.
 */
public interface MessageDAO {
    boolean addMessage(Message message);

    Message updateMessage(Message message);

    boolean deleteMessage(Message message);

    List<Message> getMessages();
}
