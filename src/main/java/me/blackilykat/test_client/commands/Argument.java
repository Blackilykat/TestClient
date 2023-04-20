package me.blackilykat.test_client.commands;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public abstract class Argument<E> {
	public String name;
	
	/**
	 * <p>The hint is what gets displayed above the chat bar while the command is getting typed.</p>
	 * <p>This is not yet implemented, but it's nice to have everything ready.</p>
	 * <p>Examples:</p>
	 * <br>
	 * <p>''''Position(x y z)</p>
	 * <p>\tp |</p>
	 * <br>
	 * <p>''''''''''''The prefix</p>
	 * <p>\prefix set |</p>
	 * <br>
	 * <p>'''''''''''Multiplier</p>
	 * <p>\speed set |</p>
	 */
	public Text hint;
	private E value;
	public Argument(String name) {
		this.name = name;
	}
	public Argument(String name, Text hint) {
		this.name = name;
		this.hint = hint;
	}
	
	public E getValue() {
		return value;
	}
	
	public void setValue(E value) {
		this.value = value;
	}
	
}
