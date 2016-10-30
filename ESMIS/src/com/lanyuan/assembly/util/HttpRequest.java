package com.lanyuan.assembly.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

public class HttpRequest
{
    public static Logger logger = Logger.getLogger(HttpRequest.class);

    public static String sendGet(String url, String param) throws IOException
    {
        String result = "";
        BufferedReader in = null;
        String urlNameString = url + "?" + param;
        URL realUrl = new URL(urlNameString);

        URLConnection connection = realUrl.openConnection();

        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent",
                                      "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

        connection.connect();

        Map<String, List<String>> map = connection.getHeaderFields();

        for (String key : map.keySet())
        {
            System.out.println(key + "--->" + map.get(key));
        }

        in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String line;
        while ((line = in.readLine()) != null)
        {
            result = result + line;
        }
        try
        {
            if (in != null) in.close();
        }
        catch (Exception e2)
        {
            e2.printStackTrace();
        }
        return result;
    }

    public static String sendPost(String url, String param) throws IOException
    {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        URL realUrl = new URL(url);

        URLConnection conn = realUrl.openConnection();

        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent",
                                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

        conn.setDoOutput(true);
        conn.setDoInput(true);

        out = new PrintWriter(conn.getOutputStream());

        out.print(param);

        out.flush();

        in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String line;
        while ((line = in.readLine()) != null)
        {
            result = result + line;
        }
        try
        {
            if (out != null)
            {
                out.close();
            }
            if (in != null) in.close();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return result;
    }

    public static String sendPost(String postUrl, JSONObject jsonParam, String apiKey)
            throws IOException
    {
        URL url = new URL(postUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type", "application/json");
        if (apiKey != null)
        {
            connection.setRequestProperty("md5", apiKey);
        }

        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        if (jsonParam != null)
        {
            out.writeBytes(jsonParam.toString());
        }
        out.flush();
        out.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));

        StringBuffer sb = new StringBuffer("");
        String lines;
        while ((lines = reader.readLine()) != null)
        {
            lines = new String(lines.getBytes(), "utf-8");
            sb.append(lines);
        }

        reader.close();

        connection.disconnect();

        return sb.toString();
    }

    public static String sendPostByString(String postUrl, String jsonParam, String apiKey)
            throws IOException
    {
        URL url = new URL(postUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type", "application/json");
        if (apiKey != null)
        {
            connection.setRequestProperty("md5", apiKey);
        }

        // DataOutputStream out = new
        // DataOutputStream(connection.getOutputStream());
        // if (jsonParam != null) {
        // out.writeBytes(jsonParam.toString());
        // }
        // out.flush();
        // out.close();

        PrintWriter out = new PrintWriter(connection.getOutputStream());

        out.print(jsonParam);

        out.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));

        StringBuffer sb = new StringBuffer("");
        String lines;
        while ((lines = reader.readLine()) != null)
        {
            lines = new String(lines.getBytes(), "utf-8");
            sb.append(lines);
        }

        reader.close();

        connection.disconnect();

        return sb.toString();
    }

    public static String getRemoteIP(HttpServletRequest request)
    {
        String ipAddress = request.getHeader("x-forwarded-for");
        if ((ipAddress == null) || (ipAddress.length() == 0)
                || ("unknown".equalsIgnoreCase(ipAddress)))
        {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if ((ipAddress == null) || (ipAddress.length() == 0)
                || ("unknown".equalsIgnoreCase(ipAddress)))
        {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((ipAddress == null) || (ipAddress.length() == 0)
                || ("unknown".equalsIgnoreCase(ipAddress)))
        {
            ipAddress = request.getRemoteAddr();
            if ((ipAddress.equals("127.0.0.1")) || (ipAddress.equals("0:0:0:0:0:0:0:1")))
            {
                InetAddress inet = null;
                try
                {
                    inet = InetAddress.getLocalHost();
                }
                catch (UnknownHostException e)
                {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }

        if ((ipAddress != null) && (ipAddress.length() > 15))
        {
            if (ipAddress.indexOf(",") > 0)
            {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}
