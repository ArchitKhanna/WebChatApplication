package bsu.fpmi.chat.controller;

import bsu.fpmi.chat.exception.ModifyException;
import bsu.fpmi.chat.model.Message;
import bsu.fpmi.chat.util.ChangesStorageUtil;
import bsu.fpmi.chat.storage.MessageXMLParser;
import bsu.fpmi.chat.util.ServletUtil;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import static bsu.fpmi.chat.util.MessageUtil.*;
import static bsu.fpmi.chat.util.ServletUtil.*;

/**
 * Created by Gennady Trubach on 21.04.2015.
 * FAMCS 2d course 5th group
 */
@WebServlet("/chat")
public class MessageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(MessageServlet.class.getName());

    @Override
    public void init() throws ServletException {
        try {
            restoreHistory();
        } catch (TransformerException | ParserConfigurationException | XMLStreamException | FileNotFoundException e) {
            logger.error(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Get request");
        String token = request.getParameter(TOKEN);
        logger.info("Request token : " + token);
        long lastModified = request.getDateHeader(IF_MODIFIED_SINCE);
        if (lastModified != -1 && Math.abs(lastModified - MessageXMLParser.getLastModifyDate()) < MILLISECONDS) {
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            logger.info("History not modified - 304");
        } else {
            if (token != null && !"".equals(token)) {
                int index = getIndex(token);
                logger.info("Index : " + index);
                String messages = serverResponse(index);
                response.setContentType(APPLICATION_JSON);
                response.setCharacterEncoding(UTF_8);
                lastModified = MessageXMLParser.getLastModifyDate();
                response.setDateHeader(LAST_MODIFIED, lastModified);
                PrintWriter pw = response.getWriter();
                pw.print(messages);
                pw.flush();
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "token parameter is absent");
                logger.error("Token parameter is absent");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Post request");
        String data = getMessageBody(request);
        logger.info("Request data : " + data);
        try {
            JSONObject jsonObject = stringToJson(data);
            Message message = jsonToNewMessage(jsonObject);
            logger.info(message.getReadableView());
            MessageXMLParser.addMessage(message);
            ChangesStorageUtil.addMessage(message);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (ParseException | ParserConfigurationException | SAXException | TransformerException | NullPointerException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            logger.error("Invalid message");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Put request");
        String data = getMessageBody(request);
        logger.info("Request data : " + data);
        Message message = null;
        try {
            JSONObject jsonObject = stringToJson(data);
            message = jsonToCurrentMessage(jsonObject);
            message.setModified();
            Message updatedMessage = MessageXMLParser.updateMessage(message);
            ChangesStorageUtil.addMessage(updatedMessage);
        } catch (ParseException | ParserConfigurationException | SAXException | XPathExpressionException | TransformerException |
                NullPointerException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            logger.error("Invalid message");
        } catch (ModifyException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            logger.error("Message with id : " + message.getID() + " doesn't exist, was deleted or not modified");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Delete request");
        String data = getMessageBody(request);
        logger.info("Request data : " + data);
        Message message = null;
        try {
            JSONObject jsonObject = stringToJson(data);
            message = jsonToCurrentMessage(jsonObject);
            message.delete();
            Message updatedMessage = MessageXMLParser.updateMessage(message);
            ChangesStorageUtil.addMessage(updatedMessage);
        } catch (ParseException | ParserConfigurationException | SAXException | XPathExpressionException | TransformerException |
                NullPointerException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            logger.error("Invalid message");
        } catch (ModifyException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            logger.error("Message with id : " + message.getID() + " doesn't exist, was deleted or not modified");
        }
    }

    @SuppressWarnings("unchecked")
    private String serverResponse(int index) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(MESSAGES, ChangesStorageUtil.getSubHistory(index));
        jsonObject.put(TOKEN, getToken(ChangesStorageUtil.getSize()));
        return jsonObject.toJSONString();
    }


    private void restoreHistory() throws TransformerException, ParserConfigurationException, XMLStreamException,
            FileNotFoundException {
        if (!MessageXMLParser.isStorageExist()) {
            MessageXMLParser.createStorage();
        } else {
            ChangesStorageUtil.addAll(MessageXMLParser.getMessages());
            logger.info('\n' + ChangesStorageUtil.getStringView());
        }
    }
}
