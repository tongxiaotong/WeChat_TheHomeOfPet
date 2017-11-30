package com.tongrui.otherServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chongwuzhijia.dataDefine.OrderDefine;
import com.tongrui.mysqlUtil.MysqlUtil;
import com.tongrui.mysqlUtil.UrlDefine;

public class OrderMakeServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");// 告诉浏览器中文编码方式
		String tel=request.getParameter("tel");
		String address=request.getParameter("address");
		String count=request.getParameter("count");
		String productName=request.getParameter("product");
		int c=Integer.parseInt(count);
		OrderDefine order=new OrderDefine(tel,address,c,productName);
		
		String sql_insert="INSERT INTO order_info (productName, count, order_person, address,currentTime,dealFlag) VALUES (?, ?, ?, ?,?,?)";
		System.out.println(sql_insert);
		System.out.println(address+productName);
		try {
			MysqlUtil.updateData(UrlDefine.URL,
					UrlDefine.USER, UrlDefine.PASSWORD, sql_insert,order);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);

	}

}
