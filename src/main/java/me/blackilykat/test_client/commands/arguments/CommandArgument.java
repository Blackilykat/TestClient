package me.blackilykat.test_client.commands.arguments;

import me.blackilykat.test_client.commands.Argument;
import me.blackilykat.test_client.commands.Command;
import net.minecraft.text.Text;

public class CommandArgument extends Argument<Command> {
	public CommandArgument(String name) {
		super(name);
		hint = Text.literal("Command (help tp)");
	}
}
