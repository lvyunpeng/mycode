package com.sandbox.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于打印请求超过1秒的请求url及参数
 *
 * @author lv.yp
 * @Date 2018-04-08
 */
public class LogFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        long startTime = (long) context.get("startTime");//请求的开始时间
        String url = request.getRequestURI();//请求的uri
        int status = context.getResponseStatusCode();//请求的状态
        long meanTime = System.currentTimeMillis() - startTime;
        if(meanTime > 1000){
            logger.error("url:{}, status:{}, cost time:{}", url, status, meanTime);
        }

        return null;
    }
}
