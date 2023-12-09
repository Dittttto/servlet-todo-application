package org.todo.controller;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.todo.dto.MemberDTO;
import org.todo.service.MemberService;

@WebServlet(name = "todoMemberController", urlPatterns = "/api/member")
public class SignupApiController extends HttpServlet {
        private final MemberService service = MemberService.INSTANCE;

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                String name = req.getParameter("name");
                String email = req.getParameter("email");
                String password = req.getParameter("password");

                MemberDTO signup = service.signup(MemberDTO.builder()
                        .name(name)
                        .email(email)
                        .password(password)
                        .build());

                if (signup == null) {
                        resp.sendRedirect("/signup?error=duplicate");
                        return;
                }

                resp.sendRedirect("/login");
        }
}
