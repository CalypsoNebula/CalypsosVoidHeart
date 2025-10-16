package settingdust.calypsos_void_heart

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import settingdust.calypsos_void_heart.mining_laser.MiningLaserItem
import settingdust.kinecraft.util.ServiceLoaderUtil

object CalypsosVoidHeartItems {
    val MiningLaser by lazy { BuiltInRegistries.ITEM.get(CalypsosVoidHeartKeys.MiningLaser) as MiningLaserItem }

    fun register(register: (ResourceLocation, Item) -> Unit) {
        register(CalypsosVoidHeartKeys.MiningLaser, ServiceLoaderUtil.findService<MiningLaserItem>())
    }
}