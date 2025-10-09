package settingdust.calypsos_void_heart.fabric

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import settingdust.calypsos_void_heart.CalypsosVoidHeart
import settingdust.calypsos_void_heart.CalypsosVoidHeartItems
import settingdust.calypsos_void_heart.util.Entrypoint

object CalypsosVoidHeartFabric {
    init {
        CalypsosVoidHeart
        Entrypoint.construct()
    }

    fun init() {
        CalypsosVoidHeartItems.register { id, item -> Registry.register(BuiltInRegistries.ITEM, id, item) }
        Entrypoint.init()
    }

    fun clientInit() {
        Entrypoint.clientInit()
    }
}
