package settingdust.calypsos_void_heart.mining_laser.data

import com.google.common.collect.HashMultimap
import com.google.common.collect.SetMultimap
import com.mojang.serialization.JsonOps
import net.minecraft.resources.FileToIdConverter
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.server.packs.resources.SimplePreparableReloadListener
import net.minecraft.util.GsonHelper
import net.minecraft.util.profiling.ProfilerFiller
import net.minecraft.world.item.Item
import settingdust.calypsos_void_heart.CalypsosVoidHeart
import settingdust.calypsos_void_heart.CalypsosVoidHeartKeys
import settingdust.calypsos_void_heart.util.Entrypoint
import settingdust.calypsos_void_heart.util.LoaderAdapter
import settingdust.kinecraft.util.DataResultExtension.Companion.unwrap

object MiningLaserComponents : SimplePreparableReloadListener<Unit>(), Entrypoint {
    val components: MutableList<MiningLaserComponent> = mutableListOf()
    val slotToComponents: SetMultimap<MiningLaserSlot, MiningLaserComponent> = HashMultimap.create()
    val itemToComponents: SetMultimap<Item, MiningLaserComponent> = HashMultimap.create()

    private val finder =
        FileToIdConverter.json("${CalypsosVoidHeart.ID}/${CalypsosVoidHeartKeys.MINING_LASER.path}/component")

    override fun init() {
        LoaderAdapter.addReloadListener(PackType.SERVER_DATA, CalypsosVoidHeart.id("components"), this)
    }

    override fun prepare(
        manager: ResourceManager,
        profiler: ProfilerFiller
    ) {
        val components = finder.listMatchingResources(manager)
            .map { (id, resource) ->
                try {
                    resource.openAsReader().use { reader ->
                        val result = MiningLaserComponent.CODEC.parse(
                            JsonOps.INSTANCE,
                            GsonHelper.parse(reader)
                        )
                        result.unwrap()
                    }
                } catch (t: Throwable) {
                    throw RuntimeException("Failed to load mining laser component $id", t)
                }
            }
        this.components.clear()
        this.slotToComponents.clear()
        this.itemToComponents.clear()
        for (component in components) {
            this.components.add(component)
            for (slot in component.slots) {
                this.slotToComponents.put(slot, component)
            }
            for (item in component.items) {
                this.itemToComponents.put(item.value(), component)
            }
        }
    }

    override fun apply(data: Unit, manager: ResourceManager, profiler: ProfilerFiller) {}
}