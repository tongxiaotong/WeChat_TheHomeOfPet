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
 * 菜单管理器类
 * 
 * @author tongrui
 * @date 2017-03-09
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	/**
	 * 定义菜单结构
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		ClickButton btn11 = new ClickButton();
		btn11.setName("生态美味");
		btn11.setType("click");
		btn11.setKey("STMW");

		ClickButton btn12 = new ClickButton();
		btn12.setName("宠物业务");
		btn12.setType("click");
		btn12.setKey("CWYW");

		

		ViewButton btn21 = new ViewButton();
		btn21.setName("订单详情");
		btn21.setType("view");
		btn21.setUrl("http://www.rejet.cn/TheHomeOfPet/my_order.html");

		ViewButton btn22 = new ViewButton();
		btn22.setName("我要订货");
		btn22.setType("view");
		btn22.setUrl("http://www.rejet.cn/TheHomeOfPet/orderMake.html");

		ClickButton btn31 = new ClickButton();
		btn31.setName("商务合作");
		btn31.setType("click");
		btn31.setKey("Corperation");

		ViewButton btn32 = new ViewButton();
		btn32.setName("订单管理");
		btn32.setType("view");
		btn32.setUrl("http://www.rejet.cn/TheHomeOfPet/login.html");
		
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("商品信息");
		mainBtn1.set_subButton(new Button[] { btn11, btn12 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("我的订单");
		mainBtn2.set_subButton(new Button[] { btn21, btn22 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("关于我们");
		mainBtn3.set_subButton(new Button[] { btn31,btn32 });

		Menu menu = new Menu();
		menu.set_button(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wxd089794be087d33f";
		// 第三方用户唯一凭证密钥
		String appSecret = "6d87b730aa719a1764fcafc16b29d9a3";

		// 调用接口获取凭证
		Token token = CommonUtil.getToken(appId, appSecret);
		System.out.println(token.getAccessToken());

		if (null != token) {
			// 创建菜单
			boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());

			// 判断菜单创建结果
			if (result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败！");
		}
	}
}
