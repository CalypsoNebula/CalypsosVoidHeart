package settingdust.calypsos_void_heart.util.serialization

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import com.google.common.collect.SetMultimap
import com.mojang.serialization.Codec
import settingdust.calypsos_void_heart.util.serialization.CalypsosVoidHeartCodecs.Companion.inlineList

fun <K, V> setMultimapCodec(keyCodec: Codec<K>, valueCodec: Codec<V>): Codec<SetMultimap<K, V>> =
    multimapCodec(
        keyCodec,
        valueCodec,
        { HashMultimap.create() })

fun <K, V, M : Multimap<K, V>> multimapCodec(
    keyCodec: Codec<K>,
    valueCodec: Codec<V>,
    mapFactory: () -> M
): Codec<M> = Codec
    .unboundedMap(keyCodec, valueCodec.inlineList() as Codec<Collection<V>>)
    .xmap({
        val multimap = mapFactory()
        for ((key, value) in it) {
            multimap.putAll(key, value)
        }
        multimap
    }, { it.asMap() })