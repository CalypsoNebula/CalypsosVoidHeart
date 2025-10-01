package settingdust.calypsos_void_heart

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import settingdust.calypsos_void_heart.util.ServiceLoaderUtil

interface CalypsosVoidHeartItems {
    companion object : CalypsosVoidHeartItems {
         val MINING_LASER by lazy { BuiltInRegistries.ITEM.get(CalypsosVoidHeartKeys.MINING_LASER) }

        private val implementations = ServiceLoaderUtil.findServices<CalypsosVoidHeartItems>()

        override fun register(register: (ResourceLocation, Item) -> Unit) {
            for (implementation in implementations) {
                implementation.register(register)
            }
        }
    }

    fun register(register: (ResourceLocation, Item) -> Unit)
}