package me.blackilykat.test_client.commands;

import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.exceptions.ExistingCommandException;
import me.blackilykat.test_client.util.ArrayUtil;

import java.util.ArrayList;

public class Command {
	public static ArrayList<Command> commandList = new ArrayList<>();
	public ArrayList<Subcommand> subcommandList = new ArrayList<>();
	public ArrayList<Argument> argumentList = new ArrayList<>();
	public String name;
	private static int lastId = 0;
	public int id;
	public static boolean printCommandOrigins = true;
	public SentCommand result;
	public String description;
	
	public Command withSubcommand(Subcommand subcommand) throws ExistingCommandException {
		for (Subcommand subcommandInList : subcommandList) {
			if(subcommandInList.name.equals(subcommand.name)) {
				throw new ExistingCommandException(subcommand, subcommandInList, this);
				// there must only be one command with the same name
			}
		}
		subcommandList.add(subcommand);
		return this;
	}
	
	public Command(String name) throws ExistingCommandException {
		this.name = name;
		lastId++; id = lastId;
		if(printCommandOrigins) Main.LOGGER.info(
				new Throwable().getStackTrace()[1].getClassName()
				+ "." + new Throwable().getStackTrace()[1].getMethodName()
				+ " (line " + new Throwable().getStackTrace()[1].getLineNumber() + ") "
				+ " created new "
				+ (this instanceof Subcommand ? "subcommand " : "command ")
				+ name + " with id " + id
		);
		// "me.blackilykat.test_client.commands.SettingCommand.<init> (line 16) created new command setting with id 1
		if(this instanceof Subcommand) return;
		for (Command command : commandList) {
			if(command.name.equals(this.name)) {
				// there must only be one command with the same name
				throw new ExistingCommandException(this, command);
			}
		}
		commandList.add(this);
	}
	
	public Command runs(SentCommand sentCommand) {
		result = sentCommand;
		return this;
	}
	
	public Command withDescription(String description) {
		this.description = description;
		return this;
	}
	
	public Command withArgument(Argument argument) {
		argumentList.add(argument);
		return this;
	}
	
	public void run(String[] args) {
		boolean foundSubcommand = false;
		for(Subcommand subcommand : subcommandList) {
			if(args.length > 0 && subcommand.name.equals(args[0])) {
				args = ArrayUtil.removeFirst(args, 1);
				subcommand.run(args);
				foundSubcommand = true;
			}
		}
		if(!foundSubcommand && result != null) result.run(args);
	}
}
