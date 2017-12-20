package com.chongwuzhijia.dataDefine;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import net.sf.json.JSONArray;

public class OrderDefine {
	public OrderDefine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderDefine(int id, String productName, double price,
			int orderCount, String orderPerson) {
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.orderCount = orderCount;
		this.orderPerson = orderPerson;
	}
	public OrderDefine(int id, String productName, double price,
			int orderCount, String orderPerson, String dealFlag,
			String address, String orderTime) {
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.orderCount = orderCount;
		this.orderPerson = orderPerson;
		this.dealFlag = dealFlag;
		this.address = address;
		this.orderTime = orderTime;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public OrderDefine(String orderPerson, String address, int c, String productName) {
		
		// TODO Auto-generated constructor stub
		this.productName = productName;
		this.address = address;
		this.orderCount = c;
		this.orderPerson = orderPerson;
	}
	
	private int id;
	private	String productName;
	private double price;
	private int orderCount;
	private String orderPerson;
	private String dealFlag;
	private String address;
	private String orderTime;
	public String getDealFlag() {
		return dealFlag;
	}
	public void setDealFlag(String dealFlag) {
		this.dealFlag = dealFlag;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getProductId() {
		return id;
	}
	public void setProductId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	public String getOrderPerson() {
		return orderPerson;
	}
	public void setOrderPerson(String orderPerson) {
		this.orderPerson = orderPerson;
	}
	
	public void setOrderTime(String orderTime) {
		// TODO Auto-generated method stub
		this.orderTime=orderTime;
	}
	public static void main(String args[]){
		ArrayList orderList=new ArrayList<OrderDefine>();
		OrderDefine od1=new OrderDefine(1,"“∞÷Ì»‚",5.5,1,"tongrui");
		OrderDefine od2=new OrderDefine(2,"—Ú»‚",15.5,2,"tongrui");
		OrderDefine od3=new OrderDefine(3,"—Ú»‚",15.5,2,"tongrui","1","xuyong","2017-11-21");
		orderList.add(od1);
		orderList.add(od2);
		orderList.add(od3);
		JSONArray jsonArray = JSONArray.fromObject(orderList);
		String data=jsonArray.toString();
		System.out.println(data);
		
		
	}
	
}
