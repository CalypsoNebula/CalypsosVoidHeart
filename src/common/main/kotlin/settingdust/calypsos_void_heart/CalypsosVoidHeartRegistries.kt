package settingdust.calypsos_void_heart

import com.mojang.serialization.Lifecycle
import net.minecraft.core.MappedRegistry
import net.minecraft.core.Registry
import net.minecraft.core.WritableRegistry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.ai.attributes.Attribute

object CalypsosVoidHeartRegistries {
    val MiningLaserAttributeKey =
        ResourceKey.createRegistryKey<Attribute>(CalypsosVoidHeartKeys.MiningLaserAttribute)
    val MiningLaserAttribute by lazy { getRegistry(MiningLaserAttributeKey) }

    private fun <T> getRegistry(key: ResourceKey<Registry<T>>) =
        (BuiltInRegistries.REGISTRY as Registry<Registry<T>>).get(key) ?: error("Registry $key not found")

    fun register(register: (WritableRegistry<*>) -> Unit) {
        register(MappedRegistry(MiningLaserAttributeKey, Lifecycle.stable(), false))
    }
}