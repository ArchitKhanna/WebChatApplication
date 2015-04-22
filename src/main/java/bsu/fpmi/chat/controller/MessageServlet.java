package bsu.fpmi.chat.controller;

import bsu.fpmi.chat.model.MessageStorage;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static bsu.fpmi.chat.util.MessageUtil.*;
import static bsu.fpmi.chat.util.ServletUtil.APPLICATION_JSON;
/**
 * Created by Gennady Trubach on 21.04.2015.
 * FAMCS 2d course 5th group
 */
@WebServlet("/chat")
public class MessageServlet extends HttpServlet {
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
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "'token' parameter needed");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    @SuppressWarnings("unchecked")
    private String serverResponse(int index) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(MESSAGES, MessageStorage.getSubHistory(index));
        jsonObject.put(TOKEN, MessageStorage.getSize());
        return jsonObject.toJSONString();
    }
}
