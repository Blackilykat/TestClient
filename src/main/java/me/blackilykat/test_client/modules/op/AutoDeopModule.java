package me.blackilykat.test_client.modules.op;

import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.gui.components.Module;

import java.util.ArrayList;

public class AutoDeopModule extends Module {
	public static ArrayList<String> players = new ArrayList<>();
	public static AutoDeopModule instance = new AutoDeopModule();
	public AutoDeopModule() {
		super("Auto Deop");
	}
	
	public static void execute(String opper, String opped) {
		if(!instance.isEnabled) return;
		if(players.contains(opped)) {
			Main.MC.getNetworkHandler().sendChatCommand("deop " + opped);
		}
	}
}
