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

public class OrderServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Cache-control","no-cache");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");// ������������ı��뷽ʽ
		String data = new String();
		ArrayList orderList = new ArrayList<OrderDefine>();
		String tel = request.getParameter("tel");
		System.out.println(tel);
		String sql_quertOrder = "select * from order_info where order_person="
				+ tel;
		try {
			orderList = (ArrayList) MysqlUtil.queryData(UrlDefine.URL,
					UrlDefine.USER, UrlDefine.PASSWORD, sql_quertOrder);
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

		// request.setAttribute("order_info", data);//����Ҫ���ݸ�����servlet��ֵ
		// request.getRequestDispatcher("/servlet/OrderInfoDis").forward(request,
		// response);

		// response.sendRedirect("/chapter-04/my_order.html");
		// //�ض��򣬲���request����Ĳ�����
		PrintWriter out = response.getWriter();
		out.write(data);
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
