package me.blackilykat.test_client.commands.commands;

import me.blackilykat.test_client.commands.Command;
import me.blackilykat.test_client.commands.Subcommand;
import me.blackilykat.test_client.commands.arguments.StringArgument;
import me.blackilykat.test_client.exceptions.ExistingCommandException;
import me.blackilykat.test_client.modules.op.AutoDeopModule;
import me.blackilykat.test_client.util.Chat;

public class AutoDeopCommand extends Command {
	public AutoDeopCommand() throws ExistingCommandException {
		super("autodeop");
		withDescription("Manages the AutoDeop module");
		withSubcommand(new add());
		withSubcommand(new remove());
		withSubcommand(new list());
	}
	
	private static class add extends Subcommand {
		
		public add() throws ExistingCommandException {
			super("add");
			withArgument(new StringArgument("Username"));
			runs(args -> {
				if(args.length == 0) {
					Chat.sendRed("You need to insert a username!");
					return;
				}
				if(AutoDeopModule.players.contains(args[0])) {
					Chat.sendRed("That player is already in the AutoDeop list!");
					return;
				}
				AutoDeopModule.players.add(args[0]);
				Chat.sendGreen("Added " + args[0] + " to the AutoDeop list!");
			});
		}
	}
	private static class remove extends Subcommand {
		public remove() throws ExistingCommandException {
			super("remove");
			withArgument(new StringArgument("Username"));
			runs(args -> {
				if(args.length == 0) {
					Chat.sendRed("You need to insert a username!");
					return;
				}
				if(!AutoDeopModule.players.contains(args[0])) {
					Chat.sendRed("That player is not in the AutoDeop list!");
					return;
				}
				AutoDeopModule.players.remove(args[0]);
				Chat.sendGreen("Removed " + args[0] + " to the AutoDeop list!");
			});
		}
	}
	private static class list extends Subcommand {
		
		public list() throws ExistingCommandException {
			super("list");
			runs(args -> {
				Chat.sendGold("AutoDeop player list:");
				for (String player : AutoDeopModule.players) {
					Chat.send(" - " + player);
				}
			});
		}
	}
}
