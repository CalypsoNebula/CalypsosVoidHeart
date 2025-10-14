package settingdust.calypsos_void_heart.util.minecraft

import net.minecraft.world.item.ItemStack
import settingdust.calypsos_void_heart.util.ServiceLoaderUtil

interface ItemStackAdapter {
    companion object : ItemStackAdapter by ServiceLoaderUtil.findService()

    fun ItemStack.isSameItemSameComponents(other: ItemStack): Boolean
}