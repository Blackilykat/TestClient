package me.blackilykat.test_client;

import me.blackilykat.test_client.commands.commands.*;
import me.blackilykat.test_client.exceptions.ExistingCommandException;
import me.blackilykat.test_client.gui.Gui;
import me.blackilykat.test_client.modules.op.AutoOpModule;
import me.blackilykat.test_client.modules.utility.AutoToolsModule;
import me.blackilykat.test_client.util.Keybinds;
import me.blackilykat.test_client.util.SharedVariables;
import me.blackilykat.test_client.util.TickRunnableHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// --uuid cf9f91987150442d8dcf2f8b90d0417f for skin but breaks offline servers that also support online auth
public class Main implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("test-client");
	public static final MinecraftClient MC = MinecraftClient.getInstance();



	@Override
	public void onInitialize() {
		TickRunnableHandler.registerTickEndRunnables();
		Keybinds.registerOpenGui();
		AutoToolsModule.registerListener();
		try {
			new HelpCommand();
			new SpeedCommand();
			new NameCommand();
			new AutoDeopCommand();
			new OpKillCommand();
			new CrashCommand();
			new SudoKickCommand();
			new AutoSudoKickCommand();
			new TestClientPrefixCommand();
		} catch (ExistingCommandException e) {
			throw new RuntimeException(e);
		}
		ClientTickEvents.END_WORLD_TICK.register(world -> {
			SharedVariables.elderGuardianParticleTimer--;
		});
		
	}
}