package me.blackilykat.test_client.commands.commands;

import me.blackilykat.test_client.commands.Command;
import me.blackilykat.test_client.commands.Subcommand;
import me.blackilykat.test_client.commands.arguments.CommandArgument;
import me.blackilykat.test_client.exceptions.ExistingCommandException;
import me.blackilykat.test_client.util.Chat;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class HelpCommand extends Command {
	CommandArgument command = new CommandArgument("Command");
	public HelpCommand() throws ExistingCommandException {
		super("help");
		withArgument(command);
		withDescription("shows this message");
		runs(args -> {
			if(args.length==0) {
				Chat.sendGold("---TestClient help message---");
				MutableText message = Text.literal("");
				for (Command commandInList : Command.commandList) {
					message.append(commandInList.name)
							.append(": ")
							.append(commandInList.description);
					Chat.send(message);
					message = Text.literal("");
//					boolean isFirstSubcommand = true;
//					for (Subcommand subcommand : commandInList.subcommandList) {
//						if(isFirstSubcommand) message.append("    Subcommands:");
//						isFirstSubcommand = false;
//						message.append("     - ")
//								.append(subcommand.name)
//								.append("\n")
//					}
					
				}
				Chat.sendGold("---TestClient help message---");
			}
		});
	}
}
