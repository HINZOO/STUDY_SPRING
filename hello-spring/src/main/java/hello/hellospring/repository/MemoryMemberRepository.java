package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store = new HashMap<>();//저장 해둘 곳 key - data //실무에서는 ConcurrentHashMap
    private static long sequence =0L;//AtomicLong 사용 고려

    @Override
    public Member save(Member member) {
        member.setId(++sequence);//아이디 값을 1씩 증가하는 수로 설정
        store.put(member.getId(), member);//해당 아이디 값과 멤버 정보 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));//Optional.ofNullable() 로 감싸면 값이 null이여도 반환가능
    }

    @Override
    public Optional<Member> findByName(String name) {

        return store.values().stream()
                .filter(member->member.getName().equals(name))
                .findAny();//store에 담긴 member 객체속 name이 파라미터값 name과 하나라도 같은 값을(findAny) 찾아서바로 반환. 없으면 null을 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();//작업했던 값을 비움.
    }
}
