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
		super.doGet(req, resp);
		System.out.println("get:"+req.getParameter("param"));
		resp.getOutputStream().print("OK");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
		// ������Ӧ��������������
        resp.setContentType("text/plain");
        // ���ñ���ΪUTF-8
        resp.setCharacterEncoding("UTF-8");
        // ��ȡһ��д���������������
        PrintWriter writer = resp.getWriter();
        // ����һ���ַ����������
        StringBuffer sb = new StringBuffer();
        // ͨ��reader��ȡ�������������
        BufferedReader br = new BufferedReader(new InputStreamReader(
                req.getInputStream()));
        String s = null;
        while ((s = br.readLine()) != null) {
            // ����ȡ�������ݴ�ŵ�����������
            sb.append(s);
        }
        // ���ַ�����ת����JSON���ݶ���
        JSONObject data = JSONObject.fromObject(sb.toString());
        // ��ȡ�������������account
        int account = data.getInt("account");
        // ��ȡ�������������password
        String password = data.getString("password");
        // ���JSONObject�����ŵ�����
        data.clear();
        
        
        // �ж����ݿ��ѯ����Ƿ�Ϊ�գ����ǣ����ʾ���ݿⲻ���ڸ��û�
        if (account==1 && password=="123") {
            //�����ݿ�����ݴ�ŵ�JSONObject���棬���ڷ��͸��ͻ���
            data.put("user", "xubinbin");
            //��JSONObject������һ��booleanֵtrue��ʾ�ɹ�
            data.put("result", true);
        }
        else{//��������ڣ�����һ��booleanֵfalse��ʾʧ��
            data.put("result", false);
        }
        //��JSONObject���ͷ���
        writer.write(data.toString());
        writer.flush();
        writer.close();
	}

}
