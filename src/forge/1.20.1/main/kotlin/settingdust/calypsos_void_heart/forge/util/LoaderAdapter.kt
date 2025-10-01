package settingdust.calypsos_void_heart.forge.util

import net.minecraftforge.fml.loading.FMLLoader
import net.minecraftforge.fml.loading.LoadingModList
import settingdust.calypsos_void_heart.util.LoaderAdapter

class LoaderAdapter : LoaderAdapter {
    override val isClient: Boolean
        get() = FMLLoader.getDist().isClient

    override fun isModLoaded(modId: String) = LoadingModList.get().getModFileById(modId) != null
}