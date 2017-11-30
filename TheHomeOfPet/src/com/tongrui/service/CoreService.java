package com.tongrui.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

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
				
				if(messageContent=="�绰"){
					respContent="�̽ܣ�13309081317/nͯ���ࣺ18008236668";
				}
				else if(messageContent=="��ַ"){
					respContent="�����h����ֵ��r�Q�Ј�";
				}else{
					respContent="Ոݔ��绰���ߵ�ַ���ֲ�ѯ�����Ϣ";
				}
/*				String readResult=MysqlUtil.queryData(UrlDefine.URL,UrlDefine.USER,UrlDefine.PASSWORD,messageContent);
				
				if(readResult!=null)
				{
					respContent=readResult;
					
				}
				else
				{
					respContent="������ϸ���ֿɲ�ѯ��Ʒ�۸�;/n����绰���ֿɲ�ѯ��ϵ��ʽ";
				} */
			}
			// ͼƬ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "�����͵���ͼƬ��Ϣ��";
			}
			// ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "�����͵���������Ϣ��";
			}
			// ��Ƶ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "�����͵�����Ƶ��Ϣ��";
			}
			// ����λ����Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "�����͵��ǵ���λ����Ϣ��";
			}
			// ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "�����͵���������Ϣ��";
			}
			// �¼�����
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = requestMap.get("Event");
				// ��ע
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "лл���Ĺ�ע��";
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
					// ����˵�����¼�֮��ϵ��ʽ
					if(buttonKey.equals("Contact")){
						
					}
					// ����˵�����¼�֮�������
					else if(buttonKey.equals("Corperation")){
						
					}
				}
			}
			// �����ı���Ϣ������
			textMessage.setContent(respContent);
			// ���ı���Ϣ����ת����xml
			respXml = MessageUtil.messageToXml(textMessage);
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
