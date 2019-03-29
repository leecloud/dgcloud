package com.lee.cloudzuul.filter;

import com.lee.cloudcommon.constants.CommonConstants;
import com.lee.cloudcommon.context.FilterContextHandler;
import com.lee.cloudcommon.dto.MenuDo;
import com.lee.cloudcommon.dto.UserToken;
import com.lee.cloudcommon.utils.JWTUtils;
import com.lee.cloudcommon.utils.JsonUtils;
import com.lee.cloudcommon.utils.R;
import com.lee.cloudzuul.rpc.MenuService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

public class AccessFilter extends ZuulFilter {
    @Autowired
    MenuService menuService;


    private String ignorePath = "/api-admin/login";
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10000;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        final String requestURI = request.getRequestURI();
        if (isStaticFilter(requestURI)){
            return null;
        }
        String accessToken = request.getHeader(CommonConstants.CONTEXT_TOKEN);
        if (null == accessToken || accessToken == ""){
            accessToken = request.getParameter(CommonConstants.TOKEN);
        }
        if (null == accessToken){
            setFailedRequest(R.error401(),200);
            return null;
        }
        try {
            UserToken userToken = JWTUtils.getInfoFromToken(accessToken);
        }catch (Exception e){
            setFailedRequest(R.error401(),200);
            return null;
        }
        FilterContextHandler.setToken(accessToken);
        if (!havePermission(request)){
            setFailedRequest(R.error403(),200);
            return null;
        }
        Set<String> headers = (Set<String>) ctx.get("ignoredHeaders");
        headers.remove("authorization");
        return null;
    }

    private boolean havePermission(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        List<MenuDo> list = menuService.userMeuns();
        for (MenuDo menuDo : list){
            if (requestURI != null && null != menuDo.getUrl() && requestURI.startsWith(menuDo.getUrl())){
                return true;
            }
        }
        return false;

    }

    private void setFailedRequest(Object body, int i) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(i);
        HttpServletResponse response = ctx.getResponse();
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(JsonUtils.beanToJson(body));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ctx.setSendZuulResponse(false);

    }

    private boolean isStaticFilter(String requestURI) {
        boolean flag = false;
        for (String s : ignorePath.split(",")){
            if (requestURI.startsWith(s)){
                return true;
            }
        }
        return flag;
    }
}
