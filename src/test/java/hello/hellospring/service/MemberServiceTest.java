package hello.hellospring.service;

import hello.hellospring.domain.Member;

import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.AbstractStringAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    // 테스트 이름 메소드는 한글이름으로 해도 무관하다
    // 권장X
    //MemberService memberService = new MemberService();

    //클리어를 해줘야함
    // 하지만 맴버 서비스 클래스를 가보면 이미 생성이 되어있다. 두개씩 만들어서 사용할 이유가 있을까??
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemberService memberService;

    // 권장함 이런것을 디펜던시 인잭션 DI라고 한다
    @BeforeEach
    public void beforeEach()
    {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach()
    {
        memberRepository.clearStore();
    }

    // 테스트를 하는 방법
    @Test
    void join() {
        // given 무언가가 주어짐
        Member member = new Member();
        member.setName("hello");

        //when 실행했을때
        Long saveId = memberService.join(member);

        //then 이런결과가 나와야함
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName()); // 알트엔터 스테틱 임포트

    }


    @Test
    public void 중복_회원_예외()
    {
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

    @Test
    void findeMembers() {
    }

    @Test
    void findOne() {
    }
}