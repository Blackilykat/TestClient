package me.blackilykat.test_client.mixin;

import me.blackilykat.test_client.commands.Command;
import me.blackilykat.test_client.commands.Subcommand;
import me.blackilykat.test_client.util.ArrayUtil;
import me.blackilykat.test_client.util.Chat;
import me.blackilykat.test_client.util.SharedVariables;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

@Mixin(ChatScreen.class)
public class ChatMixin {
	
	@Inject(method = "sendMessage(Ljava/lang/String;Z)Z", at=@At(value = "INVOKE",target = "Ljava/lang/String;startsWith(Ljava/lang/String;)Z"), cancellable = true)
	public void testclient$checkClientCommands(String chatText, boolean addToHistory, CallbackInfoReturnable<Boolean> cir) {
		if(chatText.startsWith("\\")) {
			String[] splitCommand = chatText.split(" ");
			String[] args = ArrayUtil.removeFirst(splitCommand, 1);
			splitCommand[0] = splitCommand[0].substring(1);
			boolean foundCommand = false;
			
			
			for (Command command : Command.commandList) {
				if (splitCommand[0].equals(command.name)) {
					// checks for subcommands in run to make it chainable
					command.run(args);
					foundCommand = true;
					break;
				}
			}
			if(!foundCommand) Chat.send(Text.literal("No TestClient command found under " + splitCommand[0]).formatted(Formatting.RED));
			
			cir.setReturnValue(true);
		}
		if(chatText.startsWith(".") && !SharedVariables.WarnedOfBackslashCommands) {
			Chat.send(Text.literal("TestClient commands start with a backslash(\\)! ").formatted(Formatting.RED).append(Text.literal("You will only be warned once.").formatted(Formatting.DARK_RED).formatted(Formatting.UNDERLINE)));
			SharedVariables.WarnedOfBackslashCommands = true;
			cir.setReturnValue(true);
		}
		
	}
	
	
}
