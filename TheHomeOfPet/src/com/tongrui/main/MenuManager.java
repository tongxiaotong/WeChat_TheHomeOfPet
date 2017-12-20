package com.tongrui.main;

import com.tongrui.menu.Button;
import com.tongrui.menu.ClickButton;
import com.tongrui.menu.ComplexButton;
import com.tongrui.menu.Menu;
import com.tongrui.menu.ViewButton;
import com.tongrui.token.Token;
import com.tongrui.util.CommonUtil;
import com.tongrui.util.MenuUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * �˵���������
 * 
 * @author tongrui
 * @date 2017-03-09
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	/**
	 * ����˵��ṹ
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		ClickButton btn11 = new ClickButton();
		btn11.setName("��̬��ζ");
		btn11.setType("click");
		btn11.setKey("STMW");

		ClickButton btn12 = new ClickButton();
		btn12.setName("����ҵ��");
		btn12.setType("click");
		btn12.setKey("CWYW");

		

		ViewButton btn21 = new ViewButton();
		btn21.setName("��������");
		btn21.setType("view");
		btn21.setUrl("http://www.rejet.cn/TheHomeOfPet/my_order.html");

		ViewButton btn22 = new ViewButton();
		btn22.setName("��Ҫ����");
		btn22.setType("view");
		btn22.setUrl("http://www.rejet.cn/TheHomeOfPet/orderMake.html");

		ClickButton btn31 = new ClickButton();
		btn31.setName("�������");
		btn31.setType("click");
		btn31.setKey("Corperation");

		ViewButton btn32 = new ViewButton();
		btn32.setName("��������");
		btn32.setType("view");
		btn32.setUrl("http://www.rejet.cn/TheHomeOfPet/login.html");
		
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("��Ʒ��Ϣ");
		mainBtn1.set_subButton(new Button[] { btn11, btn12 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("�ҵĶ���");
		mainBtn2.set_subButton(new Button[] { btn21, btn22 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("��������");
		mainBtn3.set_subButton(new Button[] { btn31,btn32 });

		Menu menu = new Menu();
		menu.set_button(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}

	public static void main(String[] args) {
		// �������û�Ψһƾ֤
		String appId = "wxd089794be087d33f";
		// �������û�Ψһƾ֤��Կ
		String appSecret = "6d87b730aa719a1764fcafc16b29d9a3";

		// ���ýӿڻ�ȡƾ֤
		Token token = CommonUtil.getToken(appId, appSecret);
		System.out.println(token.getAccessToken());

		if (null != token) {
			// �����˵�
			boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());

			// �жϲ˵��������
			if (result)
				log.info("�˵������ɹ���");
			else
				log.info("�˵�����ʧ�ܣ�");
		}
	}
}
