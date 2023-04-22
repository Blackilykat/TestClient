package me.blackilykat.test_client.modules.utility;

import me.blackilykat.test_client.gui.components.Module;
import me.blackilykat.test_client.gui.components.options.StringOption;
import me.blackilykat.test_client.util.Chat;

public class BlockGuardianParticlesModule extends Module {
	public static BlockGuardianParticlesModule instance = new BlockGuardianParticlesModule();
	
	public BlockGuardianParticlesModule() {
		super("No guardian particles");
		isEnabled = true;
		withOption(new StringOption("Message", "Test option"));
		activateRunnable = () -> {
			Chat.send("" + optionList.get(0).optionValue);
		};
	}
	
}
