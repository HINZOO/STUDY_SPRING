package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    //스프링 Bean에 직접 등록
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
// DI에는
// 필드주입(@Autowired private MemberService m...로 바로 주입시키는 것) ,
// setter주입, (public voic setXX.. ) -> public하게 노출된다는 단점이 있다.
// 생성자 주입 **가장많이 쓰임.
// 3가지 방법이 있다.

// 실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용한다.
// 그리고 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다.

//🤔 주의: @Autowired 를 통한 DI는 helloController , memberService 등과 같이 스프링이 관리하는
//객체에서만 동작한다. 스프링 빈으로 등록하지 않고 내가 직접 생성한 객체에서는 동작하지 않는다.