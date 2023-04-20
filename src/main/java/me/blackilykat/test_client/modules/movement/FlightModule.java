package me.blackilykat.test_client.modules.movement;

import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.gui.components.Module;

public class FlightModule extends Module {
	public FlightModule() {
		super("Flight");
		endTickRunnable = new Runnable() {
			@Override
			public void run() {
				Main.MC.player.getAbilities().allowFlying = true;
			}
		};
		deactivateRunnable = new Runnable() {
			@Override
			public void run() {
				if(Main.MC.player != null)
					Main.MC.player.getAbilities().allowFlying = Main.MC.player.getAbilities().creativeMode;
			}
		};
		
	}
	
	
	
}
