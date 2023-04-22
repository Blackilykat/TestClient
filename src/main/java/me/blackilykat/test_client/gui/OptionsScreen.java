package me.blackilykat.test_client.gui;

import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.gui.components.Module;
import me.blackilykat.test_client.gui.components.Option;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class OptionsScreen extends Screen {
	public static int backgroundColor = 0x99000000;
	ArrayList<Option<?>> optionList = new ArrayList<>();
	public OptionsScreen(Module module) {
		super(module.getMessage().copy().append("'s options"));
		for (Option<?> option : module.optionList) {
			optionList.add(option);
			addDrawableChild(option.widget);
		}
	}
	
	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		fill(matrices, 40, 40, width-40, height-40, backgroundColor);
		int textWidth = Main.MC.textRenderer.getWidth(getTitle());
		AtomicInteger lineY = new AtomicInteger(70);
		AtomicInteger textY = new AtomicInteger(50);
		
		drawTextWithShadow(matrices, Main.MC.textRenderer, getTitle(), width/2 - textWidth/2, 50, 0xFFFFFFFF);
		fill(matrices, 45, lineY.get()-1, width-45, lineY.get(), 0x33FFFFFF);
		
		for (Option<?> option : optionList) {
			lineY.addAndGet(30);
			drawTextWithShadow(matrices, Main.MC.textRenderer, option.name, 50, textY.addAndGet(30), 0xFFFFFFFF);
			fill(matrices, 45, lineY.get()-1, width-45, lineY.get(), 0x33FFFFFF);
//			drawHorizontalLine(matrices, 45, width-45, lineY.addAndGet(30), 0x33FFFFFF);
			option.widget.setX(width - 50 - option.widget.getWidth());
			option.widget.setY(textY.get() - ((option.widget.getHeight()-9)/2));
		}
		super.render(matrices, mouseX, mouseY, delta);
		
	}
}
