package org.todo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.todo.dto.TodoDTO;
import org.todo.service.TodoService;

@WebServlet(name = "todo", urlPatterns = "/api/todo")
public class TodoApiController extends HttpServlet {
        private final TodoService service = TodoService.INSTANCE;

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
                String content = req.getParameter("content");

                service.insert(TodoDTO.builder().content(content).build());
                resp.sendRedirect("/");
        }

        @Override
        protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
                BufferedReader reader = req.getReader();
                Map<String, Object> params = new HashMap<>();
                String line = reader.readLine();

                if (line == null) {
                        throw new IllegalArgumentException();
                }

                String[] result = line.replaceAll("[{}\"]", "").split(",");
                for (String s : result) {
                        String[] split = s.split(":");
                        String key = split[0];
                        String value = split[1];

                        if (value == null) {
                                throw new IllegalArgumentException();
                        }

                        params.put(key, value);
                }

                service.update(TodoDTO.builder()
                        .id(Long.parseLong(String.valueOf(params.get("id"))))
                        .content(String.valueOf(params.get("content")))
                        .done(Boolean.parseBoolean(String.valueOf(params.get("done"))))
                        .build()
                );
        }

        @Override
        protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
                BufferedReader reader = req.getReader();
                String id = reader.readLine();
                if (id == null) {
                        throw new IllegalArgumentException();
                }

                service.delete(TodoDTO.builder().id(Long.parseLong(id)).build());
        }
}
