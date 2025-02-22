package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService longDemoService;
    private final ObjectProvider<MyLogger> myLogger; // MyLogger는 request scope여서 생성 당시 주입된 값이 없음

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){
        String requestURL = request.getRequestURL().toString();
        MyLogger myLogger = this.myLogger.getObject();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        longDemoService.logic("testId");
        return "OK";
    }

}
