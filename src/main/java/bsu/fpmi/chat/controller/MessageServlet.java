package bsu.fpmi.chat.controller;

import bsu.fpmi.chat.model.Message;
import bsu.fpmi.chat.model.MessageStorage;
import bsu.fpmi.chat.storage.MessageXMLParser;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import static bsu.fpmi.chat.util.MessageUtil.*;
import static bsu.fpmi.chat.util.ServletUtil.APPLICATION_JSON;
import static bsu.fpmi.chat.util.ServletUtil.getMessageBody;

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
        String token = request.getParameter(TOKEN);
        if (token != null && !"".equals(token)) {
            int index = getIndex(token);
            String messages = serverResponse(index);
            response.setContentType(APPLICATION_JSON);
            PrintWriter pw = response.getWriter();
            pw.print(messages);
            pw.flush();
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "token parameter is absent");
            logger.error("Token parameter is absent");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Post request");
        String data = getMessageBody(request);
        logger.info("Request data : " + data);
        try {
            JSONObject jsonObject = stringToJson(data);
            Message message = jsonToMessage(jsonObject);
            logger.info(message.getReadableView());
            MessageXMLParser.addMessage(message);
            MessageStorage.addMessage(message);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (ParseException | ParserConfigurationException | SAXException | TransformerException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            logger.error("Invalid message");
        }
    }

    @SuppressWarnings("unchecked")
    private String serverResponse(int index) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(MESSAGES, MessageStorage.getSubHistory(index));
        jsonObject.put(TOKEN, getToken(MessageStorage.getSize()));
        return jsonObject.toJSONString();
    }


    private void restoreHistory() throws TransformerException, ParserConfigurationException, XMLStreamException,
            FileNotFoundException {
        if (!MessageXMLParser.isStorageExist()) {
            MessageXMLParser.createStorage();
        } else {
            MessageStorage.addAll(MessageXMLParser.getMessages());
            logger.info('\n' + MessageStorage.getStringView());
        }
    }
}
