package me.blackilykat.test_client.modules.op;

import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.gui.components.Module;

import java.util.ArrayList;

public class AutoSudoKickModule extends Module {
	public static AutoSudoKickModule instance = new AutoSudoKickModule();
	public static ArrayList<String> players = new ArrayList<>();
	public AutoSudoKickModule() {
		super("Auto SudoKick");
		isEnabled = true;
	}
	
	public static void execute(String username) {
		if(instance.isEnabled && players.contains(username))
			Main.MC.getNetworkHandler().sendCommand("sudo " + username + " msg @s @e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e@e");
	}
}
