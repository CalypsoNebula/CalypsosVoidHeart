package settingdust.calypsos_void_heart.v1_20.util

import com.google.common.collect.BiMap
import com.google.common.collect.ImmutableBiMap
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.util.ExtraCodecs
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import settingdust.calypsos_void_heart.util.serialization.CalypsosVoidHeartMapCodecs
import java.util.*

class CalypsosVoidHeartMapCodecs : CalypsosVoidHeartMapCodecs {
    private val nameToAttributeModifierOperations: BiMap<String, AttributeModifier.Operation> = ImmutableBiMap.of(
        "add_value", AttributeModifier.Operation.ADDITION,
        "add_multiplied_base", AttributeModifier.Operation.MULTIPLY_BASE,
        "add_multiplied_total", AttributeModifier.Operation.MULTIPLY_TOTAL
    )

    val OPERATION_CODEC =
        ExtraCodecs.stringResolverCodec<AttributeModifier.Operation>(
            { nameToAttributeModifierOperations.inverse()[it] },
            { nameToAttributeModifierOperations[it] }
        )

    override val ATTRIBUTE_MODIFIER = RecordCodecBuilder.mapCodec { instance ->
        instance.group(
            Codec.STRING
                .xmap({ UUID.fromString(it) }, { it.toString() })
                .fieldOf("id")
                .forGetter(AttributeModifier::getId),
            Codec.STRING.fieldOf("name").forGetter(AttributeModifier::getName),
            Codec.DOUBLE.fieldOf("amount").forGetter(AttributeModifier::getAmount),
            OPERATION_CODEC.fieldOf("operation").forGetter(AttributeModifier::getOperation)
        ).apply(instance, ::AttributeModifier)
    }
}