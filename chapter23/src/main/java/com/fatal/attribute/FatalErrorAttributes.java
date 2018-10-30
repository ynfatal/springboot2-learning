package com.fatal.attribute;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 自定义 ErrorAttributes
 * @author: Fatal
 * @date: 2018/10/30 0030 15:12
 */
@Component
public class FatalErrorAttributes extends DefaultErrorAttributes {

    /**
     * 这个方法有点像代理
     * @param webRequest ：接受异常处理方法中的request
     * @param includeStackTrace
     * @return
     */
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest,
                                                  boolean includeStackTrace) {
        /**
         * 这个map就是最后返回的数据，如果你想改格式，改这个map即可
         */
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        // 后面的scope知道你对哪个域存取数据，可以参考 RequestAttributes类
        Map<String, Object> ext = (Map<String, Object>) webRequest.getAttribute("ext", 0);
        map.put("ext", ext);
        map.put("where", "进入自定义ErrorAttributes的getErrorAttributes中了~~");
        return map;
    }
}
