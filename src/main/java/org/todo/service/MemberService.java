package org.todo.service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.todo.dto.MemberDTO;
import org.todo.model.Member;
import org.todo.repository.MemberRepository;

@Log4j2
public enum MemberService {
        INSTANCE;

        private final MemberRepository repository = MemberRepository.INSTANCE;

        public MemberDTO signup(MemberDTO dto) {
                try {
                        repository.signup(Member.builder()
                                .name(dto.getName())
                                .email(dto.getEmail())
                                .password(dto.getPassword())
                                .build());
                        return dto;
                }
                catch (SQLIntegrityConstraintViolationException e) {
                        return null;
                }
                catch (SQLException e) {
                        log.error(e.getMessage());
                        throw new RuntimeException(e);
                }
        }

        public MemberDTO login(MemberDTO dto) {
                try {
                        Member origin = repository.getByEmail(dto.getEmail());

                        if (checkAuthentication(dto, origin)) {
                                return null;
                        }

                        return MemberDTO.builder()
                                .id(origin.getId())
                                .name(origin.getName())
                                .email(origin.getEmail())
                                .build();
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }
        }

        private static boolean checkAuthentication(MemberDTO dto, Member origin) {
                return origin == null
                        || !origin.getEmail().equals(dto.getEmail())
                        || !origin.getPassword().equals(dto.getPassword());
        }
}
