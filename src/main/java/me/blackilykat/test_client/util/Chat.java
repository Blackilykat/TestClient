package me.blackilykat.test_client.util;

import me.blackilykat.test_client.Main;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class Chat {
	public static final MinecraftClient MC = Main.MC;
	
	public static void send(String message) {
		MC.inGameHud.getChatHud().addMessage(Text.of(message));
	}
	public static void send(Text message) {
		MC.inGameHud.getChatHud().addMessage(message);
	}
	public static void sendRed(String message) {
		send(Text.literal(message).formatted(Formatting.RED));
	}
	public static void sendGreen(String message) {
		send(Text.literal(message).formatted(Formatting.GREEN));
	}
	public static void sendGold(String message) {
		send(Text.literal(message).formatted(Formatting.GOLD));
	}
	
	
}
