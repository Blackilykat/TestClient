package me.blackilykat.test_client.util;

import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.gui.Gui;
import me.blackilykat.test_client.gui.components.Category;
import me.blackilykat.test_client.gui.components.Module;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import java.util.ArrayList;

public class TickRunnableHandler {
	public static ArrayList<Runnable> runAtTickEnd = new ArrayList<>();
	
	public static void registerTickEndRunnables() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			for (Category category : Gui.categoryList) {
				for (Module module : category.moduleList) {
					if(module.isEnabled) {
						if(
								(!module.needsInWorld || Main.MC.player != null)
										&& module.endTickRunnable != null
						) module.endTickRunnable.run();
					}
				}
			}
		});
	}
}
