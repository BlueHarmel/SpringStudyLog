package hello.core.web;

import hello.core.common.MyLogger;
import hello.core.logdemo.LogDemoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
//    private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
//        MyLogger myLogger = myLoggerProvider.getObject();

        System.out.println("myLogger = " + myLogger.getClass());// 프록시 객체가 주입됨
        myLogger.setRequestURL(requestURL); // 실제 호출할 때 진짜 클래스가 사용됨

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
