package me.blackilykat.test_client.commands.commands;

import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.commands.Command;
import me.blackilykat.test_client.exceptions.ExistingCommandException;

public class TestClientPrefixCommand extends Command {
	public TestClientPrefixCommand() throws ExistingCommandException {
		super("tcprefix");
		withDescription("Changes the prefix to '&8[&bTest&3Client&8]'. If it has an argument, it will sudo that player to change their prefix.");
		runs(args -> {
			if(args.length == 0) {
				Main.MC.getNetworkHandler().sendCommand("prefix &8[&bTest&3Client&8]");
				return;
			}
			Main.MC.getNetworkHandler().sendCommand("sudo " + args[0] + " prefix &8[&bTest&3Client&8]");
		});
	}
}
