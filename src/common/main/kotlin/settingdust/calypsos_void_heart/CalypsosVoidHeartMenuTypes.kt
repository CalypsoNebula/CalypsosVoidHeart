package settingdust.calypsos_void_heart

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.inventory.MenuType
import net.minecraft.world.item.Item
import settingdust.calypsos_void_heart.mining_laser.gui.MiningLaserConfigureMenu
import settingdust.calypsos_void_heart.mining_laser.item.MiningLaserItem
import settingdust.calypsos_void_heart.util.ServiceLoaderUtil

object CalypsosVoidHeartMenuTypes {
    val MINING_LASER_CONFIGURE by lazy { BuiltInRegistries.MENU.get(CalypsosVoidHeartKeys.MINING_LASER_CONFIGURE) as MenuType<MiningLaserConfigureMenu> }

    fun register(register: (ResourceLocation, Item) -> Unit) {
        register(CalypsosVoidHeartKeys.MINING_LASER_CONFIGURE, ServiceLoaderUtil.findService<MiningLaserItem>())
    }
}