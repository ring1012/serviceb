package serviceb.server.thread;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import servicea.provider.ServerAProvider;

/**
 * @author tanghuan93@foxmail.com
 * @date 2019/10/17
 */
@Component
public class FakeThread {

    @Resource
    private ServerAProvider serverAProvider;

    @Async
    public void asyncCall(JSONObject json) {
        serverAProvider.info("authcode",json);
    }

}
