package bsu.fpmi.chat.util;


import bsu.fpmi.chat.model.Message;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Gennady Trubach on 21.04.2015.
 * FAMCS 2d course 5th group
 */
public final class MessageUtil {
    public static final String TOKEN = "token";
    public static final String MESSAGES = "messages";
    private static final String TN = "TN";
    private static final String EN = "EN";
    private static final String ID = "id";
    private static final String SENDER_NAME = "senderName";
    private static final String MESSAGE_TEXT = "messageText";
    private static final String SEND_DATE = "sendDate";
    private static final String MODIFY_DATE = "modifyDate";
    private static final String IS_DELETED = "isDeleted";

    private MessageUtil() {
    }

    public static String getToken(int index) {
        Integer number = index * 8 + 11;
        return TN + number + EN;
    }

    public static int getIndex(String token) {
        return (Integer.valueOf(token.substring(2, token.length() - 2)) - 11) / 8;
    }

    public static String generateCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Minsk"));
        return dateFormat.format(new Date());
    }

    public static JSONObject stringToJson(String data) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        return (JSONObject) jsonParser.parse(data.trim());
    }

    public static Message jsonToMessage(JSONObject jsonObject) {
        Object id = jsonObject.get(ID);
        Object senderName = jsonObject.get(SENDER_NAME);
        Object messageText = jsonObject.get(MESSAGE_TEXT);
        if (id != null && senderName != null && messageText != null) {
            return new Message((String) id, (String) senderName, (String) messageText, getSendDate(jsonObject),
                    getModifyDate(jsonObject), getDeletedState(jsonObject));
        }
        return null;
    }

    private static String getSendDate(JSONObject jsonObject) {
        Object date = jsonObject.get(SEND_DATE);
        if (date != null) {
            return (String) date;
        }
        return generateCurrentDate();
    }

    private static String getModifyDate(JSONObject jsonObject) {
        Object date = jsonObject.get(MODIFY_DATE);
        if (date != null) {
            return (String) date;
        }
        return "not modified";
    }

    private static Boolean getDeletedState(JSONObject jsonObject) {
        Object isDeleted = jsonObject.get(IS_DELETED);
        if (isDeleted != null) {
            return (Boolean) isDeleted;
        }
        return Boolean.FALSE;
    }
}
