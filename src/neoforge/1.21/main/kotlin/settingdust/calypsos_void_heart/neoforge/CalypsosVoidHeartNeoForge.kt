package settingdust.calypsos_void_heart.neoforge

import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import settingdust.calypsos_void_heart.CalypsosVoidHeart
import settingdust.calypsos_void_heart.util.Entrypoint
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS

@Mod(CalypsosVoidHeart.ID)
object CalypsosVoidHeartNeoForge {
    init {
        requireNotNull(CalypsosVoidHeart)
        Entrypoint.construct()
        MOD_BUS.apply {
            addListener<FMLCommonSetupEvent> {
                Entrypoint.init()
            }
            addListener<FMLClientSetupEvent> { Entrypoint.clientInit() }
        }
    }
}