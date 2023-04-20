package me.blackilykat.test_client.mixin;

import net.minecraft.network.PacketBundleHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PacketBundleHandler.class)
public interface PacketBundleHandlerMixinInterface {
}
