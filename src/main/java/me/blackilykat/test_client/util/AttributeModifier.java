package me.blackilykat.test_client.util;

import me.blackilykat.test_client.Main;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;

public class AttributeModifier {
	/*
	EntityAttributeInstance movementSpeedAttribute = MC.player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
	EntityAttributeModifier tempModifier = new EntityAttributeModifier(
		"TestClient@Speed", // Modifier name
		3, // Modifier value
		EntityAttributeModifier.Operation.MULTIPLY_TOTAL); // Modifier operation
	movementSpeedAttribute.addPersistentModifier(tempModifier);
	 */
	String name;
	EntityAttribute attribute;
	float value;
	EntityAttributeModifier.Operation operation;
	EntityAttributeModifier modifier;
	EntityAttributeInstance instance;
	public AttributeModifier(EntityAttribute attribute, float value, EntityAttributeModifier.Operation operation) {
		this.attribute = attribute;
		this.value = value;
		this.operation = operation;
		name = "TestClient@"+(new Throwable().getStackTrace()[1].getClassName())+"."+(new Throwable().getStackTrace()[1].getMethodName());
//		Chat.send("Created new attribute modifier " + name);
		instance = Main.MC.player.getAttributeInstance(attribute);
		modifier = new EntityAttributeModifier(name, value, operation);
	}
	
	public void add() {
		instance.addPersistentModifier(modifier);
	}
	
	public void remove() {
		instance.removeModifier(modifier);
	}
	
	public boolean isOnPlayer() {
		return instance.hasModifier(modifier);
	}
	
	public void setValue(float value) {
		this.value = value;
		modifier = new EntityAttributeModifier(name, value, operation);
		// remove and add multiplier to refresh value
		if(isOnPlayer()){
			remove();
			add();
		}
	}
	
	public float getValue() {
		return value;
	}
}
