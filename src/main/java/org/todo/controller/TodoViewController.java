package org.todo.controller;


import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.todo.dto.MemberDTO;
import org.todo.dto.TodoDTO;
import org.todo.service.TodoService;

@Log4j2
@WebServlet(name = "todoViewController", urlPatterns = "/todo")
public class TodoViewController extends HttpServlet {
        private final TodoService service = TodoService.INSTANCE;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                log.info("==== view controller ==== ");
                HttpSession session = req.getSession();
                MemberDTO attribute = (MemberDTO)session.getAttribute("loginInfo");
                List<TodoDTO> list = service.getList(attribute.getId());
                req.setAttribute("todos", list);
                req.getRequestDispatcher("/WEB-INF/todo/home.jsp").forward(req, resp);
        }
}
