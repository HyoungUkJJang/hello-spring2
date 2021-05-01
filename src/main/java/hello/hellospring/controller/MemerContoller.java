package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemerContoller {


    private final MemberService memberService;
    // 방법을 내일 공부하기
    // 푸쉬연습
    @Autowired
    public MemerContoller(MemberService memberService) {
        this.memberService = memberService;
    }


}
