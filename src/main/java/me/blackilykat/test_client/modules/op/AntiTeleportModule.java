package me.blackilykat.test_client.modules.op;

import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.gui.components.Module;
import me.blackilykat.test_client.util.Chat;
import net.minecraft.util.math.Vec3d;

public class AntiTeleportModule extends Module {
	private static Vec3d previousPos;
	private static Vec3d currentPos;
	public static int timer = 0;
	public AntiTeleportModule() {
		super("AntiTP");
		endTickRunnable = new Runnable() {
			@Override
			public void run() {
				if(Main.MC.player != null) {
					currentPos = Main.MC.player.getPos();
					if(previousPos == null) {
						previousPos = currentPos;
						return;
					}
					if(getDistance(currentPos, previousPos) > 10 && timer <= 0) {
						String command = "tp "+previousPos.x+" "+previousPos.y+" "+previousPos.z;
						Main.MC.player.networkHandler.sendChatCommand(command);
						Chat.send("[AntiTP] Ran `" + command + "`");
						timer = 10;
					}
					previousPos = currentPos;
					timer--;
					
					
				}
				
			}
		};
		isEnabled = true;
	}
	public double getDistance(Vec3d first, Vec3d second) {
		double distanceX = 0, distanceY = 0, distanceZ = 0;
		distanceX = first.x - second.x;
		if(distanceX<0) distanceX = -distanceX;
		distanceY = first.y - second.y;
		if(distanceY<0) distanceY = -distanceY;
		distanceZ = first.z - second.z;
		if(distanceZ<0) distanceZ = -distanceZ;
		
		return distanceX + distanceY + distanceZ;
		
		
		
	}
}
