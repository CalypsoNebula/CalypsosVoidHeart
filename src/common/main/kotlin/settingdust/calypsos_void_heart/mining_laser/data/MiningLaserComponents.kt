package settingdust.calypsos_void_heart.mining_laser.data

import com.google.common.collect.HashMultimap
import com.google.common.collect.SetMultimap
import com.mojang.serialization.JsonOps
import net.minecraft.core.RegistryAccess
import net.minecraft.resources.FileToIdConverter
import net.minecraft.resources.RegistryOps
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.server.packs.resources.SimplePreparableReloadListener
import net.minecraft.util.GsonHelper
import net.minecraft.util.profiling.ProfilerFiller
import net.minecraft.world.item.Item
import settingdust.calypsos_void_heart.CalypsosVoidHeart
import settingdust.calypsos_void_heart.CalypsosVoidHeartKeys
import settingdust.calypsos_void_heart.util.AddServerResourcesListenerCallback
import settingdust.calypsos_void_heart.util.Entrypoint
import settingdust.kinecraft.util.DataResultExtension.Companion.unwrap

class MiningLaserComponents : Entrypoint {
    companion object {
        val components: MutableList<MiningLaserComponent> = mutableListOf()
        val slotToComponents: SetMultimap<MiningLaserSlot, MiningLaserComponent> = HashMultimap.create()
        val itemToComponents: SetMultimap<Item, MiningLaserComponent> = HashMultimap.create()
    }

    override fun init() {
        AddServerResourcesListenerCallback.CALLBACK.register { _, registryAccess ->
            return@register listOf(ReloadListener(registryAccess))
        }
    }

    class ReloadListener(private val registryAccess: RegistryAccess) : SimplePreparableReloadListener<Unit>() {
        private val finder =
            FileToIdConverter.json("${CalypsosVoidHeart.ID}/${CalypsosVoidHeartKeys.MiningLaser.path}/component")

        override fun prepare(
            manager: ResourceManager,
            profiler: ProfilerFiller
        ) {
            val foundComponents = finder.listMatchingResources(manager)
                .map { (id, resource) ->
                    try {
                        resource.openAsReader().use { reader ->
                            val result = MiningLaserComponent.CODEC.parse(
                                RegistryOps.create(JsonOps.INSTANCE, registryAccess),
                                GsonHelper.parse(reader)
                            )
                            result.unwrap()
                        }
                    } catch (t: Throwable) {
                        throw RuntimeException("Failed to load mining laser component $id", t)
                    }
                }
            components.clear()
            slotToComponents.clear()
            itemToComponents.clear()
            for (component in foundComponents) {
                components.add(component)
                for (slot in component.slots) {
                    slotToComponents.put(slot, component)
                }
                for (item in component.items) {
                    itemToComponents.put(item.value(), component)
                }
            }
        }

        override fun apply(data: Unit, manager: ResourceManager, profiler: ProfilerFiller) {}
    }
}