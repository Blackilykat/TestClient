package me.blackilykat.test_client.util;

import me.blackilykat.test_client.gui.Gui;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Keybinds {
	private static KeyBinding testKeyBinding;
	private static KeyBinding openGuiKeyBinding;
	public static void registerTest() {
		// create the keybind
		testKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.test_client.test", // the translation key for the keybind itself
				InputUtil.Type.KEYSYM, // KEYSYM for keyboard, MOUSE for mouse
				GLFW.GLFW_KEY_RIGHT_SHIFT, // the key
				"category.test_client.test" // The translation key for the keybind category
		));
		// register it
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (testKeyBinding.wasPressed()) {
				// here goes the code that gets executed when it's pressed
				Chat.send("Test button has been pressed!");
			}
		});
	}
	public static void registerOpenGui() {
		// create the keybind
		openGuiKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.test_client.open_gui", // the translation key for the keybind itself
				InputUtil.Type.KEYSYM, // KEYSYM for keyboard, MOUSE for mouse
				GLFW.GLFW_KEY_RIGHT_SHIFT, // the key
				"category.test_client.test_client" // The translation key for the keybind category
		));
		// register it
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (openGuiKeyBinding.wasPressed()) {
				Gui.open();
			}
		});
	}
}
