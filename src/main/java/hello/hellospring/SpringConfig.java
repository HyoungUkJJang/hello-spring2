package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 애노테이션이 아니라 자바 코드로 직접 스프링 빈에 등록하는 방법이다.
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository()
    {
        return new MemoryMemberRepository();

    }

    /* 코드로 하는것과 애노테이션으로 하는것의 장점이 각각있다.
    과거에는 XML 이라는 문서로 설정을 했다. 현재는 자바코드로 많이 설정한다.
    디펜던시 인잭션에는 필드,세터,생성자주입 3가지가 있다.
    세터주입은 비슷한것 같지만 아무곳에서나 호출이 될수있다는 단점이 있다.
    생성자 주입을 권장함.

     */

}
