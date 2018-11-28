package com.fatal;

import com.fatal.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class Chapter5ApplicationTests {

    @LocalServerPort
    private int port;

    private String url;

    private String urlWithId;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void before() throws Exception{
        this.url = "http://localhost:" + port + "/users";
        this.urlWithId = "http://localhost:" + port + "/users/{id}";
    }

    /**
     *  postForEntity(String url, Object request, Class<T> responseType, Object... urlVariables)
     *  String url ：请求路径
     *  Object request ：参数
     *  Class<T> responseType ：返回值类型
     *  Object... urlVariables ：条件参数，对应请求路径上的占位符
     */
    @Test
    public void addUser() {
        User user = new User().setUsername("米彩").setPassword("20");
        template.postForEntity(url, user, Integer.class);
        log.info("[添加用户成功]\n");
    }

    @Test
    public void exchange() {
        ResponseEntity<List<User>> responseEntity = template.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<User>>() {
                });
        log.info("[查询所有] - [{}]\n", responseEntity.getBody());
    }

    @Test
    public void getForEntity() {
        ResponseEntity<User> responseEntity = template.getForEntity(urlWithId, User.class, 1);
        log.info("[主键查询] - [{}]\n", responseEntity.getBody());
    }

    @Test
    public void put() {
        User user = new User().setUsername("米琪").setPassword("20");
        template.put(urlWithId, user, 1);
        log.info("[修改用户成功]\n");
    }

    @Test
    public void delete() {
        template.delete(urlWithId, 2);
        log.info("[删除用户成功]");
    }

}
