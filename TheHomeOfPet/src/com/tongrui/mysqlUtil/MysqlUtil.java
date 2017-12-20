package com.tongrui.mysqlUtil;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chongwuzhijia.dataDefine.EmployeeDefine;
import com.chongwuzhijia.dataDefine.OrderDefine;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class MysqlUtil {

	private static Connection con = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	private static Connection getConnect(String url,String user,String password) throws ClassNotFoundException, SQLException {
		// ����������
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		con = (Connection) DriverManager.getConnection(url, user, password);
		return con;
	}

	private static void closeConnect() {
		if (rs != null) { // �رռ�¼��
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) { // �ر�����
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) { // �ر����Ӷ���
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static List queryData(String url,String user,String password,String sql) throws ClassNotFoundException,
			SQLException {
	//	String sql="SELECT tel FROM employee_info WHERE id="+"'"+nameId+"' or name="+"'"+nameId+"'";
		ArrayList list=new ArrayList<OrderDefine>();
		con=getConnect(url,user,password);
		ps = (PreparedStatement) con.prepareStatement(sql);
		rs = ps.executeQuery(sql);
		while (rs.next()) {
			OrderDefine order_info=new OrderDefine();
			order_info.setProductId(rs.getInt("id"));
			order_info.setProductName(rs.getString("productName"));
			order_info.setOrderCount(rs.getInt("count"));
			order_info.setOrderPerson(rs.getString("order_person"));
			
			Date date=new Date(rs.getTimestamp("currentTime").getTime());
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String date_dis=format.format(date);
			System.out.println(date_dis);
			order_info.setOrderTime(date_dis);
		
			order_info.setAddress(rs.getString("address"));
		    list.add(order_info);
	//		System.out.println(telnum);
		}
	//	System.out.println(sql);
		closeConnect();
		return list;

	}
	//ǰ�������Ϊ���Ӳ����������ڶ�������Ϊ����sql��䣬���һ������Ϊ�������ֵ��
	public static void updateData(String url,String user,String password,String updateSql,OrderDefine order) throws ClassNotFoundException, SQLException{
		con=getConnect(url,user,password);
		
		ps = (PreparedStatement) con.prepareStatement(updateSql);
		ps.setString(1, order.getProductName());//���õ�һ������ֵ
		ps.setInt(2, order.getOrderCount());//���õڶ�������ֵ
		ps.setString(3, order.getOrderPerson());//���õ���������ֵ
		ps.setString(4, order.getAddress());//���õ��ĸ�����ֵ
		ps.setTimestamp(5, new java.sql.Timestamp(new java.util.Date().getTime()));//�����ȡ�ĵ�ǰʱ�䣻
		ps.setString(6,"0"); //�͑���ӆ�����O�Ô�����order����dealflagδ0����ʾ�̑�δ̎��͑�ӆ��
		int rs=ps.executeUpdate();
		
		closeConnect();
	}
	
	public static List queryEmployeeData(String url,String user,String password,String sql) throws ClassNotFoundException,
		SQLException {
	//	String sql="SELECT tel FROM employee_info WHERE id="+"'"+nameId+"' or name="+"'"+nameId+"'";
			ArrayList list=new ArrayList<EmployeeDefine>();
			con=getConnect(url,user,password);
			ps = (PreparedStatement) con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				EmployeeDefine employee_info=new EmployeeDefine();
				employee_info.setPwd(rs.getString("employee_pwd"));
				employee_info.setLevel(rs.getInt("employee_level"));
				employee_info.setId(rs.getString("employee_id"));
				employee_info.setName(rs.getString("employee_name"));
				
			    list.add(employee_info);
	//		System.out.println(telnum);
	}
	//	System.out.println(sql);
	closeConnect();
	return list;
	
	}
	
	public static int updateDealFlag(String url, String user, String password,
			String updateSql) {
		int rs=0;
		// TODO Auto-generated method stub
		try {
			con=getConnect(url,user,password);
			ps = (PreparedStatement) con.prepareStatement(updateSql);
		    rs=ps.executeUpdate();
		    closeConnect();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
		
		 
	}

	public static void main(String args[]){
		String sql="172008";
		String sql2="ͯ��";
		String sql_insert="insert into order_info (productName, count, order_person, address) values (?,?,?,?)";
		System.out.println(sql_insert);
		OrderDefine od=new OrderDefine("pig","xuyong",6,"15883075050");
		try {
			MysqlUtil.updateData(UrlDefine.URL,UrlDefine.USER,UrlDefine.PASSWORD,sql_insert,od);
		//	MysqlUtil.queryData(UrlDefine.URL_TEST,UrlDefine.USER,UrlDefine.PASSWORD,sql);
		//	MysqlUtil.queryData(UrlDefine.URL_TEST,UrlDefine.USER,UrlDefine.PASSWORD,sql2);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
