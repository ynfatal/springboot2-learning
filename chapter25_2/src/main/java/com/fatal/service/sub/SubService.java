package com.fatal.service.sub;

import com.fatal.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * @author: Fatal
 * @date: 2018/11/10 0010 14:38
 */
@Service
public class SubService extends ProductService {

    public String sub() {
        System.out.println("execute sub method Return String ~ ~ ~ ~");
        return "sub";
    }

}
