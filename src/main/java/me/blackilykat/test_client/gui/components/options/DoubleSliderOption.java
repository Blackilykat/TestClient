package me.blackilykat.test_client.gui.components.options;

import me.blackilykat.test_client.gui.components.Option;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;

public class DoubleSliderOption extends Option<Double> {
	public double maxValue = 100;
	public double minValue = 0;
	int roundTo = 4;
	SliderWidget sliderWidget = new SliderWidget(0, 0, 100, 20, Text.literal("text ig"), 0.0) {
		@Override
		protected void updateMessage() {
			setMessage(Text.literal(round((value * (maxValue - minValue) + minValue), roundTo)+""));
		}
		
		@Override
		protected void applyValue() {
			optionValue = round((value * (maxValue - minValue) + minValue), roundTo);
		}
		
		public double getValue() {
			return value;
		}
	};
	
	public DoubleSliderOption(String name, double defaultValue) {
		super(name, defaultValue);
		optionValue = defaultValue;
		widget = sliderWidget;
	}
	public DoubleSliderOption(String name, double defaultValue, double minValue, double maxValue) {
		super(name, defaultValue);
		optionValue = defaultValue;
		widget = sliderWidget;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	public DoubleSliderOption(String name, double defaultValue, double minValue, double maxValue, int round) {
		super(name, defaultValue);
		optionValue = defaultValue;
		widget = sliderWidget;
		this.minValue = minValue;
		this.maxValue = maxValue;
		roundTo = round;
	}
	
	public static double round(double value, int places) {
		if (places < 0) return value;
		
		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
}
