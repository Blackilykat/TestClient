package me.blackilykat.test_client.modules.utility;

import me.blackilykat.test_client.gui.components.Module;

public class BlockGuardianParticlesModule extends Module {
	public static BlockGuardianParticlesModule instance = new BlockGuardianParticlesModule();
	
	public BlockGuardianParticlesModule() {
		super("No guardian particles");
		isEnabled = true;
	}
}
