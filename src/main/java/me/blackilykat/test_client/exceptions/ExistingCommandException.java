package me.blackilykat.test_client.exceptions;

import me.blackilykat.test_client.commands.Command;
import me.blackilykat.test_client.commands.Subcommand;

public class ExistingCommandException extends Exception{
	public ExistingCommandException(Command thisCommand, Command existingCommand) {
		super(
				"Command "
				+ thisCommand.name
				+ "(id:" + thisCommand.id + ")"
				+ " already exists!"
				+ "(id:" + existingCommand.id + ")"
		);
		/*
		 * new Command("setting");
		 * new Command("setting"); // throws the error
		 *
		 * "Command setting(id:1) already exists! (id:2)
		 */
	}
	public ExistingCommandException(Subcommand thisSubcommand, Subcommand existingSubcommand, Command parent) {
		super(
				"Subcommand "
				+ thisSubcommand.name
				+ "(" + thisSubcommand.id + ")"
				+ "for " + parent.name
				+ "(" + parent.id + ")"
				+ " already exists!"
				+ "(id:" + existingSubcommand.id + ")"
		);
		/*
		 * new Command("setting")
		 *     .withSubcommand(new Subcommand("speed"))
		 *     .withSubcommand(new Subcommand("speed")) // throws the error
		 *
		 * would become
		 *
		 * "Subcommand speed(id:3) for setting(id:1) already exists! (id:2)"
		 */
	}
}
