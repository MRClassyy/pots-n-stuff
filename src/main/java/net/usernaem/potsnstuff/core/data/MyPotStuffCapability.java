package net.usernaem.potsnstuff.core.data;

import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.capabilities.Capability;


public class MyPotStuffCapability {
	public static final Capability<MyPotStuffInterface> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});
	
	
	public static void register(RegisterCapabilitiesEvent event) {
		event.register(MyPotStuffInterface.class);
	}
	
	
	
	private MyPotStuffCapability() {
		
	}
}
