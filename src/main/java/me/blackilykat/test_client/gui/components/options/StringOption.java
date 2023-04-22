package me.blackilykat.test_client.gui.components.options;

import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.gui.components.Option;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class StringOption extends Option<String> {
	TextFieldWidget textFieldWidget = new TextFieldWidget(Main.MC.textRenderer, 0, 0, 200, 20, Text.empty());
	public StringOption(String name, String defaultValue) {
		super(name, defaultValue);
		textFieldWidget.setText(defaultValue);
		textFieldWidget.setChangedListener(text -> {
			optionValue = text;
		});
		widget = textFieldWidget;
	}
	
}
