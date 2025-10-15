package settingdust.calypsos_void_heart.fabric

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder
import net.fabricmc.fabric.api.event.registry.RegistryAttribute
import net.minecraft.client.gui.screens.MenuScreens
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.inventory.AbstractContainerMenu
import settingdust.calypsos_void_heart.CalypsosVoidHeart
import settingdust.calypsos_void_heart.CalypsosVoidHeartItems
import settingdust.calypsos_void_heart.CalypsosVoidHeartMenuTypes
import settingdust.calypsos_void_heart.CalypsosVoidHeartRegistries
import settingdust.calypsos_void_heart.mining_laser.data.MiningLaserAttributes
import settingdust.calypsos_void_heart.util.Entrypoint
import settingdust.calypsos_void_heart.util.LoaderAdapter

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
        CalypsosVoidHeartMenuTypes.register { id, menuType, screenFactory ->
            Registry.register(BuiltInRegistries.MENU, id, menuType)
            if (LoaderAdapter.isClient) {
                @Suppress("UNCHECKED_CAST")
                MenuScreens.register(
                    menuType,
                    screenFactory as MenuScreens.ScreenConstructor<AbstractContainerMenu, AbstractContainerScreen<AbstractContainerMenu>>
                )
            }
        }
        CalypsosVoidHeartItems.register { id, item -> Registry.register(BuiltInRegistries.ITEM, id, item) }
        Entrypoint.init()
    }

    fun clientInit() {
        Entrypoint.clientInit()
    }
}
