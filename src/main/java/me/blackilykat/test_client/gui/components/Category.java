package me.blackilykat.test_client.gui.components;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WButton;
import me.blackilykat.test_client.util.Chat;
import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.gui.Gui;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Category extends ButtonWidget {
	
	public ArrayList<Module> moduleList = new ArrayList<>();
	public boolean isExtended;
	private int fullHeight;
	private int defaultColor = 0xAAAA0000;
	private int hoveredColor = 0xCCCC0000;
	public Category(String name) {
		super(0, 0, 20, 20, Text.literal(name), widget -> {}, ButtonWidget.DEFAULT_NARRATION_SUPPLIER);
		isExtended = false;
		
	}
	
	@Override
	public void onPress() {
		isExtended = !isExtended;
		if(isExtended) Main.LOGGER.info("Extended " + getMessage().getString());
		else Main.LOGGER.info("Contracted " + getMessage().getString());
		// getModuleList();
		AtomicInteger yPos = new AtomicInteger(getY());
		moduleList.forEach(module -> {
			if(isExtended) {
				Gui.gui.publicAddDrawableChild(module);
				module.setX(getX());
				module.setY(yPos.addAndGet(20));
				module.setWidth(getWidth());
			}
			else Gui.gui.publicRemove(module);
			// give module same width as category
//					module.setWidth(getWidth());
		});
	}
	
	
	public int getFullHeight() {
		return fullHeight;
	}
	
	public void setFullHeight(int fullHeight) {
		this.fullHeight = fullHeight;
	}
	
	public void register() {
		Gui.categoryList.add(this);
	}
	
	public Category withModule(Module module) {
		int moduleWidth = Main.MC.textRenderer.getWidth(module.getMessage())+16;
		Main.LOGGER.info("Module (" + module.getMessage() +") width: " + moduleWidth);
		Main.LOGGER.info("This width: " + width);
		Main.LOGGER.info("Module width is larger than this width: " + (moduleWidth > width));
		if(moduleWidth > width) width = moduleWidth;
		Main.LOGGER.info("Width after: " + width);
		moduleList.add(module);
		return this;
	}
	
	public void getModuleList() {
		Chat.send(this.getMessage().getString() + "'s modules: ");
		moduleList.forEach(module -> {
			Chat.send(" - " + module.getMessage().getString());
		});
	}
	
//	@Override
//	public void paint(MatrixStack matrices, int x, int y, int mouseX, int mouseY) {
//		boolean hovered = (mouseX>=0 && mouseY>=0 && mouseX<getWidth() && mouseY<getHeight());
//		boolean isHovered = false; //0 = regular, 1 = hovered.
//		if (hovered || isFocused()) {
//			isHovered = true;
//		}
//		// draw the box
//		ScreenDrawing.coloredRect(matrices, x, y, getWidth(), getHeight(), isHovered ? hoveredColor : defaultColor);
//
//		if (this.getLabel()!=null) {
//			// draw the text
//			int textColor = 0xE0E0E0;
//			ScreenDrawing.drawStringWithShadow(matrices, this.getLabel().asOrderedText(), alignment, x, y + ((20 - 8) / 2), width, textColor);
//            /* else if (hovered) {
//				color = 0xFFFFA0;
//			}*/
//
//			// int xOffset = (icon != null && alignment == HorizontalAlignment.LEFT) ? ICON_SPACING+iconSize+ICON_SPACING : 0;
//		}
//	}
	
	@Override
	public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		boolean hovered = (mouseX>=getX() && mouseY>=getY() && mouseX<getWidth()+getX() && mouseY<getHeight()+getY());
		MinecraftClient minecraftClient = MinecraftClient.getInstance();
		fill(matrices, getX(), getY(), getX()+getWidth(), getY()+getHeight(), hovered ? hoveredColor : defaultColor);
		this.drawMessage(matrices, minecraftClient.textRenderer, 0xFFFFFF | MathHelper.ceil(this.alpha * 255.0F) << 24);
	}
}