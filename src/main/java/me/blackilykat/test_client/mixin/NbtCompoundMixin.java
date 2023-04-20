package me.blackilykat.test_client.mixin;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtTagSizeTracker;
import net.minecraft.nbt.NbtType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.DataInput;

@Mixin(NbtCompound.class)
public class NbtCompoundMixin {
//	@Inject(method = "read", at = @At("HEAD"))
	private static void testClient$nbtCompoundPrintStacktrace(NbtType<?> reader, String key, DataInput input, int depth, NbtTagSizeTracker tracker, CallbackInfoReturnable<NbtElement> cir) {
		new Throwable().printStackTrace();
	}
}
