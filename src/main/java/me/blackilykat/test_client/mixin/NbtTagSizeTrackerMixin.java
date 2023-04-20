package me.blackilykat.test_client.mixin;

import net.minecraft.nbt.NbtTagSizeTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NbtTagSizeTracker.class)
public class NbtTagSizeTrackerMixin {
	
//	@ModifyVariable(method = "<init>", at = @At("HEAD"), ordinal = 0, argsOnly = true)
	private static long testclient$overrideMaxBytes(long maxBytes) {
		return 10000000L;
	}
}
