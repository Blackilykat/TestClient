package me.blackilykat.test_client.mixin;

import net.minecraft.network.PacketBundleHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PacketBundleHandler.class)
public interface PacketBundleHandlerMixin {
	@Shadow @Final public static int MAX_PACKETS = 100000000;
	
//	@Inject(method = "<clinit>", at = @At("HEAD"))
//	private static void testClient$increaseMaxPackets(CallbackInfo ci) {
//		MAX_PACKETS = 1000000;
//	}
}