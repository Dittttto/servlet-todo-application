# TODO Application

Sprin프레임워크로 웹 애플리케이션을 구현하기 위해서는 Servlet을 사용하게 된다. 

Servlet은 Java EE 기술 스펙 중 하나로 동적으로 클라이언트의 요청을 처리하고 응답할 수 있는 API의 집합이다. 같은 맥락으로 JSP(Java Server Pages)가 있지만, JSP는 화면을 그리는 부분에 초점이 맞추어져 있다. JSP는 내부적으로 servlet과 동일하게 처리되는데, 이는 JSP 코드가 servlet 코드로 변환 후 컴파일되고 실행되기 때문이다.
Servlet은 Servelt Container, Servlet Context, Session Storage 등의 개념을 포함한다. 

이번 프로젝트에서는 Spring 프레임워크의 기반이 될 수 있는 Servlet과 JSP를 학습하고 체득하기 위해서 Spring 프레임워크를 사용하지 않고, Servlet과 JSP만을 활용한 초 심플 TODO 애플리케이션을 구현하려고 한다.


## 기술 스택

- Servlet
- JSP
- MySQL or MariaDB
- HikariCP(for db connection pool)
- JSTL
- Lombok
- Log4j2 or Logback

## 기술 명세(TBD)

- 회원 가입이 가능하다.
- TODO 애플리케이션은 로그인한 사용자만 사용할 수 있다.
- TODO는 생성, 수정, 삭제가 가능하다.
- TODO는 단건 조회, 전체 조회가 가능하다.

