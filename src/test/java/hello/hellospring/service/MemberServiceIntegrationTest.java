package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 통합테스트
@SpringBootTest
@Transactional // 테스트 후에 롤백하여 반영을 안시킨다.
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    // 테스트를 하는 방법
    @Test
    void join() {
        // given 무언가가 주어짐
        Member member = new Member();
        member.setName("hello2");

        //when 실행했을때
        Long saveId = memberService.join(member);

        //then 이런결과가 나와야함
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName()); // 알트엔터 스테틱 임포트

    }


    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("Spring");
        Member member2 = new Member();
        member2.setName("Spring");


        //when
        memberService.join(member1);
        // 권장방법 트라이 캐치로 할수있지만 이게 효율적
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미존재");
        // memberService.join(member2);

        //then
    }
}