package settingdust.calypsos_void_heart.fabric

import settingdust.calypsos_void_heart.CalypsosVoidHeart
import settingdust.calypsos_void_heart.adapter.Entrypoint

object CalypsosVoidHeartFabric {
    init {
        requireNotNull(CalypsosVoidHeart)
        Entrypoint.construct()
    }

    fun init() {
        Entrypoint.init()
    }

    fun clientInit() {
        Entrypoint.clientInit()
    }
}
