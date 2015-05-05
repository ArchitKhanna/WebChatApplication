package bsu.fpmi.chat.util;

import bsu.fpmi.chat.model.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Gennady Trubach on 21.04.2015.
 * FAMCS 2d course 5th group
 */
public final class ChangesStorageUtil {
    private static final List<Message> HISTORY = Collections.synchronizedList(new ArrayList<Message>());

    private ChangesStorageUtil() {
    }

    public static void addMessage(Message message) {
        HISTORY.add(message);
    }

    public static void addAll(List<Message> messages) {
        HISTORY.addAll(messages);
    }

    public static int getSize() {
        return HISTORY.size();
    }

    public static List<Message> getSubHistory(int index) {
        return HISTORY.subList(index, HISTORY.size());
    }

    public static String getStringView() {
        StringBuffer sb = new StringBuffer();
        for (Message message : HISTORY) {
            sb.append(message.getReadableView());
            sb.append('\n');
        }
        return sb.toString().trim();
    }
}