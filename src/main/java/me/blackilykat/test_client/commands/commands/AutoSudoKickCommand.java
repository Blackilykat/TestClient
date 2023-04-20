package me.blackilykat.test_client.commands.commands;

import me.blackilykat.test_client.commands.Command;
import me.blackilykat.test_client.commands.Subcommand;
import me.blackilykat.test_client.commands.arguments.StringArgument;
import me.blackilykat.test_client.exceptions.ExistingCommandException;
import me.blackilykat.test_client.modules.op.AutoDeopModule;
import me.blackilykat.test_client.modules.op.AutoSudoKickModule;
import me.blackilykat.test_client.util.Chat;

import java.util.ArrayList;

public class AutoSudoKickCommand extends Command {
	ArrayList<String> playerList = new ArrayList<>();
	public AutoSudoKickCommand() throws ExistingCommandException {
		super("autosudokick");
		withDescription("Manages the Auto SudoKick module.");
		withSubcommand(new AutoSudoKickCommand.add());
		withSubcommand(new AutoSudoKickCommand.remove());
		withSubcommand(new AutoSudoKickCommand.list());
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
				if(AutoSudoKickModule.players.contains(args[0])) {
					Chat.sendRed("That player is already in the Auto SudoKick list!");
					return;
				}
				AutoSudoKickModule.players.add(args[0]);
				Chat.sendGreen("Added " + args[0] + " to the Auto SudoKick list!");
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
				if(!AutoSudoKickModule.players.contains(args[0])) {
					Chat.sendRed("That player is not in the Auto SudoKick list!");
					return;
				}
				AutoSudoKickModule.players.remove(args[0]);
				Chat.sendGreen("Removed " + args[0] + " to the Auto SudoKick list!");
			});
		}
	}
	private static class list extends Subcommand {
		
		public list() throws ExistingCommandException {
			super("list");
			runs(args -> {
				Chat.sendGold("Auto SudoKick player list:");
				for (String player : AutoSudoKickModule.players) {
					Chat.send(" - " + player);
				}
			});
		}
	}
}
