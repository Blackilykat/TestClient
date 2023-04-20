package me.blackilykat.test_client.gui.components;

import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import me.blackilykat.test_client.Main;
import net.minecraft.client.util.math.MatrixStack;

public class OptionsPanel extends WPlainPanel {
	Module module;
	private int color = 0x99333333;
	public OptionsPanel(Module module) {
		super();
		int scale = Main.MC.options.getGuiScale().getValue();
		setSize((Main.MC.getWindow().getWidth()/scale)-30, (Main.MC.getWindow().getHeight()/scale)-30); // still broken but fuck it idc
		this.module = module;
		
	}
	@Override
	public void paint(MatrixStack matrices, int x, int y, int mouseX, int mouseY) {
		ScreenDrawing.coloredRect(matrices, x, y, getWidth(), getHeight(), color);
	}
}
