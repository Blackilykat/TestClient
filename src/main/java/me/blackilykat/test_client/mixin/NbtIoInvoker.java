package me.blackilykat.test_client.mixin;

import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.NbtTagSizeTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.io.DataInput;

@Mixin(NbtIo.class)
public interface NbtIoInvoker {
	@Invoker(value = "read")
	static NbtElement readInvoker(DataInput input, int depth, NbtTagSizeTracker tracker) {
		throw new AssertionError();
	}
}