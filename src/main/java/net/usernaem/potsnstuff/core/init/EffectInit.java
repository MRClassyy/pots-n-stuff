package net.usernaem.potsnstuff.core.init;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.usernaem.potsnstuff.PotsNStuff;
import net.usernaem.potsnstuff.common.effects.AquaEffect;
import net.usernaem.potsnstuff.common.effects.BlastProtectionEffect;
import net.usernaem.potsnstuff.common.effects.BombEffect;
import net.usernaem.potsnstuff.common.effects.ClenseEffect;
import net.usernaem.potsnstuff.common.effects.CureEffect;
import net.usernaem.potsnstuff.common.effects.FlightEffect;
import net.usernaem.potsnstuff.common.effects.FrailEffect;
import net.usernaem.potsnstuff.common.effects.FuzeEffect;
import net.usernaem.potsnstuff.common.effects.GroundingEffect;
import net.usernaem.potsnstuff.common.effects.HellsTouchEffect;
import net.usernaem.potsnstuff.common.effects.InfernoEffect;
import net.usernaem.potsnstuff.common.effects.LaunchingEffect;
import net.usernaem.potsnstuff.common.effects.LightningEffect;
import net.usernaem.potsnstuff.common.effects.NullifyEffect;
import net.usernaem.potsnstuff.common.effects.PhotosynthesisEffect;
import net.usernaem.potsnstuff.common.effects.RecallEffect;
import net.usernaem.potsnstuff.common.effects.ReflectEffect;
import net.usernaem.potsnstuff.common.effects.UndeathEffect;

public class EffectInit {
	public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, PotsNStuff.MOD_ID_STRING);
	
	public static final RegistryObject<MobEffect> FUSE_OBJECT = EFFECTS.register("fuze", () -> new FuzeEffect());
	public static final RegistryObject<MobEffect> BOMB_OBJECT = EFFECTS.register("bomb", () -> new BombEffect());
	public static final RegistryObject<MobEffect> GROUNDED_OBJECT = EFFECTS.register("grounded", () -> new GroundingEffect());
	public static final RegistryObject<MobEffect> LAUNCH_OBJECT = EFFECTS.register("launched", () -> new LaunchingEffect());
	//public static final RegistryObject<Effect> STUN_OBJECT = EFFECTS.register("stunned", () -> new StunEffect());
	public static final RegistryObject<MobEffect> AQUA_OBJECT = EFFECTS.register("aqua", () -> new AquaEffect());
	public static final RegistryObject<MobEffect> HT_OBJECT = EFFECTS.register("hellstouch", () -> new HellsTouchEffect());
	public static final RegistryObject<MobEffect> CLENSE_OBJECT = EFFECTS.register("clense", () -> new ClenseEffect());
	public static final RegistryObject<MobEffect> CURE_OBJECT = EFFECTS.register("cure", () -> new CureEffect());
	public static final RegistryObject<MobEffect> NULLIFY_OBJECT = EFFECTS.register("nullify", () -> new NullifyEffect());
	public static final RegistryObject<MobEffect> RECALL_OBJECT = EFFECTS.register("recall", () -> new RecallEffect());
	public static final RegistryObject<MobEffect> PHOTOSYN_OBJECT = EFFECTS.register("photosynthesis", () -> new PhotosynthesisEffect());
	public static final RegistryObject<MobEffect> INFERN_OBJECT = EFFECTS.register("inferno", () -> new InfernoEffect());
	//public static final RegistryObject<Effect> CLAIRVOYANCE_OBJECT = EFFECTS.register("clairvoyance", () -> new ClairvoyanceEffect());
	public static final RegistryObject<MobEffect> REFLECT_OBJECT = EFFECTS.register("reflect", () -> new ReflectEffect());
	public static final RegistryObject<MobEffect> BLAST_OBJECT = EFFECTS.register("blast", () -> new BlastProtectionEffect());
	public static final RegistryObject<MobEffect> UNDEATH_OBJECT = EFFECTS.register("undeath", () -> new UndeathEffect());
	public static final RegistryObject<MobEffect> LIGHTNING_OBJECT = EFFECTS.register("lightning", () -> new LightningEffect());
	public static final RegistryObject<MobEffect> FRAIL_OBJECT = EFFECTS.register("frail", () -> new FrailEffect());
	public static final RegistryObject<MobEffect> FLIGHT_OBJECT = EFFECTS.register("flight", () -> new FlightEffect());


}
