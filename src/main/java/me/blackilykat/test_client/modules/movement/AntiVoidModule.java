package me.blackilykat.test_client.modules.movement;

import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.gui.components.Module;

public class AntiVoidModule extends Module {
	public static AntiVoidModule instance = new AntiVoidModule();
	public AntiVoidModule() {
		super("Anti void");
		endTickRunnable = () -> {
			if(Main.MC.player.getY() < -64) {
				Main.MC.player.setPosition(Main.MC.player.getX(), -64, Main.MC.player.getZ());
				Main.MC.player.setOnGround(true);
			}
		};
		isEnabled = true;
	}
}
