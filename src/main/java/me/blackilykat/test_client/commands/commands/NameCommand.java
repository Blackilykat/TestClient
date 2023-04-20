package me.blackilykat.test_client.commands.commands;

import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.commands.Command;
import me.blackilykat.test_client.commands.arguments.StringArgument;
import me.blackilykat.test_client.exceptions.ExistingCommandException;
import me.blackilykat.test_client.util.Chat;
import me.blackilykat.test_client.util.SharedVariables;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class NameCommand extends Command {
	public NameCommand() throws ExistingCommandException {
		super("name");
		withArgument(new StringArgument("New name"));
		withDescription("Changes the username in offline mode");
		runs(args -> {
			if(args.length==0) {
				Chat.send(Text.literal("You must put a name!").formatted(Formatting.RED));
				return;
			}
			if(args[0].length() > 16) {
				Chat.send(Text.literal("Maximum name length is 16 characters.").formatted(Formatting.RED));
				return;
			}
			SharedVariables.username = args[0];
			if(Main.MC.getCurrentServerEntry()==null) {
				Chat.send(Text.literal("You're not connected to a server, somehow. How are you even seeing this??").formatted(Formatting.RED));
				return;
			}
			
			ServerInfo serverEntry = Main.MC.getCurrentServerEntry();
			Main.MC.world.disconnect();
			Main.MC.disconnect();
			ConnectScreen.connect(null, Main.MC, ServerAddress.parse(serverEntry.address), serverEntry);
		});
		
		
	}
}
