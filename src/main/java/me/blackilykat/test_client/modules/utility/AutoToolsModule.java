package me.blackilykat.test_client.modules.utility;

import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.gui.components.Module;
import me.blackilykat.test_client.gui.components.options.StringOption;
import me.blackilykat.test_client.util.Chat;
import me.blackilykat.test_client.util.WorldUtil;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.RaycastContext;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AutoToolsModule extends Module {
	public static AutoToolsModule instance = new AutoToolsModule();
	
	public AutoToolsModule() {
		super("Auto tools");
		// it's all in the event listener below
	}
	
	public static void registerListener() {
		Materials.initialize();
		Tools.initialize();
		AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
			WorldUtil.setBlockBreaking(world.getBlockState(pos).getBlock());
			if(instance.isEnabled) {
//                Chat.send("Breaking: " + WorldUtil.getBlockBreaking().getTranslationKey());
				switchToToolFor(WorldUtil.getBlockBreaking().getDefaultState().getMaterial());
			}
			return ActionResult.PASS;
		});
	}
	
	public static void switchToToolFor(Material material) {
		/*
		 * 0 = none
		 * 1 = pickaxe
		 * 2 = axe
		 * 3 = hoe
		 * 4 = sword
		 * 5 = shovel
		 * 6 = shears
		 */
		int tool = 0;
		if(Materials.pickaxe.contains(material)) tool = 1;
		if(Materials.axe.    contains(material)) tool = 2;
		if(Materials.hoe.    contains(material)) tool = 3;
		if(Materials.sword.  contains(material)) tool = 4;
		if(Materials.shovel. contains(material)) tool = 5;
		if(Materials.shears. contains(material)) tool = 6;
		
		switch (tool) {
			case 0 -> Chat.send("This block has no tool.");
			case 1 -> Chat.send("This block needs a pickaxe.");
			case 2 -> Chat.send("This block needs an axe.");
			case 3 -> Chat.send("This block needs a hoe.");
			case 4 -> Chat.send("This block needs a sword.");
			case 5 -> Chat.send("This block needs a shovel.");
			case 6 -> Chat.send("This block needs shears.");
		}
		switchToTool(tool);
	}
	public static void switchToTool(int tool) {
		ArrayList<Item> tools = new ArrayList<>();
		switch(tool) {
			case 0 -> {return;}
			case 1 -> tools = Tools.pickaxe;
			case 2 -> tools = Tools.axe;
			case 3 -> tools = Tools.hoe;
			case 4 -> tools = Tools.sword;
			case 5 -> tools = Tools.shovel;
			case 6 -> tools = Tools.shears;
		}
		int i = 0;
		for (ItemStack itemStack : Main.MC.player.getInventory().main) {
			if(tools.contains(itemStack.getItem())) {
//                Chat.send(i + "You have that tool!");
				if(i < 9) {
					Main.MC.player.getInventory().selectedSlot = i;
					Chat.send(Text.empty()
							.append("Switched to ")
							.append(itemStack.toHoverableText())
							.append(" in slot ")
							.append(""+i)
							.append(" in your hotbar.")
					);
				}
			}
			// else Chat.send(Main.MC.player.getInventory().main.indexOf(itemStack) + "You do not have that tool!");
			i++;
		}
	}
	
	public static class Tools {
		public static final ArrayList<Item> pickaxe = new ArrayList<>();
		public static final ArrayList<Item> axe = new ArrayList<>();
		public static final ArrayList<Item> sword = new ArrayList<>();
		public static final ArrayList<Item> hoe = new ArrayList<>();
		public static final ArrayList<Item> shovel = new ArrayList<>();
		public static final ArrayList<Item> shears = new ArrayList<>();
		
		public static void initialize() {
			/* PICKAXE */ {
				pickaxe.add(Items.NETHERITE_PICKAXE);
				pickaxe.add(Items.  DIAMOND_PICKAXE);
				pickaxe.add(Items.   GOLDEN_PICKAXE);
				pickaxe.add(Items.     IRON_PICKAXE);
				pickaxe.add(Items.    STONE_PICKAXE);
				pickaxe.add(Items.   WOODEN_PICKAXE);
			}
			/*   AXE   */ {
				axe.add(Items.NETHERITE_AXE);
				axe.add(Items.  DIAMOND_AXE);
				axe.add(Items.   GOLDEN_AXE);
				axe.add(Items.     IRON_AXE);
				axe.add(Items.    STONE_AXE);
				axe.add(Items.   WOODEN_AXE);
			}
			/*   HOE   */ {
				hoe.add(Items.NETHERITE_HOE);
				hoe.add(Items.  DIAMOND_HOE);
				hoe.add(Items.   GOLDEN_HOE);
				hoe.add(Items.     IRON_HOE);
				hoe.add(Items.    STONE_HOE);
				hoe.add(Items.   WOODEN_HOE);
			}
			/*  SWORD  */ {
				sword.add(Items.NETHERITE_SWORD);
				sword.add(Items.  DIAMOND_SWORD);
				sword.add(Items.   GOLDEN_SWORD);
				sword.add(Items.     IRON_SWORD);
				sword.add(Items.    STONE_SWORD);
				sword.add(Items.   WOODEN_SWORD);
			}
			/* SHUOWEL */ {
				shovel.add(Items.NETHERITE_SHOVEL);
				shovel.add(Items.  DIAMOND_SHOVEL);
				shovel.add(Items.   GOLDEN_SHOVEL);
				shovel.add(Items.     IRON_SHOVEL);
				shovel.add(Items.    STONE_SHOVEL);
				shovel.add(Items.   WOODEN_SHOVEL);
			}
			/* SHAEARS */ {
				shears.add(Items.SHEARS);
			}
		}
		
	}
	
	public static class Materials {
		public static final ArrayList<Material> pickaxe = new ArrayList<>();
		public static final ArrayList<Material> axe = new ArrayList<>();
		public static final ArrayList<Material> sword = new ArrayList<>();
		public static final ArrayList<Material> hoe = new ArrayList<>();
		public static final ArrayList<Material> shovel = new ArrayList<>();
		public static final ArrayList<Material> shears = new ArrayList<>();
		
		public static void initialize() {
			/* PICKAXE */ {
				pickaxe.add(Material.ICE);
				pickaxe.add(Material.STONE);
				pickaxe.add(Material.DENSE_ICE);
				pickaxe.add(Material.AMETHYST);
				pickaxe.add(Material.METAL);
				pickaxe.add(Material.PISTON);
				pickaxe.add(Material.REDSTONE_LAMP);
				pickaxe.add(Material.REPAIR_STATION);
				pickaxe.add(Material.SCULK);
			}
			/*   AXE   */ {
				axe.add(Material.WOOD);
				axe.add(Material.NETHER_WOOD);
				axe.add(Material.NETHER_SHOOTS);
			}
			/*   HOE   */ {
				hoe.add(Material.MOSS_BLOCK);
				hoe.add(Material.ORGANIC_PRODUCT);
				hoe.add(Material.LEAVES);
			}
			/*  SWORD  */ {
				sword.add(Material.BAMBOO);
				sword.add(Material.BAMBOO_SAPLING);
				sword.add(Material.COBWEB);
			}
			/* SHUOWEL */ {
				shovel.add(Material.SOLID_ORGANIC);
				shovel.add(Material.AGGREGATE);
			}
			/* SHAEARS */ {
				shears.add(Material.CARPET);
				shears.add(Material.WOOL);
			}
		}
	}
}
