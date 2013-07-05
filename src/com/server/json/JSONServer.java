package com.server.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;


public class JSONServer extends HttpServlet{ 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("get:"+req.getParameter("param"));
		resp.getOutputStream().print("<html>OK</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 设置响应的数据种类类型
		System.out.println("enter post");
        
		resp.setContentType("text/plain");
        // 设置编码为UTF-8
        resp.setCharacterEncoding("UTF-8");
        // 获取一个写对象，用于数据输出
        PrintWriter writer = resp.getWriter();
        // 创建一个字符串缓存对象
        StringBuffer sb = new StringBuffer();
        // 通过reader读取请求包含的数据
        BufferedReader br = new BufferedReader(new InputStreamReader(
                req.getInputStream()));
        String s = null;
        while ((s = br.readLine()) != null) {
            // 将读取到的数据存放到缓存区里面
            sb.append(s);
        }
        System.out.println("data: "+sb.toString());
        // 将字符数据转换成JSON数据对象
        JSONObject data = JSONObject.fromObject(sb.toString());
        // 获取请求数据里面的account
        int account = data.getInt("account");
        // 获取请求数据里面的password
        String password = data.getString("password");
        // 清除JSONObject里面存放的数据
        data.clear();
        
        
        // 判断数据库查询结果是否为空，若是，则表示数据库不存在该用户
        if (account==1 && password.equals("123")) {
            //将数据库的数据存放到JSONObject里面，便于发送给客户端
            data.put("user", "xubinbin");
            //往JSONObject里面存放一个boolean值true表示成功
            data.put("result", true);
            
            System.out.println("login success");
        }
        else{//如果不存在，则存放一个boolean值false表示失败
            data.put("result", false);
            System.out.println("login fail");
        }
        
        System.out.println("end handle");
        //将JSONObject发送返回
        writer.write(data.toString());
        writer.flush();
        writer.close();
	}

}
