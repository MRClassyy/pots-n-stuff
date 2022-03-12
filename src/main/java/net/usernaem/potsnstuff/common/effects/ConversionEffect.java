package net.usernaem.potsnstuff.common.effects;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ConversionEffect extends MobEffect{

	public static final DamageSource ConversionDamageSource = new DamageSource("Conversion").bypassArmor().setMagic();
	
	public ConversionEffect() {
		super(MobEffectCategory.BENEFICIAL, 11494047);
	}

}
