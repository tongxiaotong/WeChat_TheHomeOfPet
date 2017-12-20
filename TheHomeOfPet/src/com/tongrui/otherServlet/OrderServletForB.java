package com.tongrui.otherServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.chongwuzhijia.dataDefine.OrderDefine;
import com.tongrui.mysqlUtil.MysqlUtil;
import com.tongrui.mysqlUtil.UrlDefine;

public class OrderServletForB extends HttpServlet {

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

		response.setHeader("Cache-control","no-cache");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");// 告诉浏览器中文编码方式
		String data = new String();
		ArrayList orderList = new ArrayList<OrderDefine>();
		PrintWriter out = response.getWriter();
	
		String sql_quertOrder = "select * from order_info where dealFlag='0'";//deaLFlag=0表示客户刚下订单，订单未处理。为1则表示订单已经处理
		try {
			orderList = (ArrayList) MysqlUtil.queryData(UrlDefine.URL,
					UrlDefine.USER, UrlDefine.PASSWORD, sql_quertOrder);
			for(int i=0;i<orderList.size();i++)
			{
				OrderDefine od=(OrderDefine)(orderList.get(i));
				String date=od.getOrderTime();
				System.out.println(date);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONArray jsonArray = JSONArray.fromObject(orderList);
		data = jsonArray.toString();
		System.out.println(data);
		out.write(data);
		out.flush();

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
