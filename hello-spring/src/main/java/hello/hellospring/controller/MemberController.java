package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired //스프링이 스프링 컨테이너에서 멤버서비스를 보관했다가 가져와줌=>의존성주입(DI)
    public MemberController(MemberService memberService) {
                this.memberService = memberService;
        System.out.println("memberService = " + memberService.getClass());
        //aop 적용전 프록시 memberService 에서 작업을 수행하고 실행.
        //-> proxy방식의 aop라고 한다.
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMembersForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member 이름 = "+form.getName());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }

}
//🐸 스프링 빈과 의존관계
//1. 컴포넌트 스캔과 자동 의존관계 설정
//  생성자에 @Autowired 가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다. 이렇게
//  객체 의존관계를 외부에서 넣어주는 것을 DI (Dependency Injection), 의존성 주입이라 한다.
//  이전 테스트에서는 개발자가 직접 주입했고, 여기서는 @Autowired에 의해 스프링이 주입해준다.

//2. 자바 코드로 직접 스프링 빈 등록하기