package me.blackilykat.test_client.util;

import me.blackilykat.test_client.Main;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.concurrent.atomic.AtomicReference;

public class WorldUtil {
	private static Block blockBreaking = null;
	private static MinecraftClient MC = Main.MC;
	
	public static Block getBlockBreaking() {
		return blockBreaking;
	}
	public static void setBlockBreaking(Block block) {
		blockBreaking = block;
	}
	
	public static Block getBlockLooking() {
		Vec3d pos = MinecraftClient.getInstance().crosshairTarget.getPos();
		BlockPos blockPos = BlockPos.ofFloored(pos.x, pos.y, pos.z);
		if(MC.world.getBlockState(blockPos) != null) return MC.world.getBlockState(blockPos).getBlock();
		return null; // only if this is called while not in a world
	}
	
	public static double getPlayerDistance(Vec3d position) {
		// crash-proof
		if(Main.MC.player == null) return 0;
		
		Vec3d playerPos = Main.MC.player.getPos();
		double distanceX, distanceY, distanceZ;
		
		distanceX = position.x - playerPos.x;
		if(distanceX<0) distanceX = -distanceX;
		distanceY = position.y - playerPos.y;
		if(distanceY<0) distanceY = -distanceY;
		distanceZ = position.z - playerPos.z;
		if(distanceZ<0) distanceZ = -distanceZ;
		
		return distanceX + distanceY + distanceZ;
	}
	
	public static boolean isPlayerOP() {
		if(SharedVariables.serverConnectedTo==null) return false;
		Chat.send(SharedVariables.serverConnectedTo.getOpPermissionLevel()+"");
		return SharedVariables.serverConnectedTo.getOpPermissionLevel() > 0;
	}
	public static boolean isPlayerOP(MinecraftServer server) {
		if(server==null) return false;
		Chat.send(server.getOpPermissionLevel()+"");
		return server.getOpPermissionLevel() > 0;
	}
	
}
