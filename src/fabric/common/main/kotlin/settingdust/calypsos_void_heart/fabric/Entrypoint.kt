package settingdust.calypsos_void_heart.fabric

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder
import net.fabricmc.fabric.api.event.registry.RegistryAttribute
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.minecraft.client.gui.screens.MenuScreens
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.core.Registry
import net.minecraft.core.WritableRegistry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.resources.PreparableReloadListener
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.util.profiling.ProfilerFiller
import net.minecraft.world.inventory.AbstractContainerMenu
import settingdust.calypsos_void_heart.*
import settingdust.calypsos_void_heart.mining_laser.data.MiningLaserAttributes
import settingdust.calypsos_void_heart.mining_laser.render.LaserSprites
import settingdust.calypsos_void_heart.util.Entrypoint
import settingdust.calypsos_void_heart.util.LoaderAdapter
import java.util.concurrent.Executor

object CalypsosVoidHeartFabric {
    init {
        requireNotNull(CalypsosVoidHeart)
        Entrypoint.construct()
    }

    fun init() {
        CalypsosVoidHeartRegistries.register {
            FabricRegistryBuilder.from(it as WritableRegistry<Any>)
                .attribute(RegistryAttribute.MODDED)
                .buildAndRegister()
        }
        MiningLaserAttributes.register { id, value ->
            Registry.register(
                CalypsosVoidHeartRegistries.MiningLaserAttribute,
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
        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES)
            .registerReloadListener(object : IdentifiableResourceReloadListener {
                override fun getFabricId() = CalypsosVoidHeartKeys.LaserSprites

                override fun reload(
                    p0: PreparableReloadListener.PreparationBarrier,
                    p1: ResourceManager,
                    p2: ProfilerFiller,
                    p3: ProfilerFiller,
                    p4: Executor,
                    p5: Executor
                ) = LaserSprites.reload(p0, p1, p2, p3, p4, p5)
            })

        Entrypoint.clientInit()
    }
}
