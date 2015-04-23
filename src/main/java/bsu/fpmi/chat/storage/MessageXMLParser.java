package bsu.fpmi.chat.storage;

import bsu.fpmi.chat.model.Message;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Gennady Trubach on 23.04.2015.
 * FAMCS 2d course 5th group
 */
public final class MessageXMLParser {
    private static final String XML_LOCATION = "E:\\history.xml";
    private static final String MESSAGES = "messages";
    private static final String MESSAGE = "message";
    private static final String ID = "id";
    private static final String SENDER_NAME = "senderName";
    private static final String MESSAGE_TEXT = "messageText";
    private static final String SEND_DATE = "sendDate";
    private static final String MODIFY_DATE = "modifyDate";
    private static final String DELETED = "isDeleted";

    private MessageXMLParser() {
    }

    public static synchronized void createStorage() throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element rootElement = document.createElement(MESSAGES);
        document.appendChild(rootElement);

        Transformer transformer = getTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(XML_LOCATION));
        transformer.transform(source, result);
    }

    public static synchronized void addMessage(Message message) throws ParserConfigurationException, IOException,
            SAXException, TransformerException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(XML_LOCATION);

        Element rootElement = document.getDocumentElement();
        Element messageElement = document.createElement(MESSAGE);
        rootElement.appendChild(messageElement);
        messageElement.setAttribute(ID, message.getID());

        Element senderNameElement = document.createElement(SENDER_NAME);
        senderNameElement.appendChild(document.createTextNode(message.getSenderName()));
        messageElement.appendChild(senderNameElement);

        Element messageTextElement = document.createElement(MESSAGE_TEXT);
        messageTextElement.appendChild(document.createTextNode(message.getMessageText()));
        messageElement.appendChild(messageTextElement);

        Element sendDateElement = document.createElement(SEND_DATE);
        sendDateElement.appendChild(document.createTextNode(message.getSendDate()));
        messageElement.appendChild(sendDateElement);

        Element modifyDateElement = document.createElement(MODIFY_DATE);
        modifyDateElement.appendChild(document.createTextNode(message.getModifyDate()));
        messageElement.appendChild(modifyDateElement);

        Element deletedElement = document.createElement(DELETED);
        deletedElement.appendChild(document.createTextNode(Boolean.toString(message.isDeleted())));
        messageElement.appendChild(deletedElement);

        Transformer transformer = getTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(XML_LOCATION);
        transformer.transform(source, result);
    }

    public static synchronized List<Message> getMessages() {
        return null;
    }

    private static Transformer getTransformer() throws TransformerConfigurationException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
        return transformer;
    }

    public static synchronized boolean isStorageExist() {
        File file = new File(XML_LOCATION);
        return file.exists();
    }
}
