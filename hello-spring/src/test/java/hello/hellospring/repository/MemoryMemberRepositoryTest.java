package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class MemoryMemberRepositoryTest {
    //테스트는 의존관계 없이 실행이 되어야 되기 때문에
    //테스트 하나가 끝날때마다 저장소를 지워줘야한다.
    //@AfterEach 로 테스트가 끝날때마다 저장소를 지워줌.

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 단위 테스트가 끝날때 마다 실행
    public void afterEach(){
        repository.clearStore(); // 저장소를 지움.
    }
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring1");
        repository.save(member);
        Member result = repository.findById(member.getId()).get();
//        System.out.println("result = " + (result==member));
//        Assertions.assertEquals(member,result);// 테스트하고싶은값, 기대값 순으로 입력
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring2").get();
        assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
