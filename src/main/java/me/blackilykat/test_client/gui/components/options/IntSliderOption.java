package me.blackilykat.test_client.gui.components.options;

import me.blackilykat.test_client.gui.components.Option;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;

public class IntSliderOption extends Option<Integer> {
	public int maxValue = 100;
	public int minValue = 0;
	SliderWidget sliderWidget = new SliderWidget(0, 0, 100, 20, Text.literal("text ig"), 0.0) {
		@Override
		protected void updateMessage() {
			setMessage(Text.literal(((int) (value * (maxValue - minValue) + minValue))+""));
		}
		
		@Override
		protected void applyValue() {
			optionValue = (int) (value * (maxValue - minValue) + minValue);
		}
		
		public double getValue() {
			return value;
		}
	};
	
	public IntSliderOption(String name, int defaultValue) {
		super(name, defaultValue);
		optionValue = defaultValue;
		widget = sliderWidget;
	}
	public IntSliderOption(String name, int defaultValue, int minValue, int maxValue) {
		super(name, defaultValue);
		optionValue = defaultValue;
		widget = sliderWidget;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
}
