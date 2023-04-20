package me.blackilykat.test_client.modules.movement;

import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.gui.components.Module;
import net.minecraft.util.math.Vec3d;

public class AirJumpModule extends Module {
	boolean wasSpacePressed;
	public AirJumpModule() {
		super("Air Jump");
		wasSpacePressed = false;
		endTickRunnable = new Runnable() {
			@Override
			public void run() {
				if(Main.MC.options.jumpKey.wasPressed() && !wasSpacePressed) {
					wasSpacePressed = true;
					Vec3d v = Main.MC.player.getVelocity();
					Main.MC.player.setVelocity(v.x, 0.35, v.z);
				} else if (!Main.MC.options.jumpKey.wasPressed()) {
					wasSpacePressed = false;
				}
			}
		};
	}
}
