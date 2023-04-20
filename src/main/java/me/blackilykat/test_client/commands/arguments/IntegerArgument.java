package me.blackilykat.test_client.commands.arguments;

import me.blackilykat.test_client.commands.Argument;
import net.minecraft.text.Text;

public class IntegerArgument extends Argument<Integer> {
	public IntegerArgument(String name) {
		super(name);
		hint = Text.literal("Integer(number)");
	}
	
}
