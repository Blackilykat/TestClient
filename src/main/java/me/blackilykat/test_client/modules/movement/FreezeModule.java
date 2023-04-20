package me.blackilykat.test_client.modules.movement;

import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.gui.components.Module;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.math.Vec3d;

public class FreezeModule extends Module {
	public static Vec3d mantainPos;
	public FreezeModule() {
		super("Freeze");
		activateRunnable = () -> {
			mantainPos = Main.MC.player.getPos();
		};
		endTickRunnable = new Runnable() {
			@Override
			public void run() {
				Main.MC.player.setVelocity(0, 0, 0);
				Main.MC.player.setPosition(mantainPos);
			}
		};
	}
}
