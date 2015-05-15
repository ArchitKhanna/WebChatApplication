package bsu.fpmi.chat.controller;

import bsu.fpmi.chat.model.Message;
import bsu.fpmi.chat.storage.MessageXMLParser;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

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

import static bsu.fpmi.chat.util.MessageUtil.MESSAGES;
import static bsu.fpmi.chat.util.ServletUtil.APPLICATION_JSON;
import static bsu.fpmi.chat.util.ServletUtil.UTF_8;

/**
 * Created by gtrubach on 15.05.2015.
 */
@WebServlet(urlPatterns = "/restore")
public class HistoryServlet extends HttpServlet {
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Get request in history restore");
        try {
            String messages = serverResponse();
            response.setContentType(APPLICATION_JSON);
            response.setCharacterEncoding(UTF_8);
            PrintWriter pw = response.getWriter();
            pw.print(messages);
            pw.flush();
        } catch (XMLStreamException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private String serverResponse() throws IOException, XMLStreamException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(MESSAGES, MessageXMLParser.getMessages());
        return jsonObject.toJSONString();
    }

    private void restoreHistory() throws TransformerException, ParserConfigurationException, XMLStreamException,
            FileNotFoundException {
        if (!MessageXMLParser.isStorageExist()) {
            MessageXMLParser.createStorage();
        } else {
            for (Message message : MessageXMLParser.getMessages()) {
                logger.info(message.getReadableView());
            }
        }
    }
}
