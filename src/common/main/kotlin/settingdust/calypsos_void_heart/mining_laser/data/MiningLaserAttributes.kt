package settingdust.calypsos_void_heart.mining_laser.data

import net.minecraft.core.Holder
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.RangedAttribute
import settingdust.calypsos_void_heart.CalypsosVoidHeartKeys
import settingdust.calypsos_void_heart.CalypsosVoidHeartRegistries

object MiningLaserAttributes {
    val Energy by lazy { getAttribute(CalypsosVoidHeartKeys.ENERGY) }

    val BestRange by lazy { getAttribute(CalypsosVoidHeartKeys.BEST_RANGE) }

    val MaxRange by lazy { getAttribute(CalypsosVoidHeartKeys.MAX_RANGE) }

    val Damage by lazy { getAttribute(CalypsosVoidHeartKeys.DAMAGE) }

    val Speed by lazy { getAttribute(CalypsosVoidHeartKeys.SPEED) }

    val ModuleSlot by lazy { getAttribute(CalypsosVoidHeartKeys.MODULE_SLOT) }

    private fun getAttribute(id: ResourceLocation): Holder<Attribute> {
        return CalypsosVoidHeartRegistries.MINING_LASER_ATTRIBUTE.getHolderOrThrow(
            ResourceKey.create(
                CalypsosVoidHeartRegistries.MINING_LASER_ATTRIBUTE_KEY,
                id
            )
        )
    }

    fun register(register: (ResourceLocation, (id: ResourceLocation) -> Attribute) -> Unit) {
        register(CalypsosVoidHeartKeys.ENERGY) { RangedAttribute(it.toLanguageKey(), 300.0, 1.0, Double.MAX_VALUE) }
        register(CalypsosVoidHeartKeys.BEST_RANGE) { RangedAttribute(it.toLanguageKey(), 6.0, 1.0, 48.0) }
        register(CalypsosVoidHeartKeys.MAX_RANGE) { RangedAttribute(it.toLanguageKey(), 8.0, 1.0, 48.0) }
        register(CalypsosVoidHeartKeys.DAMAGE) { RangedAttribute(it.toLanguageKey(), 0.5, 0.0, 100.0) }
        register(CalypsosVoidHeartKeys.SPEED) { RangedAttribute(it.toLanguageKey(), 0.6, 0.0, 32.0) }
        register(CalypsosVoidHeartKeys.MODULE_SLOT) { RangedAttribute(it.toLanguageKey(), 1.0, 1.0, 7.0) }
    }
}