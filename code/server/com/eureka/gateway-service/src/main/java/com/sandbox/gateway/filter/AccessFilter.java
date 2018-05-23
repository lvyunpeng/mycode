package com.sandbox.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于token校验
 *
 * @author lv.yp
 * @Date 2017-10-16
 */
public class AccessFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    private boolean isInnerRequest(String uri){
        return uri.contains("inner");
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestUri = request.getRequestURI();
        if(isInnerRequest(requestUri)){
             if(isValidRequest(request)){
                 return null;
             }
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        String accessToken = request.getHeader("Access-Token");
        if(StringUtils.isBlank(accessToken)){
            accessToken = request.getParameter("Access-Token");
        }
        String userId = request.getHeader("userId");
        if(StringUtils.isBlank(userId)){
            userId = request.getParameter("userId");
        }

        if(StringUtils.isBlank(accessToken) || StringUtils.isBlank(userId)){
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        try{
            if(isValidUserRequest(userId, accessToken)){
               return null;
            }
        }catch (Exception e){
        }
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(401);
        return null;
    }

    /**
     * token校验
     * @param userId
     * @param accessToken
     */
    private boolean isValidUserRequest(String userId, String accessToken) {
        return true;
    }

    private boolean isValidRequest(HttpServletRequest request){
        return true;
    }

}
