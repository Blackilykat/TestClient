package me.blackilykat.test_client.modules.combat;

import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.gui.components.Module;
import me.blackilykat.test_client.util.WorldUtil;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.text.Text;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.math.Box;

import java.util.ArrayList;
import java.util.Objects;

public class KillAuraModule extends Module {
	ArrayList<EntityType> attackEntityList = new ArrayList<>();
	double reach = 10;
	static boolean debug = false;
	public KillAuraModule() {
		super("KillAura");
		attackEntityList.add(EntityType.ZOMBIE);
		endTickRunnable = () -> {
			
			debug("Ticked!");
			for (Entity entity : Main.MC.world.getOtherEntities(Main.MC.player, Box.of(Main.MC.player.getEyePos(), reach / 2, reach / 2, reach / 2))) {
				debug("Found entity " + entity.getType().toString());
				for (EntityType entityType : attackEntityList) {
					if(Objects.equals(entity.getType().toString(), entityType.toString())) {
						debug("And is of the correct entity type");
						Main.MC.inGameHud.setSubtitle(Text.literal(entity.getType().toString()));
						Main.MC.player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, entity.getPos());
						if(Main.MC.player.getAttackCooldownProgress(0)==1) {
							Main.MC.interactionManager.attackEntity(Main.MC.player, entity);
						}
						return;
					}
				}
			}
//			for (Entity entity : Main.MC.world.getEntities()) {
//				if(entity.equals(Main.MC.player)) return;
//				debug(WorldUtil.getPlayerDistance(entity.getPos()) + " blocks away");
//				if(WorldUtil.getPlayerDistance(entity.getPos()) > reach) return;
//				debug("Which is close enough to the player");
//				Main.MC.inGameHud.setTitle(Text.literal(entity.getType().toString()));
//				debug("Found entity " + entity.getType().toString());
//				for (EntityType entityType : attackEntityList) {
//					if(Objects.equals(entity.getType().toString(), entityType.toString())) {
//						debug("And is of the correct entity type");
//						Main.MC.inGameHud.setSubtitle(Text.literal(entity.getType().toString()));
////						Main.MC.player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, entity.getPos());
//						Main.MC.interactionManager.attackEntity(Main.MC.player, entity);
//					}
//				}
//			}
		};
	}
	private static void debug(String message) {
		if(debug) Main.LOGGER.info("[KillAura] " + message);
	}
	
}
