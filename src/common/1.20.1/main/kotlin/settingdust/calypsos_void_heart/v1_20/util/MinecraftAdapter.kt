package settingdust.calypsos_void_heart.v1_20.util

import net.minecraft.resources.ResourceLocation
import settingdust.calypsos_void_heart.util.MinecraftAdapter

class MinecraftAdapter : MinecraftAdapter {
    override fun id(namespace: String, path: String) = ResourceLocation(namespace, path)
}