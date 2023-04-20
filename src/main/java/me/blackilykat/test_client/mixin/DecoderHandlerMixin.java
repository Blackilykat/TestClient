package me.blackilykat.test_client.mixin;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.*;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.profiling.jfr.FlightProfiler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static me.blackilykat.test_client.Main.LOGGER;

@Mixin(DecoderHandler.class)
public class DecoderHandlerMixin {
	@Final
	@Mutable
	@Shadow
	private final NetworkSide side;
	
	public DecoderHandlerMixin(NetworkSide side) {
		this.side = side;
	}
	
	@Inject(method = "decode", at = @At("HEAD"), cancellable = true)
	private void testClient$cancelDecodeException(ChannelHandlerContext ctx, ByteBuf buf, List<Object> objects, CallbackInfo ci) {
		int i = buf.readableBytes();
		if (i != 0) {
			PacketByteBuf packetByteBuf = new PacketByteBuf(buf);
			int j = packetByteBuf.readVarInt();
			Packet<?> packet = ((NetworkState)ctx.channel().attr(ClientConnection.PROTOCOL_ATTRIBUTE_KEY).get()).getPacketHandler(this.side, j, packetByteBuf);
			if (packet == null) {
//				throw new IOException("Bad packet id " + j);
			} else {
				int k = ((NetworkState)ctx.channel().attr(ClientConnection.PROTOCOL_ATTRIBUTE_KEY).get()).getId();
				FlightProfiler.INSTANCE.onPacketReceived(k, j, ctx.channel().remoteAddress(), i);
				objects.add(packet);
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug(ClientConnection.PACKET_RECEIVED_MARKER, " IN: [{}:{}] {}", new Object[]{ctx.channel().attr(ClientConnection.PROTOCOL_ATTRIBUTE_KEY).get(), j, packet.getClass().getName()});
				}
			}
		}
		ci.cancel();
	}
}
