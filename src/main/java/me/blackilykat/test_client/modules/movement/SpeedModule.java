package me.blackilykat.test_client.modules.movement;

import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.gui.components.Module;
import me.blackilykat.test_client.gui.components.options.DoubleSliderOption;
import me.blackilykat.test_client.gui.components.options.IntSliderOption;
import me.blackilykat.test_client.util.AttributeModifier;
import me.blackilykat.test_client.util.Chat;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;

public class SpeedModule extends Module {
	public static AttributeModifier attributeModifier;
	public SpeedModule() {
		super("Speed");
		withOption(new DoubleSliderOption("multiplier", 0, 0, 10, 3));
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
				} else {
					
					float value = ((Double)optionList.get(0).optionValue).floatValue();
					if(attributeModifier.getValue() != value) {
						attributeModifier.setValue(value);
//						Chat.sendGold("Speed multiplier: " + value);
					}
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
