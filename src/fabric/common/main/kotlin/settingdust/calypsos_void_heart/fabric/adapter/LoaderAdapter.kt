package settingdust.calypsos_void_heart.fabric.adapter

import net.fabricmc.api.EnvType
import net.fabricmc.loader.api.FabricLoader
import settingdust.calypsos_void_heart.util.LoaderAdapter

class LoaderAdapter : LoaderAdapter {
    override val isClient = FabricLoader.getInstance().environmentType === EnvType.CLIENT

    override fun isModLoaded(modId: String) = FabricLoader.getInstance().isModLoaded(modId)
}