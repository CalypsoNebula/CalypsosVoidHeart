package settingdust.calypsos_void_heart.mining_laser.data

import net.minecraft.core.Holder
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.RangedAttribute
import settingdust.calypsos_void_heart.CalypsosVoidHeartKeys
import settingdust.calypsos_void_heart.CalypsosVoidHeartRegistries

object MiningLaserAttributes {
    val Energy by lazy { getAttribute(CalypsosVoidHeartKeys.Energy) }

    val BestRange by lazy { getAttribute(CalypsosVoidHeartKeys.BestRange) }

    val MaxRange by lazy { getAttribute(CalypsosVoidHeartKeys.MaxRange) }

    val Damage by lazy { getAttribute(CalypsosVoidHeartKeys.Damage) }

    val Speed by lazy { getAttribute(CalypsosVoidHeartKeys.Speed) }

    val ModuleSlot by lazy { getAttribute(CalypsosVoidHeartKeys.ModuleSlot) }

    val MaxHeat by lazy { getAttribute(CalypsosVoidHeartKeys.MaxHeat) }

    private fun getAttribute(id: ResourceLocation): Holder<Attribute> {
        return CalypsosVoidHeartRegistries.MiningLaserAttribute.getHolderOrThrow(
            ResourceKey.create(
                CalypsosVoidHeartRegistries.MiningLaserAttributeKey,
                id
            )
        )
    }

    fun register(register: (ResourceLocation, (id: ResourceLocation) -> Attribute) -> Unit) {
        register(CalypsosVoidHeartKeys.Energy) { RangedAttribute(it.toLanguageKey(), 300.0, 1.0, Double.MAX_VALUE) }
        register(CalypsosVoidHeartKeys.BestRange) { RangedAttribute(it.toLanguageKey(), 6.0, 1.0, 48.0) }
        register(CalypsosVoidHeartKeys.MaxRange) { RangedAttribute(it.toLanguageKey(), 8.0, 1.0, 48.0) }
        register(CalypsosVoidHeartKeys.Damage) { RangedAttribute(it.toLanguageKey(), 0.5, 0.0, 100.0) }
        register(CalypsosVoidHeartKeys.Speed) { RangedAttribute(it.toLanguageKey(), 0.6, 0.0, 32.0) }
        register(CalypsosVoidHeartKeys.ModuleSlot) { RangedAttribute(it.toLanguageKey(), 1.0, 1.0, 7.0) }
        register(CalypsosVoidHeartKeys.MaxHeat) { RangedAttribute(it.toLanguageKey(), 200.0, 1.0, Double.MAX_VALUE) }
    }
}