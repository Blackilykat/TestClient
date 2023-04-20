package me.blackilykat.test_client.mixin;

import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.gui.Gui;
import me.blackilykat.test_client.util.SharedVariables;
import net.minecraft.client.util.Session;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Session.class)
public abstract class SessionMixin {
	@Shadow public abstract Session.AccountType getAccountType();
	
	@Inject(method = "getUsername", at=@At("RETURN"), cancellable = true)
	void testclient$overrideUsernameGetter(CallbackInfoReturnable<String> cir) {
		// only overrides when gui has been initialized cause issues
		if(Gui.gui != null) cir.setReturnValue(SharedVariables.username);
	}
}
