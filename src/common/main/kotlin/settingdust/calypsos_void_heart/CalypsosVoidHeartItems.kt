package settingdust.calypsos_void_heart

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import settingdust.calypsos_void_heart.item.mining_laser.MiningLaserItem
import settingdust.calypsos_void_heart.util.ServiceLoaderUtil

object CalypsosVoidHeartItems {
    val MINING_LASER by lazy { BuiltInRegistries.ITEM.get(CalypsosVoidHeartKeys.MINING_LASER) as MiningLaserItem }

    fun register(register: (ResourceLocation, Item) -> Unit) {
        register(CalypsosVoidHeartKeys.MINING_LASER, ServiceLoaderUtil.findService<MiningLaserItem>())
    }
}