package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>,MemberRepository {

    //findByXX ~ 규칙성으로 다양한 쿼리를 작성할 수 있다.
    //Optional<Member> findByNameAndId(String name, int Id);
    //JPQL : select m from Member m where m.name =? and m.id=?
    @Override
    Optional<Member> findByName(String name);
    //JPQL : select m from Member m where m.name =?
    //인터페이스를 통한 기본적인 CRUD
    //findByName() , findByEmail() 처럼 메서드 이름 만으로 조회 기능 제공
    //페이징 기능 자동 제공
    //복잡한 동적 쿼리는 Querydsl이라는 라이브러리를 사용하면 된다.
    //Querydsl을 사용하면 쿼리도 자바 코드로 안전하게 작성할 수 있고, 동적
    //쿼리도 편리하게 작성할 수 있다. 이 조합으로 해결하기 어려운 쿼리는 JPA가 제공하는 네이티브 쿼리를
    //사용하거나, 앞서 학습한 스프링 JdbcTemplate를 사용하면 된다.
}
