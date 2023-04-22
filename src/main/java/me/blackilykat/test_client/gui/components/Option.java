package me.blackilykat.test_client.gui.components;


import net.minecraft.client.gui.widget.ClickableWidget;

public abstract class Option<ValueType> {
	public String name;
	public ValueType optionValue;
	public ClickableWidget widget;
	public Option(String name, ValueType defaultValue) {
		this.name = name;
		optionValue = defaultValue;
	}
	
}
