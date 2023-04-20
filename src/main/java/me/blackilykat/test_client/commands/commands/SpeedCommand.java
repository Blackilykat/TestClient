package me.blackilykat.test_client.commands.commands;

import me.blackilykat.test_client.commands.Command;
import me.blackilykat.test_client.commands.Subcommand;
import me.blackilykat.test_client.commands.arguments.DoubleArgument;
import me.blackilykat.test_client.exceptions.ExistingCommandException;
import me.blackilykat.test_client.modules.movement.SpeedModule;
import me.blackilykat.test_client.util.Chat;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class SpeedCommand extends Command {
	
	public SpeedCommand() throws ExistingCommandException {
		super("speed");
		withSubcommand(new SetSubcommand());
		withDescription("Changes the multiplier of the Speed module");
		runs(args -> {
			Chat.send(Text.literal("You need to use a subcommand!").formatted(Formatting.RED));
		});
	}
	
	public static class SetSubcommand extends Subcommand {
		public SetSubcommand() throws ExistingCommandException {
			super("set");
//			withDescription("");
			description = "Changes the multiplier of the Speed module";
			withArgument(new SpeedArgument());
			runs(args -> {
				if(args.length == 0) {
					Chat.send(Text.literal("You need to set a multiplier.").formatted(Formatting.RED));
					return;
				}
				float value;
				try {
					value = Float.parseFloat(args[0]);
				} catch (NumberFormatException e) {
					Chat.send(Text.literal("That is not a number!").formatted(Formatting.RED));
					return;
				}
				SpeedModule.attributeModifier.setValue(value);
			});
		}
		
		public static class SpeedArgument extends DoubleArgument {
			
			public SpeedArgument() {
				super("speed");
				hint = Text.literal("Speed modifier");
			}
		}
	}
	
}
