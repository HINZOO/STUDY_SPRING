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