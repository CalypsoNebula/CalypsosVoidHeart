package settingdust.calypsos_void_heart.util.serialization

import com.google.common.collect.Multimap
import com.google.common.collect.Multimaps
import com.mojang.serialization.Codec

fun <K, V> SetMultimapCodec(keyCodec: Codec<K>, valueCodec: Codec<V>) =
    MultimapCodec(
        keyCodec,
        valueCodec,
        { map, valueFactory -> Multimaps.newSetMultimap(map, valueFactory) },
        { HashSet() })

class MultimapCodec<K, V, VC : Collection<V>, M : Multimap<K, V>>(
    keyCodec: Codec<K>,
    valueCodec: Codec<V>,
    mapFactory: (Map<K, Collection<V>>, () -> VC) -> M,
    valueFactory: () -> VC
) : Codec<M> by Codec
    .unboundedMap<K, Collection<V>>(keyCodec, valueCodec.listOf() as Codec<Collection<V>>)
    .xmap({ mapFactory(it, valueFactory) }, { it.asMap() })