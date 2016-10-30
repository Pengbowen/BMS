package com.lanyuan.assembly.util;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookies操作
 *
 * @author ly-two
 * @date 2016-9-22 下午12:21:01
 */
public class CookiesUtil
{

    /**
     * 添加cookie
     * @param response
     * @param name
     * @param value
     */
    public static void addCookie(HttpServletResponse response, String name, String value)
    {
        Cookie cookie = new Cookie(name.trim(), value.trim());
        cookie.setMaxAge(60 * 60 * 60 * 24 * 30);// 设置为30min
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 修改cookie
     * @param request
     * @param response
     * @param name
     * @param value
     * 注意一、修改、删除Cookie时，新建的Cookie除value、maxAge之外的所有属性，例如name、path、domain等，都要与原Cookie完全一样。否则，浏览器将视为两个不同的Cookie不予覆盖，导致修改、删除失败。
     */
    public static void editCookie(HttpServletRequest request, HttpServletResponse response,
            String name, String value)
    {
        Cookie[] cookies = request.getCookies();
        if (null == cookies)
        {
            // 没有cookie
        }
        else
        {
            for (Cookie cookie : cookies)
            {
                if (cookie.getName().equals(name))
                {
                    cookie.setValue(value);
                    cookie.setPath("/");
                    cookie.setMaxAge(30 * 60);// 设置为30min
                    response.addCookie(cookie);
                    break;
                }
            }
        }

    }

    /**
     * 删除cookie
     * @param request
     * @param response
     * @param name
     */
    public static void delCookie(HttpServletRequest request, HttpServletResponse response,
            String name)
    {
        Cookie[] cookies = request.getCookies();
        if (null == cookies)
        {
            // 没有cookie
        }
        else
        {
            for (Cookie cookie : cookies)
            {
                if (cookie.getName().equals(name))
                {
                    cookie.setValue(null);
                    cookie.setMaxAge(0);// 立即销毁cookie
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }

    /**
     * 根据名字获取cookie
     * @param request
     * @param name cookie名字
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name)
    {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        if (cookieMap.containsKey(name))
        {
            Cookie cookie = (Cookie) cookieMap.get(name);
            return cookie;
        }
        else
        {
            return null;
        }
    }

    /**
     * 将cookie封装到Map里面
     * @param request
     * @return
     */
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request)
    {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies)
        {
            for (Cookie cookie : cookies)
            {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
