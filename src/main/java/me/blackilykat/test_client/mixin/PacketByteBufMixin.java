package me.blackilykat.test_client.mixin;

import io.netty.buffer.ByteBuf;
import me.blackilykat.test_client.util.Chat;
import net.minecraft.network.PacketByteBuf;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PacketByteBuf.class)
public class PacketByteBufMixin {
	@Shadow @Final private ByteBuf parent;
	
//	@Inject(method = "readDouble", at = @At("HEAD"), cancellable = true)
	private void testclient$nbtKickFix(CallbackInfoReturnable<Double> cir) {
		double value = 0;
		try {
			value = parent.readDouble();
		} catch (IndexOutOfBoundsException ignored) {
			Chat.sendGold("TestClient prevented an nbt kick!");
		}
		cir.setReturnValue(value);
	}
}
