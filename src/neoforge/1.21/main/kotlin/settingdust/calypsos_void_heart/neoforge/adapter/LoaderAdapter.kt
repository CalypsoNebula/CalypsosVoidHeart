package settingdust.calypsos_void_heart.neoforge.adapter

import net.neoforged.fml.loading.FMLLoader
import net.neoforged.fml.loading.LoadingModList
import settingdust.calypsos_void_heart.adapter.LoaderAdapter

class LoaderAdapter : LoaderAdapter {
    override val isClient: Boolean
        get() = FMLLoader.getDist().isClient

    override fun isModLoaded(modId: String) = LoadingModList.get().getModFileById(modId) != null
}