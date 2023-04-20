package me.blackilykat.test_client.mixin;

import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.util.SharedVariables;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MultiplayerScreen.class)
public class MultiplayerScreenMixin extends Screen{
	@Shadow private ButtonWidget buttonEdit;
	
	protected MultiplayerScreenMixin(Text title) {
		super(title);
	}
	
	@Inject(method="init", at = @At("TAIL"))
	private void testclient$addUsernameField(CallbackInfo ci) {
		TextFieldWidget usernameField = new TextFieldWidget(textRenderer, buttonEdit.getX()-130, buttonEdit.getY(), 120, 20, Text.literal("Username"));;
		usernameField.setText(SharedVariables.username);
		usernameField.setChangedListener(text -> {
			SharedVariables.username = text;
		});
		usernameField.setMaxLength(16);
		addDrawableChild(usernameField);
	}
}
