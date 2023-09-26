package net.usernaem.potsnstuff.client.util;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

public class KeyBinding {

	public static final String KEY_CATEGORY_GLOVE = "key.category.potsnstuff.keymap";
	    public static final String KEY_OPEN_GLOVE = "key.potsnstuff.open_glove";

	    public static final KeyMapping GLOVE_KEY = new KeyMapping(KEY_OPEN_GLOVE, KeyConflictContext.IN_GAME,
	            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_ALT, KEY_CATEGORY_GLOVE);
}
