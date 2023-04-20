package me.blackilykat.test_client.commands.arguments;

import me.blackilykat.test_client.commands.Argument;
import net.minecraft.text.Text;

public class DoubleArgument extends Argument<Double> {
	public DoubleArgument(String name) {
		super(name);
		hint = Text.literal("Integer(number)");
	}
	
}
