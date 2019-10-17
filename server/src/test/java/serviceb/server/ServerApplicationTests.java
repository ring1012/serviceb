package serviceb.server;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class ServerApplicationTests {

    @Test
    public void contextLoads() {
        final Map<String, Collection<String>> headers = new HashMap<>();
        headers.put("s", Collections.singleton("s"));
        System.out.println(headers.get("s"));
    }

}
