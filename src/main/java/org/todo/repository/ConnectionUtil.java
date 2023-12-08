package org.todo.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import lombok.extern.log4j.Log4j2;

@Log4j2
public enum ConnectionUtil {
        INSTANCE;
        private HikariDataSource dataSource;
        private boolean initialized = false;

        public void init(ServletContext context) {
                if (initialized) {
                        return;
                }

                HikariConfig hikariConfig = new HikariConfig();
                hikariConfig.setDriverClassName(context.getInitParameter("MariaDB"));
                hikariConfig.setJdbcUrl(context.getInitParameter("MariaDBURL"));
                hikariConfig.setUsername(context.getInitParameter("MariaDBUsername"));
                hikariConfig.setPassword(context.getInitParameter("MariaDBPassword"));
                hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
                hikariConfig.addDataSourceProperty("prpStmtCacheSize", "250");
                hikariConfig.addDataSourceProperty("prpStmtCacheSqlLimit", "2048");

                dataSource = new HikariDataSource(hikariConfig);
                initialized = true;
        }

        public Connection getConnection() {
                try {
                        return dataSource.getConnection();
                } catch (SQLException e) {
                        // todo log 를 추가 및 예외 처리 필요
                        log.error(e.getMessage());
                        e.printStackTrace();
                        throw new RuntimeException(e);
                }
        }
}
