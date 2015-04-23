package bsu.fpmi.chat.storage;

import bsu.fpmi.chat.model.Message;

import java.util.List;

/**
 * Created by Gennady Trubach on 23.04.2015.
 * FAMCS 2d course 5th group
 */
public final class MessageXMLParser {
    private static final String XML_LOCATION = "E:\\history.xml";
    private static final String MESSAGES = "messages";
    private static final String ID = "id";
    private static final String SENDER_NAME = "senderName";
    private static final String MESSAGE_TEXT = "messageText";
    private static final String SEND_DATE = "sendDate";
    private static final String MODIFY_DATE = "modifyDate";
    private static final String DELETED = "isDeleted";

    private MessageXMLParser() {
    }

    public static synchronized void addMessage(Message message) {

    }

    public static synchronized List<Message> getMessages() {
        return null;
    }


}
