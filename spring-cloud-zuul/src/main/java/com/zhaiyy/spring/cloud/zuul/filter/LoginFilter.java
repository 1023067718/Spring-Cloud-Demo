package com.zhaiyy.spring.cloud.zuul.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class LoginFilter extends ZuulFilter {
    @Override
    public String filterType() {
        /* pre:路由之前
         * routing:路由之时
         * post:路由之后
         * error:发送错误调用
         * */
        return "pre";
    }

    @Override
    public int filterOrder() {
        //过滤顺序
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //是否需要过滤
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        var currentContext = RequestContext.getCurrentContext();
        var request = currentContext.getRequest();
        var token = request.getParameter("token");
        if (token == null) {
            var objectMapper = new ObjectMapper();
            var map = new HashMap<String, Object>();
            map.put("status", 401);
            map.put("message", "非法请求");
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            var response = currentContext.getResponse();
            response.setContentType("text/html;charset=utf-8");
            try {
                response.getWriter().write(objectMapper.writeValueAsString(map));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
