package me.blackilykat.test_client.mixin;

import me.blackilykat.test_client.gui.Gui;
import net.minecraft.client.gui.screen.TitleScreen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
	@Shadow @Nullable private String splashText;
	
	@Inject(method = "init", at = @At("HEAD"))
	void testclient$initializeGui(CallbackInfo ci) {
		splashText = "TestClient!";
		if(Gui.gui == null){
			Gui.initAutoRefresher();
			Gui.addComponents();
			Gui.gui = new Gui();
		}
	}
}
