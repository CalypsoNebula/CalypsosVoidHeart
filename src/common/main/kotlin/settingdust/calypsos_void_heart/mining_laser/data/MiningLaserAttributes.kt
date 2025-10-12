package settingdust.calypsos_void_heart.mining_laser.data

import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import com.mojang.serialization.Codec
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.RangedAttribute
import settingdust.calypsos_void_heart.CalypsosVoidHeart

object MiningLaserAttributes : BiMap<ResourceLocation, Attribute> by HashBiMap.create() {
    val BY_ID_CODEC: Codec<Attribute> =
        ResourceLocation.CODEC.xmap(
            { MiningLaserAttributes.getValue(it) },
            { inverse().getValue(it) }
        )

    @JvmField
    val Energy = registerRangedAttribute("energy", 300.0, 1.0, Double.MAX_VALUE)

    @JvmField
    val BestRange = registerRangedAttribute("best_range", 6.0, 1.0, 48.0)

    @JvmField
    val MaxRange = registerRangedAttribute("max_range", 8.0, 1.0, 48.0)

    @JvmField
    val Damage = registerRangedAttribute("damage", 0.5, 0.0, 100.0)

    @JvmField
    val Speed = registerRangedAttribute("speed", 0.6, 0.0, 32.0)

    @JvmField
    val ModuleSlot = registerRangedAttribute("module_slot", 1.0, 1.0, 8.0)

    fun registerRangedAttribute(path: String, defaultValue: Double, min: Double, max: Double): Attribute {
        val id = CalypsosVoidHeart.id(path)
        val attribute =
            RangedAttribute(id.toLanguageKey(), defaultValue, min, max)
        this[id] = attribute
        return attribute
    }
}