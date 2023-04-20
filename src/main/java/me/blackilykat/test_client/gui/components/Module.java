package me.blackilykat.test_client.gui.components;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import io.github.cottonmc.cotton.gui.widget.data.InputResult;
import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.gui.Gui;
import me.blackilykat.test_client.util.TickRunnableHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

public class Module extends ButtonWidget {
	
	public Runnable activateRunnable;
	public Runnable deactivateRunnable;
	public Runnable endTickRunnable;
	public boolean needsInWorld = true;
	public boolean isEnabled;
	private int defaultColor = 0xCC333333;
	private int hoveredColor = 0xEE444444;
	private int disabledBarColor = 0xAA000000;
	private int enabledBarColor = 0xEE00AA00;
	public OptionsButton optionsButton;
	public Module(String name) {
		super(0, 0, 20, 20, Text.literal(name), widget -> {}, ButtonWidget.DEFAULT_NARRATION_SUPPLIER);
		isEnabled = false;
		optionsButton = new OptionsButton(this);
		
	}
	
	@Override
	public void onPress() {
		isEnabled = !isEnabled;
		if (isEnabled) {
			if (activateRunnable != null) activateRunnable.run();
			if (endTickRunnable != null) TickRunnableHandler.runAtTickEnd.add(endTickRunnable);
		} else {
			if (deactivateRunnable != null) deactivateRunnable.run();
			if (endTickRunnable != null) TickRunnableHandler.runAtTickEnd.remove(endTickRunnable);
		}
	}
	
//	void setWidth(int width) {
//		this.width = width;
//	}
	
//	@Override
//	public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
//		MinecraftClient minecraftClient = MinecraftClient.getInstance();
//		RenderSystem.setShaderTexture(0, WIDGETS_TEXTURE);
//		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
//		RenderSystem.enableBlend();
//		RenderSystem.enableDepthTest();
//		drawNineSlicedTexture(matrices, this.getX(), this.getY(), this.getWidth(), this.getHeight(), 20, 4, 200, 20, 0, this.getTextureY());
//
//		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//		int i = this.active ? 16777215 : 10526880;
//		this.drawMessage(matrices, minecraftClient.textRenderer, i | MathHelper.ceil(this.alpha * 255.0F) << 24);
//	}

//	public void paint(MatrixStack matrices, int x, int y, int mouseX, int mouseY) {
//		boolean hovered = (mouseX>=0 && mouseY>=0 && mouseX<getWidth() && mouseY<getHeight());
//		boolean isHovered = false; //0 = regular, 1 = hovered.
//		if (hovered || isFocused()) {
//			isHovered = true;
//		}
//		// draw the box
//		ScreenDrawing.coloredRect(matrices, x, y, getWidth(), getHeight(), isHovered ? hoveredColor : defaultColor);
//		// draw enabled/disabled indicator
//		ScreenDrawing.coloredRect(matrices, x, y, 2, getHeight(), isEnabled ? enabledBarColor : disabledBarColor);
//
//
//		if (this.getLabel()!=null) {
//			// draw the text
//			int textColor = 0xE0E0E0;
//			ScreenDrawing.drawStringWithShadow(matrices, this.getLabel().asOrderedText(), alignment, x+1 /* to make it offset to the indicator */, y + ((20 - 8) / 2), width, textColor);
//            /* else if (hovered) {
//				color = 0xFFFFA0;
//			}*/
//
//			// int xOffset = (icon != null && alignment == HorizontalAlignment.LEFT) ? ICON_SPACING+iconSize+ICON_SPACING : 0;
//		}
//	}
	
	public Module atActivate(Runnable runnable) {
		activateRunnable = runnable;
		return this;
	}
	public Module atDeactivate(Runnable runnable) {
		deactivateRunnable = runnable;
		return this;
	}
	public Module atEndTick(Runnable runnable) {
		endTickRunnable = runnable;
		return this;
	}
	
	private Module getThis() {return this;}
	
	public static class OptionsButton extends WButton {
		public boolean isHovered = false;
		public Module parentModule;
		public OptionsButton(Module parent) {
			super(Text.of("⚙"));
			setSize(20, 20);
			parentModule = parent;
			setOnClick(() -> {
				int scale = Main.MC.options.getGuiScale().getValue();
				Gui.gui.root.add(new OptionsPanel(parentModule), 10, 10, (Main.MC.getWindow().getWidth()/scale)-40, (Main.MC.getWindow().getHeight()/scale)-40);
			});
		}
		
		
		private final int defaultColor = 0x00333333;
		private final int hoveredColor = 0xEE777777;
		
		@Override
		public void paint(MatrixStack matrices, int x, int y, int mouseX, int mouseY) {
			boolean hovered = (mouseX>=0 && mouseY>=0 && mouseX<getWidth() && mouseY<getHeight());
			isHovered = false; //0 = regular, 1 = hovered.
			if (hovered || isFocused()) {
				isHovered = true;
			}
			// draw the box
			ScreenDrawing.coloredRect(matrices, x, y, getWidth(), getHeight(), isHovered ? hoveredColor : defaultColor);
			
			
			if (this.getLabel()!=null) {
				// draw the text
				int textColor = 0xE0E0E0;
				ScreenDrawing.drawStringWithShadow(matrices, this.getLabel().asOrderedText(), alignment, x, y + ((20 - 8) / 2), width, textColor);
            /* else if (hovered) {
				color = 0xFFFFA0;
			}*/
				
				// int xOffset = (icon != null && alignment == HorizontalAlignment.LEFT) ? ICON_SPACING+iconSize+ICON_SPACING : 0;
			}
		}
		
	}
}

/*
TODO:
 - finish translating Module from WButton to vanilla ButtonWidget
 - translate categories from WButton to vanilla ButtonWidget
 - make everything work with vanilla stuff
The goal is to stop struggling with screen size. It's so annoying with LibGui.
 */