package me.blackilykat.test_client.mixin;

import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.util.Chat;
import net.minecraft.nbt.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.DataInput;
import java.io.IOException;

@Mixin(NbtIo.class)
public class NbtIoMixin {
	
	@Inject(method = "read(Ljava/io/DataInput;ILnet/minecraft/nbt/NbtTagSizeTracker;)Lnet/minecraft/nbt/NbtElement;",at = @At("HEAD"), cancellable = true)
	private static void read(DataInput input, int depth, NbtTagSizeTracker tracker, CallbackInfoReturnable<NbtElement> cir) throws IOException {
		byte b = input.readByte();
		if (b == 0) {
			cir.setReturnValue(NbtEnd.INSTANCE);
		} else {
			NbtString.skip(input);

			try {
				cir.setReturnValue(NbtTypes.byId(b).read(input, depth, tracker));
			} catch (Exception var7) {
				Chat.sendGold("TestClient prevented an NBT kick! (hopefully no issues lol)");
				cir.setReturnValue(NbtEnd.INSTANCE); // i don't fucking know i just copied
			}
		}
	}
	
	@Inject(method = "read(Ljava/io/DataInput;Lnet/minecraft/nbt/NbtTagSizeTracker;)Lnet/minecraft/nbt/NbtCompound;", at = @At("HEAD"), cancellable = true)
	private static void testclient$antiNbtKickPreventLag(DataInput input, NbtTagSizeTracker tracker, CallbackInfoReturnable<NbtCompound> cir) {
		NbtElement nbtElement = NbtIoInvoker.readInvoker(input, 0, tracker);
		if (nbtElement instanceof NbtCompound) {
			cir.setReturnValue((NbtCompound)nbtElement);
		} else {
			Main.LOGGER.warn("TestClient hopefully prevented lag lol idk");
		}
	}

}
