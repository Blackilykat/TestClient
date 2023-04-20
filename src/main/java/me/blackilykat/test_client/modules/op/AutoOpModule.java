package me.blackilykat.test_client.modules.op;

import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.gui.components.Module;
import me.blackilykat.test_client.util.Chat;
import me.blackilykat.test_client.util.SharedVariables;
import me.blackilykat.test_client.util.WorldUtil;
import net.minecraft.server.MinecraftServer;

public class AutoOpModule extends Module {
	public static AutoOpModule instance = new AutoOpModule();
	public AutoOpModule() {
		super("Auto OP");
//		endTickRunnable = () -> {
//			if(!WorldUtil.isPlayerOP()) Main.MC.getNetworkHandler().sendCommand("op " + Main.MC.getSession().getUsername());
//		};
	}
	public void run(MinecraftServer server) {
		if(!WorldUtil.isPlayerOP(server)) Main.MC.getNetworkHandler().sendCommand("op " + Main.MC.getSession().getUsername());
	
	}
}
