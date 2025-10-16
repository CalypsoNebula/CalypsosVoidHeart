package settingdust.calypsos_void_heart.util

import settingdust.calypsos_void_heart.CalypsosVoidHeart
import settingdust.kinecraft.util.ServiceLoaderUtil

interface Entrypoint {
    companion object : Entrypoint {
        private val services by lazy {
            ServiceLoaderUtil.findServices<Entrypoint>(
                required = false,
                logger = CalypsosVoidHeart.LOGGER
            ).toList()
        }

        override fun construct() {
            services.forEach { it.construct() }
        }

        override fun init() {
            services.forEach { it.init() }
        }

        override fun clientInit() {
            services.forEach { it.clientInit() }
        }
    }

    fun construct() {}

    fun init() {}

    fun clientInit() {}
}