package com.tongrui.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.tongrui.message.resp.Article;
import com.tongrui.message.resp.NewsMessage;
import com.tongrui.message.resp.TextMessage;
import com.tongrui.mysqlUtil.MysqlUtil;
import com.tongrui.mysqlUtil.UrlDefine;
import com.tongrui.util.MessageUtil;

/**
 * 核心服务类
 * 
 * @author tongrui
 * @date 2017-11-29
 */
public class CoreService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml格式的消息数据
		String respXml = null;
		// 默认返回的文本消息内容
		String respContent = "";
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
		
			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				
				//消息内容
				String messageContent=requestMap.get("Content");
				
				if(messageContent.equals("电话")){
					respContent="程杰：13309081317/n童海燕：18008236668";
				}
				else if(messageContent.equals("地址")){
					respContent="敘永县西外街道农贸市场";
				}else{
					respContent="请输入电话或者地址二字查询相关信息";
				}
				textMessage.setContent(respContent);
				respXml=MessageUtil.messageToXml(textMessage);
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息,请输入电话或者地址二字查询相关信息！";
				textMessage.setContent(respContent);
				respXml=MessageUtil.messageToXml(textMessage);
			}
			// 语音消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是语音消息,请输入电话或者地址二字查询相关信息！";
				textMessage.setContent(respContent);
				respXml=MessageUtil.messageToXml(textMessage);
			}
			// 视频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "您发送的是视频消息,请输入电话或者地址二字查询相关信息！";
				textMessage.setContent(respContent);
				respXml=MessageUtil.messageToXml(textMessage);
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息,请输入电话或者地址二字查询相关信息！";
				textMessage.setContent(respContent);
				respXml=MessageUtil.messageToXml(textMessage);
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息,请输入电话或者地址二字查询相关信息！";
				textMessage.setContent(respContent);
				respXml=MessageUtil.messageToXml(textMessage);
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 关注
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "谢谢您的关注,请输入电话或者地址二字查询相关信息,或点击菜单进行相关操作！";
				}
				// 取消关注
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
				}
				// 扫描带参数二维码
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					// TODO 处理扫描带参数二维码事件
				}
				// 上报地理位置
				else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					// TODO 处理上报地理位置事件
				}
				// 自定义菜单
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO 处理菜单点击事件
					String buttonKey=requestMap.get("EventKey");
					// 处理菜单点击生态美味事件
					if(buttonKey.equals("STMW")){
						
						respContent="来自大自然的馈赠，丹山养殖场为您提供海鲜、腊味、野味等各种美味，详情请联系海燕：18008236668或程杰：13309081317";
						textMessage.setContent(respContent);
						respXml=MessageUtil.messageToXml(textMessage);
					}
					// 处理菜单点击宠物业务事件
					else if(buttonKey.equals("CWYW")){
						respContent="亲，宠物之家为您提供宠物各项清洁、医疗及相关周边等服务，我们有专业的团队及技术人员，详情请联系海燕：18008236668或程杰：13309081317";
						textMessage.setContent(respContent);
						respXml=MessageUtil.messageToXml(textMessage);
					}
					// 处理菜单商务合作事件
					else if(buttonKey.equals("Corperation")){
						Article article=new Article();
						article.setTitle("商务合作");
						article.setDescription("商务合作请联系程杰：13309081317\n\n地址：叙永县南环农贸市场-丹山野生养殖场与宠物之家");
						article.setUrl("");
						article.setPicUrl("");
						List<Article> articleList=new ArrayList<Article>();
						articleList.add(article);
						//创建图文信息
						NewsMessage  newsMsg=new NewsMessage();
						newsMsg.setFromUserName(toUserName);
						newsMsg.setCreateTime(new Date().getTime());
						newsMsg.setToUserName(fromUserName);
						newsMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMsg.setArticleCount(articleList.size());
						newsMsg.setArticles(articleList);
						respXml=MessageUtil.messageToXml(newsMsg);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
	

	
	private static String readDataFromFile(String key) throws IOException{
		InputStream in=null;
		String value=null;
		try {
			in = CoreService.class.getClassLoader().getResourceAsStream("tel.properties");
			Properties properties=new Properties();
			properties.load(in);
			value=properties.getProperty(key);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			in.close();
		}
		return value;
	}
	
	public static void main(String args[]){
		
		try {
			String tel=readDataFromFile("童锐");
			String tel2=readDataFromFile("172000");
			System.out.println(tel);
			System.out.println(tel2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
