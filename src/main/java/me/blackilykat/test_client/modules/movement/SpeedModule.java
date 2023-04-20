package me.blackilykat.test_client.modules.movement;

import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.gui.components.Module;
import me.blackilykat.test_client.util.AttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;

public class SpeedModule extends Module {
	public static AttributeModifier attributeModifier;
	public SpeedModule() {
		super("Speed");
		endTickRunnable = new Runnable() {
			@Override
			public void run() {
				if(Main.MC.player == null) return;
				if(attributeModifier == null) {
					attributeModifier = new AttributeModifier(
							EntityAttributes.GENERIC_MOVEMENT_SPEED,
							2,
							EntityAttributeModifier.Operation.MULTIPLY_TOTAL
					);
				}
				if(!attributeModifier.isOnPlayer()) attributeModifier.add();
			}
		};
		deactivateRunnable = new Runnable() {
			@Override
			public void run() {
				attributeModifier.remove();
			}
		};
	}
}
