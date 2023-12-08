package org.todo.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.Cleanup;
import org.todo.model.Todo;

public enum TodoRepository {
        INSTANCE;

        public void insert(Todo todo) throws SQLException {
                String sql = "insert into tbl_todo (content, done, due_date) values(?,?,?);";

                @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
                @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);
                psmt.setString(1, todo.getContent());
                psmt.setBoolean(2, todo.isDone());
                psmt.setDate(3, Date.valueOf(todo.getDueDate()));

                psmt.executeUpdate();
        }

        public List<Todo> getList() throws SQLException {
                String sql = "select * from tbl_todo;";

                @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
                @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);
                ResultSet resultSet = psmt.executeQuery();

                List<Todo> todos = new ArrayList<>();

                while (resultSet.next()) {
                        Todo todo = Todo.builder()
                                .id(resultSet.getLong("id"))
                                .content(resultSet.getString("content"))
                                .dueDate(resultSet.getDate("due_date").toLocalDate())
                                .done(resultSet.getBoolean("done"))
                                .build();

                        todos.add(todo);
                }

                return todos;
        }

        public Todo getById(Long id) throws SQLException {
                String sql = "select * from tbl_todo where id = ?;";

                @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
                @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);
                psmt.setLong(1, id);
                ResultSet resultSet = psmt.executeQuery();
                resultSet.next();

                return Todo.builder()
                        .id(resultSet.getLong("id"))
                        .content(resultSet.getString("content"))
                        .done(resultSet.getBoolean("done"))
                        .dueDate(resultSet.getDate("due_date").toLocalDate())
                        .build();
        }

        public void update(Todo todo) throws SQLException {
                String sql = "update tbl_todo set content = ?, done = ? where id = ?";

                @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
                @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);
                psmt.setString(1, todo.getContent());
                psmt.setBoolean(2, todo.isDone());
                psmt.setLong(3, todo.getId());

                psmt.executeUpdate();
        }

        public void deleteById(Long id) throws SQLException {
                String sql = "delete from tbl_todo where id = ?";

                @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
                @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);
                psmt.setLong(1, id);

                psmt.executeUpdate();
        }
}
