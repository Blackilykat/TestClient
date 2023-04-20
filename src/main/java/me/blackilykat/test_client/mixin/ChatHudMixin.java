package me.blackilykat.test_client.mixin;

import me.blackilykat.test_client.modules.op.AutoDeopModule;
import me.blackilykat.test_client.modules.op.AutoSudoKickModule;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mixin(ChatHud.class)
public class ChatHudMixin {
	
	@Inject(method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V", at = @At("TAIL"))
	private void testclient$autoDeopListener(Text message, MessageSignatureData signature, MessageIndicator indicator, CallbackInfo ci) {
		String pattern = "\\[(.*?)\\: Made (.*?) a server operator\\]";
		
		
		Matcher matcher = Pattern.compile(pattern).matcher(message.getString());
		
		if (matcher.find()) {
			AutoDeopModule.execute(matcher.group(1), matcher.group(2));
		}
	}
	@Inject(method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V", at = @At("TAIL"))
	private void testclient$autoSudoKickListener(Text message, MessageSignatureData signature, MessageIndicator indicator, CallbackInfo ci) {
		String pattern = "(.*?) joined the game";
		
		Matcher matcher = Pattern.compile(pattern).matcher(message.getString());
		
		if (matcher.find()) {
			AutoSudoKickModule.execute(matcher.group(1));
		}
	}
	
	@Inject(method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;ILnet/minecraft/client/gui/hud/MessageIndicator;Z)V", at = @At("HEAD"), cancellable = true)
	private void testclient$preventMsgCrash1(Text message, MessageSignatureData signature, int ticks, MessageIndicator indicator, boolean refresh, CallbackInfo ci) {
		if(message.getString().length() > 1000) ci.cancel();
	}
	
	@Inject(method = "addMessage(Lnet/minecraft/text/Text;)V", at = @At("HEAD"), cancellable = true)
	private void testclient$preventMsgCrash2(Text message, CallbackInfo ci) {
		if(message.getString().length() > 1000) ci.cancel();
	}
	
	@Inject(method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V", at = @At("HEAD"), cancellable = true)
	private void testclient$preventMsgCrash3(Text message, MessageSignatureData signature, MessageIndicator indicator, CallbackInfo ci) {
		if(message.getString().length() > 1000) ci.cancel();
	}
	
	
}
