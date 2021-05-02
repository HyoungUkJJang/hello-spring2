package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 컨트롤러 에노테이션이 붙어있으면 스프링 실행될 때 컨테이너를 만들고 그안에 객체를 생성하여 스프링이 관리를 해준다. 스프링 빈이라고함
@Controller
public class MemerContoller {


    private final MemberService memberService;
    // 방법을 내일 공부하기
    // 푸쉬연습
    @Autowired // 이 부분까지는 해줘야함
    public MemerContoller(MemberService memberService) {
        this.memberService = memberService;
        System.out.println(memberService.getClass()); // aop 가자클래스 확인해보기
    }

    @GetMapping("/members/new")
    public String createform(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form)
    {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findeMembers();
        model.addAttribute("members", members);
        return "members/membersList";
    }
}
