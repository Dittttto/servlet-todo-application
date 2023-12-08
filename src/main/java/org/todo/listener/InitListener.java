package org.todo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.todo.repository.ConnectionUtil;

@WebListener
public class InitListener implements ServletContextListener {
        @Override
        public void contextInitialized(ServletContextEvent sce) {
                ConnectionUtil.INSTANCE.init(sce.getServletContext());
        }
}
