package me.blackilykat.test_client.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import me.blackilykat.test_client.gui.components.Category;
import me.blackilykat.test_client.Main;
import me.blackilykat.test_client.gui.components.Module;
import me.blackilykat.test_client.modules.combat.KillAuraModule;
import me.blackilykat.test_client.modules.movement.*;
import me.blackilykat.test_client.modules.op.AntiTeleportModule;
import me.blackilykat.test_client.modules.op.AutoDeopModule;
import me.blackilykat.test_client.modules.op.AutoOpModule;
import me.blackilykat.test_client.modules.op.AutoSudoKickModule;
import me.blackilykat.test_client.modules.utility.AutoToolsModule;
import me.blackilykat.test_client.modules.utility.BlockGuardianParticlesModule;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class Gui extends Screen{
	
	public static ArrayList<Category> categoryList = new ArrayList<>();
	
	public static Gui gui;
	
	public <T extends Element & Drawable & Selectable> void publicAddDrawableChild(T element) {
		addDrawableChild(element);
	}
	
	public void publicRemove(Element child) {
		remove(child);
	}
	
	public Gui() {
		super(Text.literal("TestClient menu"));
		AtomicInteger categoryX = new AtomicInteger(20);
		AtomicInteger categoryY = new AtomicInteger(20);
		categoryList.forEach(category -> {
			category.setFullHeight(categoryY.get());
			addDrawableChild(category); // adapt category to be buttons
			category.setX(categoryX.get());
			category.setY(categoryY.get());
//			category.setWidth(Main.MC.textRenderer.getWidth(category.getMessage()) + 20);
			
			Main.LOGGER.info("Created category " + category.getMessage().getString() + " at x " + categoryX.get() + "(" + category.getX() + ") and y " + categoryY + "(" + category.getY() + ") and is " + category.getWidth() + " wide and " + category.getFullHeight() + " high");
			categoryX.addAndGet(category.getWidth() + 20);
			if(categoryX.get() + category.getWidth() + 20 > Main.MC.getWindow().getWidth()) {
				categoryX.set(20);
				categoryY.addAndGet(40); //TODO make a max height per row at some point
                    /* Something like:
                        +----------------------+
                        | #### ### #### #####  |
                        | //// ///      /////  |
                        |      ///             |
                        | ### ##### ###        |
                        | /// /////            |
                        |     /////            |
                        |     /////            |
                        +----------------------+
                        actually nvm its good as it is now
                     */
			}
			AtomicInteger yPos = new AtomicInteger(category.getY());
			if(category.isExtended) category.moduleList.forEach(module -> {
				Gui.gui.publicAddDrawableChild(module);
				module.setX(category.getX());
				module.setY(yPos.addAndGet(20));
				module.setWidth(category.getWidth());
			});
		});
	}
	
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		
		this.renderBackground(matrices);
		super.render(matrices, mouseX, mouseY, delta);
	}
	
	public static void open() {
		gui = new Gui();
		Main.MC.setScreen(gui);
	}
	
	
	public WPlainPanel root;
	
//	private class ScreenGui extends LightweightGuiDescription {
//		public ScreenGui() {
//			int scale = Main.MC.options.getGuiScale().getValue();
//			// create invisible panel
//			root = new Root();
//			// set it as root
//			setRootPanel(root);
//			// make panel size big as the screen
//			root.setSize(Main.MC.getWindow().getWidth()/scale, Main.MC.getWindow().getHeight()/scale); // still broken but fuck it idc
//			root.setInsets(Insets.ROOT_PANEL);
//
//			// set initial positions for the buttons
//			AtomicInteger categoryX = new AtomicInteger(20);
//			AtomicInteger categoryY = new AtomicInteger(20);
//			categoryList.forEach(category -> {
//				category.setFullHeight(categoryY.get());
//				root.add(category, categoryX.get(), categoryY.get());
//				category.setWidth(Main.MC.textRenderer.getWidth(category.getLabel()) + 20);
//
//				Main.LOGGER.info("Created category " + category.getLabel().getString() + " at x " + categoryX.get() + "(" + category.getX() + ") and y " + categoryY + "(" + category.getY() + ") and is " + category.getWidth() + " wide and " + category.getFullHeight() + " high");
//				categoryX.addAndGet(category.getWidth() + 20);
//				if(categoryX.get() + category.getWidth() + 20 > root.getWidth()) {
//					categoryX.set(20);
//					categoryY.addAndGet(40); //TODO make a max height per row at some point
//                    /* Something like:
//                        +----------------------+
//                        | #### ### #### #####  |
//                        | //// ///      /////  |
//                        |      ///             |
//                        | ### ##### ###        |
//                        | /// /////            |
//                        |     /////            |
//                        |     /////            |
//                        +----------------------+
//                        actually nvm its good as it is now
//                     */
//				}
//			});
//
//			root.validate(this);
//		}
//	}
	
	public static void refreshGui() {
		gui = new Gui();
	}
	
	public static void initAutoRefresher() {
//		ClientTickEvents.END_CLIENT_TICK.register(listener -> {
//			int scale = Main.MC.options.getGuiScale().getValue();
////			scale = 1;
//			if(
//				Main.MC.getWindow().getWidth()/scale != gui.root.getWidth()
//				|| Main.MC.getWindow().getHeight()/scale != gui.root.getHeight()
//			) {
//				Main.LOGGER.info("Refreshed GUI size because "
//						+ Main.MC.getWindow().getWidth()/scale
//						+ " != "
//						+ gui.root.getWidth()
//						+ " or "
//						+ Main.MC.getWindow().getHeight()/scale
//						+ " != "
//						+ gui.root.getHeight()
//				);
//				refreshGui();
//			}
//		});
	}
	
	
	
	public static void addComponents() {
		new Category("Movement")
				.withModule(new FlightModule())
				.withModule(new SpeedModule())
				.withModule(new FreezeModule())
				.withModule(new AirJumpModule())
				.register();
		new Category("Combat")
				.withModule(new KillAuraModule())
				.withModule(new Module("Reach"))
				.withModule(new Module("Bow Aimbot"))
				.register();
		new Category("Utility")
				.withModule(AutoToolsModule.instance)
				.withModule(AntiVoidModule.instance)
				.withModule(BlockGuardianParticlesModule.instance)
				.register();
		new Category("OP")
				.withModule(new AntiTeleportModule())
				.withModule(AutoDeopModule.instance)
				.withModule(AutoOpModule.instance)
				.withModule(AutoSudoKickModule.instance)
				.register();
	}
	
	private static class Root extends WPlainPanel {
		public Root() {
			super();
		}
		// for some reason setting the backgroundpainter doesn't work so i'll just do this lol
		@Override
		public void paint(MatrixStack matrices, int x, int y, int mouseX, int mouseY) {
			for(WWidget child : children) {
				child.paint(matrices, x + child.getX(), y + child.getY(), mouseX-child.getX(), mouseY-child.getY());
			}
		}
		
//		@Override
//		public void add(WWidget w, int x, int y, int width, int height) {
//			children.add(w);
//			w.setParent(this);
//			w.setLocation(insets.left() + x, insets.top() + y);
//			if (w.canResize()) {
//				w.setSize(width, height);
//			}
//
//			expandToFit(w, insets);
//			if(w instanceof Module) {
//				add(((Module)w).optionsButton, x+width-20, y);
//			}
//		}
//
//		@Override
//		public void add(WWidget w, int x, int y) {
//			children.add(w);
//			w.setParent(this);
//			w.setLocation(insets.left() + x, insets.top() + y);
//			if (w.canResize()) {
//				w.setSize(18, 18);
//			}
//
//			expandToFit(w, insets);
//			//valid = false;
//			if(w instanceof Module) {
//				add(((Module)w).optionsButton, x+w.getWidth()-20, y);
//			}
//		}
//
//		@Override
//		public void remove(WWidget w) {
//			if(w instanceof Module) {
//				remove(((Module)w).optionsButton);
//			}
//			children.remove(w);
//		}
	}
}
