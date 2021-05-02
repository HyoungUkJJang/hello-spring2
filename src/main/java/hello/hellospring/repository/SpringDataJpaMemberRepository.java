package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{
    // select m from Member m where m.name =?
    // 인터페이스 이름으로 개발이 가능하다... 신기..
    @Override
    Optional<Member> findByName(String name);




}
