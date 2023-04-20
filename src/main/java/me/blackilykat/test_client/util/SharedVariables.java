package me.blackilykat.test_client.util;

import net.minecraft.server.MinecraftServer;

/**
 * This class is used when there isn't a specific class where you'd store a variable, but you still need it somewhere.
 * It's mostly used by mixins which can't hold public variables themselves.
 */
public class SharedVariables {
	/**
	 * The first time the player starts a message with '.', the game will warn them about commands having a '\' prefix.
	 * This is here to make sure starting messages with '.' isn't entirely blocked.
	 */
	public static boolean WarnedOfBackslashCommands = false;
	/**
	 * The username that gets returned in Session.
	 */
	public static String username = "Dev_Blackilykat";
	public static MinecraftServer serverConnectedTo = null;
	public static int elderGuardianParticleTimer = 0;
}
