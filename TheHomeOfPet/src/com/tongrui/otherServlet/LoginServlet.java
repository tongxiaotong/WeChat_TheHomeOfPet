package com.tongrui.otherServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chongwuzhijia.dataDefine.EmployeeDefine;
import com.tongrui.mysqlUtil.MysqlUtil;
import com.tongrui.mysqlUtil.UrlDefine;

public class LoginServlet extends HttpServlet {

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
		PrintWriter out = response.getWriter();
		String tel=request.getParameter("tel");//获取表单提交上来的电话号码参数
		String pwd=request.getParameter("pwd");//获取表单提交上来的密码
		ArrayList employeeList=new ArrayList<EmployeeDefine>();
		String sql_queryPWD="select * from employee_info where employee_tel ="+tel;
		
		try {
			employeeList=(ArrayList)MysqlUtil.queryEmployeeData(UrlDefine.URL,
					UrlDefine.USER, UrlDefine.PASSWORD, sql_queryPWD);
			for(int i=0;i<employeeList.size();i++){
				EmployeeDefine temp=(EmployeeDefine) employeeList.get(i);
				if(temp.getPwd().equals(pwd)){
					response.sendRedirect("orderDeal.html");
				}else{
					out.write("密码不对，请核对后在输入");
					response.sendRedirect("login.html");
				}
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
