package serviceb.server.controller;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import servicea.provider.ServerAProvider;
import serviceb.server.thread.FakeThread;

/**
 * @author tanghuan93@foxmail.com
 * @date 2019/10/16
 */
@RestController
public class BController {


    @Resource
    private ServerAProvider serverAProvider;

    @Resource
    private HttpServletRequest request;

    @Resource
    private FakeThread fakeThread;

    @PostMapping("/infoa")
    public JSONObject infoa(@RequestBody JSONObject json) {
        Enumeration<String> headerEnum = request.getHeaderNames();
        while (headerEnum.hasMoreElements()) {
            String headerName = headerEnum.nextElement();
            System.out.println(headerName + ":" + request.getHeader(headerName));
        }
        return serverAProvider.info("authcode", json);
    }

    @PostMapping("/async/infoa")
    public JSONObject asyncInfoa(@RequestBody JSONObject json) {
        Enumeration<String> headerEnum = request.getHeaderNames();
        while (headerEnum.hasMoreElements()) {
            String headerName = headerEnum.nextElement();
            System.out.println(headerName + ":" + request.getHeader(headerName));
        }
        fakeThread.asyncCall(json);
        JSONObject ret = new JSONObject();
        ret.put("hello", "async");
        return ret;
    }

}
