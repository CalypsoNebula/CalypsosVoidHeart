package settingdust.calypsos_void_heart.fabric

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder
import net.fabricmc.fabric.api.event.registry.RegistryAttribute
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import settingdust.calypsos_void_heart.CalypsosVoidHeart
import settingdust.calypsos_void_heart.CalypsosVoidHeartItems
import settingdust.calypsos_void_heart.CalypsosVoidHeartRegistries
import settingdust.calypsos_void_heart.mining_laser.data.MiningLaserAttributes
import settingdust.calypsos_void_heart.util.Entrypoint

object CalypsosVoidHeartFabric {
    init {
        requireNotNull(CalypsosVoidHeart)
        Entrypoint.construct()
    }

    fun init() {
        CalypsosVoidHeartRegistries.register {
            FabricRegistryBuilder.from(it)
                .attribute(RegistryAttribute.MODDED)
                .buildAndRegister()
        }
        MiningLaserAttributes.register { id, value ->
            Registry.register(
                CalypsosVoidHeartRegistries.MINING_LASER_ATTRIBUTE,
                id,
                value(id)
            )
        }
        CalypsosVoidHeartItems.register { id, item -> Registry.register(BuiltInRegistries.ITEM, id, item) }
        Entrypoint.init()
    }

    fun clientInit() {
        Entrypoint.clientInit()
    }
}
