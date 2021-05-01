package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    // 테스트할때마다 초기화를 해줘야함
    @AfterEach
    public void afterEach()
    {
        repository.clearStore();
    }


    @Test
    public void save()
    {
        Member member = new Member();
        member.setName("Spring");

        repository.sava(member);
        Member result = repository.findById(member.getId()).get();

       // Assertions.assertEquals(member, result); // 기댓값, 비교값
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result); // 이게 더 많이씀

    }

    @Test
    public void findByName()
    {
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.sava(member1);

        // 쉬프트 에프육육
       Member member2 = new Member();
        member2.setName("Spring2");
        repository.sava(member2);

        Member result = repository.findByName("Spring1").get();
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findALl()
    {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.sava(member1);
        // 쉬프트 에프육육
        Member member2 = new Member();
        member2.setName("Spring2");
        repository.sava(member2);

        List<Member> result = repository.findAll();

        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2);

    }

}