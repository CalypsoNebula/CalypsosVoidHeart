package settingdust.calypsos_void_heart.adapter

import settingdust.calypsos_void_heart.util.ServiceLoaderUtil

interface LoaderAdapter {
    companion object : LoaderAdapter by ServiceLoaderUtil.findService()

    val isClient: Boolean

    fun isModLoaded(modId: String): Boolean
}