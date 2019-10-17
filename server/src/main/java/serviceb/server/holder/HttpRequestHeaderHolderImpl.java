package serviceb.server.holder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alibaba.ttl.TransmittableThreadLocal;

import istio.fake.support.HttpRequestHeaderHolder;


@Component
public class HttpRequestHeaderHolderImpl extends HttpRequestHeaderHolder implements ServletRequestListener {

    @Autowired(required = false)
    private List<String> tracingHeaderList;

    @PostConstruct
    public void addUserId() {
//        tracingHeaderList.add("userId");
    }

    private ThreadLocal<HttpServletRequest> httpServletRequestHolder =
            new TransmittableThreadLocal<>();

    @Override
    public void requestInitialized(ServletRequestEvent requestEvent) {
        HttpServletRequest request = (HttpServletRequest) requestEvent.getServletRequest();
        httpServletRequestHolder.set(request);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent requestEvent) {
        httpServletRequestHolder.remove();
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequestHolder.get();
    }

    @Override
    public Map<String, Object> getHeaderMap() {
        HttpServletRequest request = getHttpServletRequest();
        if (request == null) {
            return null;
        }

        if (CollectionUtils.isEmpty(tracingHeaderList)) {
            return null;
        }

        Map<String, Object> headerMap = new HashMap<>(10);
        for (String name : tracingHeaderList) {
            String value = request.getHeader(name);
            if (value != null) {
                headerMap.put(name, request.getHeader(name));
            }
        }
        return headerMap;

    }
}