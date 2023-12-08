package org.todo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.todo.dto.TodoDTO;
import org.todo.service.TodoService;

@WebServlet(name = "todo", urlPatterns = "/api/todo")
public class TodoController extends HttpServlet {
        private final TodoService service = TodoService.INSTANCE;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
                List<TodoDTO> list = service.getList();

                // todo replace to redirection
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                PrintWriter writer = resp.getWriter();
                writer.print(list);
                writer.flush();
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
                String content = req.getParameter("content");

                service.insert(TodoDTO.builder().content(content).build());
        }

        @Override
        protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
                Long id = Long.parseLong(req.getParameter("id"));
                String content = req.getParameter("content");
                String doneStr = req.getParameter("done");

                boolean done = doneStr != null && doneStr.equals("on");

                service.update(TodoDTO.builder()
                        .id(id)
                        .content(content)
                        .done(done)
                        .build()
                );
        }

        @Override
        protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
                Long id = Long.parseLong(req.getParameter("id"));
                service.delete(TodoDTO.builder().id(id).build());
        }
}
