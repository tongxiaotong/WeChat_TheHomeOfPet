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
 * ���ķ�����
 * 
 * @author tongrui
 * @date 2017-11-29
 */
public class CoreService {
	/**
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml��ʽ����Ϣ����
		String respXml = null;
		// Ĭ�Ϸ��ص��ı���Ϣ����
		String respContent = "";
		try {
			// ����parseXml��������������Ϣ
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// ���ͷ��ʺ�
			String fromUserName = requestMap.get("FromUserName");
			// ������΢�ź�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");
		
			// �ظ��ı���Ϣ
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			// �ı���Ϣ
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				
				//��Ϣ����
				String messageContent=requestMap.get("Content");
				
				if(messageContent.equals("�绰")){
					respContent="�̽ܣ�13309081317/nͯ���ࣺ18008236668";
				}
				else if(messageContent.equals("��ַ")){
					respContent="����������ֵ�ũó�г�";
				}else{
					respContent="������绰���ߵ�ַ���ֲ�ѯ�����Ϣ";
				}
				textMessage.setContent(respContent);
				respXml=MessageUtil.messageToXml(textMessage);
			}
			// ͼƬ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "�����͵���ͼƬ��Ϣ,������绰���ߵ�ַ���ֲ�ѯ�����Ϣ��";
				textMessage.setContent(respContent);
				respXml=MessageUtil.messageToXml(textMessage);
			}
			// ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "�����͵���������Ϣ,������绰���ߵ�ַ���ֲ�ѯ�����Ϣ��";
				textMessage.setContent(respContent);
				respXml=MessageUtil.messageToXml(textMessage);
			}
			// ��Ƶ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "�����͵�����Ƶ��Ϣ,������绰���ߵ�ַ���ֲ�ѯ�����Ϣ��";
				textMessage.setContent(respContent);
				respXml=MessageUtil.messageToXml(textMessage);
			}
			// ����λ����Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "�����͵��ǵ���λ����Ϣ,������绰���ߵ�ַ���ֲ�ѯ�����Ϣ��";
				textMessage.setContent(respContent);
				respXml=MessageUtil.messageToXml(textMessage);
			}
			// ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "�����͵���������Ϣ,������绰���ߵ�ַ���ֲ�ѯ�����Ϣ��";
				textMessage.setContent(respContent);
				respXml=MessageUtil.messageToXml(textMessage);
			}
			// �¼�����
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = requestMap.get("Event");
				// ��ע
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "лл���Ĺ�ע,������绰���ߵ�ַ���ֲ�ѯ�����Ϣ,�����˵�������ز�����";
				}
				// ȡ����ע
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO ȡ�����ĺ��û��������յ������˺ŷ��͵���Ϣ����˲���Ҫ�ظ�
				}
				// ɨ���������ά��
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					// TODO ����ɨ���������ά���¼�
				}
				// �ϱ�����λ��
				else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					// TODO �����ϱ�����λ���¼�
				}
				// �Զ���˵�
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO ����˵�����¼�
					String buttonKey=requestMap.get("EventKey");
					// ����˵������̬��ζ�¼�
					if(buttonKey.equals("STMW")){
						
						respContent="���Դ���Ȼ����������ɽ��ֳ��Ϊ���ṩ���ʡ���ζ��Ұζ�ȸ�����ζ����������ϵ���ࣺ18008236668��̽ܣ�13309081317";
						textMessage.setContent(respContent);
						respXml=MessageUtil.messageToXml(textMessage);
					}
					// ����˵��������ҵ���¼�
					else if(buttonKey.equals("CWYW")){
						respContent="�ף�����֮��Ϊ���ṩ���������ࡢҽ�Ƽ�����ܱߵȷ���������רҵ���ŶӼ�������Ա����������ϵ���ࣺ18008236668��̽ܣ�13309081317";
						textMessage.setContent(respContent);
						respXml=MessageUtil.messageToXml(textMessage);
					}
					// ����˵���������¼�
					else if(buttonKey.equals("Corperation")){
						Article article=new Article();
						article.setTitle("�������");
						article.setDescription("�����������ϵ�̽ܣ�13309081317\n\n��ַ���������ϻ�ũó�г�-��ɽҰ����ֳ�������֮��");
						article.setUrl("");
						article.setPicUrl("");
						List<Article> articleList=new ArrayList<Article>();
						articleList.add(article);
						//����ͼ����Ϣ
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
			String tel=readDataFromFile("ͯ��");
			String tel2=readDataFromFile("172000");
			System.out.println(tel);
			System.out.println(tel2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
