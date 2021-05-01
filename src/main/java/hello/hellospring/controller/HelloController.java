package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hell(Model model)
    {
        model.addAttribute("data","hello!!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model)
    {
        model.addAttribute("name",name);
        return "hello-template";
    }

    // 이 방식은 그냥 값을 그대로 내려주게 된다. 소스보기 하면 html 코드가 하나도 없는걸 확인할 수 있음 !
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name)
    {
        return "hello " + name;
    }

    // API 방식 JSON 방식으로 내려가게 된다 키 : 값 방식
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name)
    {
        Hello hello = new Hello(); // 컨트롤 + 쉬프트 + 엔터 > 세미콜론 닫고 바로 다음줄로 넘어간당
        hello.setName(name);
        return hello;

    }

    static class Hello
    {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;

    }
}
