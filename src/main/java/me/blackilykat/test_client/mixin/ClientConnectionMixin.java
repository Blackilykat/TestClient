package me.blackilykat.test_client.mixin;

import io.netty.channel.ChannelHandlerContext;
import me.blackilykat.test_client.Main;
import net.minecraft.network.ClientConnection;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
	
//	@Inject(method = "disconnect", at = @At("TAIL"))
	private void testclient$ccDisconnectStacktrace(Text disconnectReason, CallbackInfo ci) {
		new Throwable().printStackTrace();
	}
	
	@Inject(method = "exceptionCaught", at = @At("HEAD"), cancellable = true)
	private void testclient$ccDisableDisconnectOnException(ChannelHandlerContext context, Throwable ex, CallbackInfo ci) {
			Main.LOGGER.warn("An exception has been caught in ClientConnection, but TestClient stopped the chain.");
			ex.printStackTrace();
			ci.cancel();
		
	}
}
