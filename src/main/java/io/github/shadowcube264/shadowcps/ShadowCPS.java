package io.github.shadowcube264.shadowcps;

import org.lwjgl.input.Mouse;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = ShadowCPS.MODID, version = ShadowCPS.VERSION)
@SideOnly(Side.CLIENT)
public class ShadowCPS
{
    public static final String MODID = "shadowcps";
    public static final String VERSION = "4.2.0";
    public int leftClicks = 0;
    public int leftMouseTicks = 0;
    public int leftCPS = 0;
    public int ticksSinceLeftMouseEvent = 0;
    public boolean newLeftEvent = true;
    public int rightClicks = 0;
    public int rightMouseTicks = 0;
    public int rightCPS = 0;
    public int ticksSinceRightMouseEvent = 0;
    public boolean newRightEvent = true;
    
	@EventHandler
    public void init(FMLInitializationEvent event)
    {
    	System.out.println("lol");
        MinecraftForge.EVENT_BUS.register(this);
    }
	
	/**Changes leftCPS to show the current CPS of the left mouse button.
	 * Resets after a second without left mouse clicks.
	 */
    @SubscribeEvent
    public void leftMouseDetect(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.START) {
			if (Mouse.getEventButton() == 0 && Mouse.getEventButtonState() == false && newLeftEvent == true) {
	        	leftClicks += 1;
	        	newLeftEvent = false;
	        	ticksSinceLeftMouseEvent = 0;
	        	leftMouseTicks += 1;
	        	leftCPS = ((leftClicks * 20) / leftMouseTicks);
	        	System.out.println(Integer.toString(leftCPS));
			}
			if (Mouse.getEventButton() != 0 || Mouse.getEventButtonState() != false) {
				newLeftEvent = true;
				ticksSinceLeftMouseEvent += 1;
				if (ticksSinceLeftMouseEvent > 20) {
					leftClicks = 0;
					leftMouseTicks = 0;
					leftCPS = 0;
				} else {
					leftMouseTicks += 1;
				}
			}
	    }
    }
    
    /**Changes rightCPS to show the current CPS of the right mouse button.
	 * Resets after a second without right mouse clicks.
	 */
    @SubscribeEvent
    public void rightMouseDetect(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.START) {
			if (Mouse.getEventButton() == 1 && Mouse.getEventButtonState() == false && newRightEvent == true) {
	        	rightClicks += 1;
	        	newRightEvent = false;
	        	ticksSinceRightMouseEvent = 0;
	        	rightMouseTicks += 1;
	        	rightCPS = ((rightClicks * 20) / rightMouseTicks);
	        	System.out.println(Integer.toString(rightCPS));
			}
			if (Mouse.getEventButton() != 1 || Mouse.getEventButtonState() != false) {
				newRightEvent = true;
				ticksSinceRightMouseEvent += 1;
				if (ticksSinceRightMouseEvent > 20) {
					rightClicks = 0;
					rightMouseTicks = 0;
					rightCPS = 0;
				} else {
					rightMouseTicks += 1;
				}
			}
	    }
    }
}

