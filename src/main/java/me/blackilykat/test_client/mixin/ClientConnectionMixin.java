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
	private static boolean printedOnce = false;
	
//	@Inject(method = "disconnect", at = @At("TAIL"))
	private void testclient$ccDisconnectStacktrace(Text disconnectReason, CallbackInfo ci) {
		new Throwable().printStackTrace();
	}
	
	@Inject(method = "exceptionCaught", at = @At("HEAD"), cancellable = true)
	private void testclient$ccDisableDisconnectOnException(ChannelHandlerContext context, Throwable ex, CallbackInfo ci) {
		boolean cancel = printedOnce;
		printedOnce = true;
		if(cancel){
			Main.LOGGER.warn("An exception has been caught in ClientConnection, but TestClient stopped the chain.");
			ex.printStackTrace();
			if (printedOnce) ci.cancel();
		}
	}
}
