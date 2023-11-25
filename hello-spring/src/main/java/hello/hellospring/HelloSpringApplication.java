package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}

//기본적으로는 스프링이 hello.hellospring 패키지 안의 것만 bean으로 등록한다.
//스프링은 스프링컨테이너에 스프링 빈을 등록할 때 기본으로 싱글톤으로 등록한다.(유일하게 하나만 등록해서 공유)
//따라서 같은 스프링 빈이면 모두 같은 인스턴스다. 설정으로 싱글톤이 아니게 설정할 수 있지만
//특별한 경우를 제외하면 대부분 싱글톤을 사용한다.

//DB접속
// c:/app/h2/bin 에서  /h2.bat 으로 실행.
//H2 db접속시 기본값 접속 후 // test.mv.db 파일 생기는지 확인
//JDBC URL 을
//jdbc:h2:tcp://localhost/~/test
//이렇게 설정해줘야 차후 충돌이 없다.