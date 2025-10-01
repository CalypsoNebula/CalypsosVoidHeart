package settingdust.calypsos_void_heart

import org.apache.logging.log4j.LogManager
import settingdust.calypsos_void_heart.util.MinecraftAdapter
import settingdust.calypsos_void_heart.util.ServiceLoaderUtil

object CalypsosVoidHeart {
    const val ID = "calypsos_void_heart"

    val LOGGER = LogManager.getLogger()

    init {
        ServiceLoaderUtil.defaultLogger = LOGGER
    }

    fun id(path: String) = MinecraftAdapter.id(ID, path)
}