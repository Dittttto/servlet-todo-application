package org.todo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.todo.dto.MemberDTO;
import org.todo.service.MemberService;

@WebServlet(name = "loginController", urlPatterns = "/api/login")
public class LoginApiController extends HttpServlet {

        private final MemberService service = MemberService.INSTANCE;

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                String email = req.getParameter("email");
                String password = req.getParameter("password");

                MemberDTO login = service.login(MemberDTO.builder()
                        .email(email)
                        .password(password)
                        .build());

                if (login == null) {
                        resp.sendRedirect("/login?state=error");
                        return;
                }

                HttpSession session = req.getSession();
                session.setAttribute("loginInfo", login);
                resp.sendRedirect("/");
        }
}
