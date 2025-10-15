package settingdust.calypsos_void_heart.util.minecraft

import net.minecraft.world.item.ItemStack
import settingdust.calypsos_void_heart.CalypsosVoidHeart
import settingdust.kinecraft.util.ServiceLoaderUtil

interface ItemStackAdapter {
    companion object : ItemStackAdapter by ServiceLoaderUtil.findService(logger = CalypsosVoidHeart.LOGGER)

    fun ItemStack.isSameItemSameComponents(other: ItemStack): Boolean
}