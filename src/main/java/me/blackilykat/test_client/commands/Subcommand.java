package me.blackilykat.test_client.commands;

import me.blackilykat.test_client.exceptions.ExistingCommandException;

public class Subcommand extends Command{
	public Subcommand(String name) throws ExistingCommandException {
		super(name);
	}
}
