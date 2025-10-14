package settingdust.calypsos_void_heart.mining_laser.data

import com.google.common.collect.SetMultimap
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.Holder
import net.minecraft.core.HolderSet
import net.minecraft.core.RegistryCodecs
import net.minecraft.core.registries.Registries
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.item.Item
import settingdust.calypsos_void_heart.CalypsosVoidHeartRegistries
import settingdust.calypsos_void_heart.util.serialization.CalypsosVoidHeartCodecs
import settingdust.calypsos_void_heart.util.serialization.CalypsosVoidHeartCodecs.Companion.inlineList
import settingdust.calypsos_void_heart.util.serialization.SetMultimapCodec

data class MiningLaserComponent(
    val slots: Set<MiningLaserSlot>,
    val items: HolderSet<Item>,
    val modifiers: SetMultimap<Holder<Attribute>, AttributeModifier>,
    val fuels: HolderSet<Item>
) {
    companion object {
        val MAP_CODEC = RecordCodecBuilder.mapCodec<MiningLaserComponent> { instance ->
            instance.group(
                MiningLaserSlot.CODEC.inlineList()
                    .xmap({ it.toSet() }, { it.toList() })
                    .fieldOf("slots")
                    .forGetter { it.slots },
                RegistryCodecs.homogeneousList(Registries.ITEM)
                    .fieldOf("items")
                    .forGetter { it.items },
                SetMultimapCodec(
                    CalypsosVoidHeartRegistries.MINING_LASER_ATTRIBUTE.holderByNameCodec(),
                    CalypsosVoidHeartCodecs.ATTRIBUTE_MODIFIER
                ).fieldOf("modifiers").forGetter { it.modifiers },
                RegistryCodecs.homogeneousList(Registries.ITEM)
                    .fieldOf("fuels")
                    .forGetter { it.fuels }
            ).apply(instance, ::MiningLaserComponent)
        }

        val CODEC = MAP_CODEC.codec()
    }
}
