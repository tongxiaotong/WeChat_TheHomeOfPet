package com.tongrui.menu;

public class ComplexButton extends Button {

	private Button[] sub_button;

	public Button[] get_subButton() {
		return this.sub_button;
	}

	public Button[] set_subButton(Button[] sub_button) {
		return this.sub_button = sub_button;
	}
}
