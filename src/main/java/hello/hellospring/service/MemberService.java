package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional // jpa를 쓰는경우에 서비스에 달아줘야함.
public class MemberService {

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;

    }




    //회원가입
    // 조건1 같은 이름이 있으면 안된다.
    public Long join(Member member)
    {
        long start = System.currentTimeMillis();
        // 컨트롤 알트 브이 꿀단축키 ! 옵셔널에 대해 공부하기.
        //권장하는 방법은아니다
       // Optional<Member> result = memberRepository.findByName(member.getName());
        /*
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재");
        });
      */
        try {
            // 권장하는 방법 따로 메소드로 뺀다 컨트롤 알트 엠
            validateDuplicate(member);
            memberRepository.save(member);
            return member.getId();
        }
        finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish-start;
            System.out.println(timeMs);
        }
    }

    private void validateDuplicate(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m->{
            throw new IllegalStateException("이미존재");
        });
    }

    // 전체회원 조회
    public List<Member> findeMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId)
    {
        return memberRepository.findById(memberId);
    }

}
