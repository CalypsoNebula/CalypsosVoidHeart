package settingdust.calypsos_void_heart.util.minecraft

import net.minecraft.resources.ResourceLocation
import settingdust.calypsos_void_heart.util.ServiceLoaderUtil

interface MinecraftAdapter {
    companion object : MinecraftAdapter by ServiceLoaderUtil.findService()

    fun id(namespace: String, path: String): ResourceLocation
}