package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     * 회원가입
     */

    public Long join (Member member){
        //같은 이름이 있는 중복 확인
        validateDuplicateMember(member);//중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m->{
                 throw new IllegalStateException("이미 존재하는 회원입니다.");
             });
    }

    /*
    * 전체 회원 조회
    */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}
//🐸 I STUDY
//optional<T>  NPE(NullPointerException)을 방지
//https://mangkyu.tistory.com/70
//ifPresent: 값이 있으면 해당 값으로 지정된 소비자를 호출하고, 그렇지 않으면 아무 작업도 수행하지 않습니다.