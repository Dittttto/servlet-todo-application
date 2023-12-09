package org.todo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.Cleanup;
import org.todo.model.Member;

public enum MemberRepository {
        INSTANCE;

        public Member getByEmail(String email) throws SQLException {
                String sql = "select * from tbl_member where email = ?;";

                @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
                @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);
                psmt.setString(1, email);
                @Cleanup ResultSet resultSet = psmt.executeQuery();

                resultSet.next();

                return Member.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .build();
        }

        public void signup(Member member) throws SQLException {
                String sql = "insert into tbl_member(name, email, password) values(?, ?, ?);";
                @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
                @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);
                psmt.setString(1, member.getName());
                psmt.setString(2, member.getEmail());
                psmt.setString(3, member.getPassword());

                psmt.executeUpdate();
        }
}
