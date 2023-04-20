package me.blackilykat.test_client.commands.commands;

import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.commands.Command;
import me.blackilykat.test_client.commands.arguments.StringArgument;
import me.blackilykat.test_client.exceptions.ExistingCommandException;
import me.blackilykat.test_client.util.Chat;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.CreativeInventoryActionC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class OpKillCommand extends Command {
	int timer = -1;
	String username;
	int slot = 0;
	ItemStack previousItemStack = null;
	public OpKillCommand() throws ExistingCommandException {
		super("opkill");
		withArgument(new StringArgument("Username"));
		withDescription("Kills a creative mode player using the damage command for servers that block /kill");
		ClientTickEvents.END_WORLD_TICK.register(world -> {
			if(timer == 0) {
				previousItemStack = Main.MC.player.getMainHandStack();
				ItemStack killItemStack = new ItemStack(Items.SALMON);
				killItemStack.setCustomName(Text.literal("TestClient OpKill").formatted(Formatting.GOLD));
				slot = Main.MC.player.getInventory().selectedSlot;
				Main.MC.getNetworkHandler().sendPacket(new CreativeInventoryActionC2SPacket(36 + slot, killItemStack));
				Main.MC.getNetworkHandler().sendCommand("gamemode survival " + username);
			}  else if(timer == 2) {
				Main.MC.getNetworkHandler().sendCommand("damage " + username + " 10000 minecraft:player_attack by " + Main.MC.getSession().getUsername());
			} else if(timer == 4) {
				Main.MC.getNetworkHandler().sendCommand("gamemode creative " + username);
			} else if(timer == 5) {
				Main.MC.getNetworkHandler().sendPacket(new CreativeInventoryActionC2SPacket(36 + slot, previousItemStack));
				Chat.sendGreen("OpKilled " + username + "!");
				timer = -1;
			}
			if(timer != -1) timer++;
			// /execute at 0 
		});
		runs(args -> {
			if(args.length==0) {
				Chat.sendRed("You need to insert an username!");
				return;
			}
			for (AbstractClientPlayerEntity player : Main.MC.world.getPlayers()) {
				if(player.getName().getString().equals(args[0])) {
					username = args[0];
					if(timer == -1) timer = 0;
					else Chat.sendRed("Another kill is in progress!");
					return;
				}
			}
			Chat.sendRed("Player " + args[0] + " doesn't exist!");
		});
	}
}
