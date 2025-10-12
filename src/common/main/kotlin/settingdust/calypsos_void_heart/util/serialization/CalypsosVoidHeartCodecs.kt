package settingdust.calypsos_void_heart.util.serialization

import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import com.mojang.serialization.MapCodec
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import settingdust.calypsos_void_heart.util.ServiceLoaderUtil

interface CalypsosVoidHeartMapCodecs {
    companion object : CalypsosVoidHeartMapCodecs by ServiceLoaderUtil.findService()

    val ATTRIBUTE_MODIFIER: MapCodec<AttributeModifier>
}

interface CalypsosVoidHeartCodecs {
    companion object : CalypsosVoidHeartCodecs {
        val ATTRIBUTE_MODIFIER = CalypsosVoidHeartMapCodecs.ATTRIBUTE_MODIFIER.codec()

        fun <E> stringResolver(toString: (E) -> String?, fromString: (String) -> E?): Codec<E> {
            return Codec.STRING.flatXmap(
                { name ->
                    fromString(name)?.let { DataResult.success(it) }
                        ?: DataResult.error { "Unknown element name: $name" }
                },
                { e ->
                    toString(e)?.let { DataResult.success(it) }
                        ?: DataResult.error { "Element with unknown name: $e" }
                }
            )
        }

        fun <T> Codec<T>.inlineList() =
            AlternativeCodec(listOf(), xmap({ listOf(it) }, { it.first() }))
    }
}