package com.fatal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

/**
 * WebEnvironment: web环境
 *  1.MOCK: 提供一个Mock的Servlet环境，内置的Servlet容器并没有真实的启动，主要搭配使用@AutoConfigureMockMvc
 *  2.RANDOM_PORT: 提供一个真实的Servlet环境，也就是说会启动内置容器，然后使用的是随机端口
 *  3.DEFINED_PORT: 这个配置也是提供一个真实的Servlet环境，使用的默认的端口，如果没有配置就是8080
 *  4.NONE: 这是个神奇的配置，跟Mock一样也不提供真实的Servlet环境
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Chapter1ApplicationTests {

    /**
     * @LocalServerPort 必须提供真实的Servlet环境，RANDOM_PORT 或者 DEFINED_PORT
     */
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/chapter1/demo1/");
    }

    @Test
    public void demo1() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        Assert.assertEquals(response.getBody(), "Hello Fatal!");
    }

}
