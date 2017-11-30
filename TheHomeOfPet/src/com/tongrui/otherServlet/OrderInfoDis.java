package com.tongrui.otherServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.chongwuzhijia.dataDefine.OrderDefine;

public class OrderInfoDis extends HttpServlet {

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

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");//∏ÊÀﬂ‰Ø¿¿∆˜÷–Œƒ±‡¬Î∑Ω Ω
		ArrayList orderList=new ArrayList<OrderDefine>();
		OrderDefine od1=new OrderDefine(1,"“∞÷Ì»‚",5.5,1,"tongrui");
		OrderDefine od2=new OrderDefine(2,"—Ú»‚",15.5,2,"tongrui");
		orderList.add(od1);
		orderList.add(od2);
		JSONArray jsonArray = JSONArray.fromObject(orderList);
		String data=jsonArray.toString();
//		String data=request.getAttribute("order_info").toString();
		PrintWriter out = response.getWriter();
		if(data==null){
			out.write("null data!");
		}
		else{
			out.write(data);
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
