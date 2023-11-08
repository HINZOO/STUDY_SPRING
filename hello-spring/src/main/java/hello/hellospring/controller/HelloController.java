package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","SPRING!!");//key-data
        return "hello";//rendering 하는 html 파일 명.
    }//Thymeleaf 템플릿 엔진이 Resources/templates 안의 파일들중 해당이름과 같은 파일을 찾는다.
    // Springboot의 템플릿엔진 Thymeleaf 가 resources:templates/ +{ViewName}+ .html 로 매핑

    @GetMapping("hello-mvc")
    public String helloMVC(@RequestParam(value = "name") String name,  Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }
//    @RequestParam(value = "name",required = true)
//    required=false 이면 해당 파라미터가 없어도 에러가 뜨지않는다 default=true.

    @GetMapping("hello-string")
    @ResponseBody
        // http통신의 body 부 에 직접 데이터를 넣어주겠다.
        // @ResponseBody 를 사용하면 뷰 리졸버( viewResolver)를 사용하지 않음
    public String helloString(@RequestParam("name") String name){
        return "hello " +name;
        //http://localhost:8080/hello-string?name=spring!!! 실행하면 코드없이 바로 직관적으로 해당값이 나온다
        //소스보기를 하면 코드없이 적용됨을 확인할 수 있다.
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello= new Hello();
        hello.setName(name);
        return hello;
        //@ResponseBody 를 사용하고, 객체를 반환하면 객체가 JSON으로 변환됨
        //http://localhost:8080/hello-api?name=spring!!! 실행하면
        //{"name":"spring!!!"} 로 표기됨.
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    /*🐸 API방식 동작방법.
    @ResponseBody 를 사용
    HTTP의 BODY에 문자 내용을 직접 반환
    viewResolver 대신에 HttpMessageConverter 가 동작
    기본 문자처리: StringHttpMessageConverter
    기본 객체처리: MappingJackson2HttpMessageConverter
    byte 처리 등등 기타 여러 HttpMessageConverter가 기본으로 등록되어 있음
    */
}



