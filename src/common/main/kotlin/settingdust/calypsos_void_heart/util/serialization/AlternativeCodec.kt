package settingdust.calypsos_void_heart.util.serialization

import com.mojang.datafixers.util.Pair
import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import com.mojang.serialization.DynamicOps

data class AlternativeCodec<T>(
    val codec: Codec<T>,
    val alternative: Codec<T>
) : Codec<T> {
    override fun <T1> decode(ops: DynamicOps<T1>, input: T1): DataResult<Pair<T, T1>> {
        val result = codec.decode(ops, input)
        return if (result.error().isEmpty) result else alternative.decode(ops, input)
    }

    override fun <T1> encode(input: T, ops: DynamicOps<T1>, prefix: T1): DataResult<T1> {
        val result = codec.encode(input, ops, prefix)
        return if (result.error().isEmpty) result else alternative.encode(input, ops, prefix)
    }
}