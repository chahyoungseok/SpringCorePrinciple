package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 스프링 MVC는 @Controller 또는 @RequestMapping 이 있어야 스프링 컨트롤러로 인식
 * 해당 어노테이션들은 코드에서 보이는 것과 같이 인터페이스에서도 사용가능.
 * request 는 LogTrace를 적용할 대상, noLog 는 적용하지 않을 대상입니다.
 * 인터페이스에서는 @RequestParam 같은 어노테이션이 있어야 인식이 잘 됨.
 *
 *
 * **/

@RequestMapping
@ResponseBody
public interface OrderControllerV1 {

    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v1/no-log")
    String noLog();
}
