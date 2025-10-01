package settingdust.calypsos_void_heart.forge

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import settingdust.calypsos_void_heart.CalypsosVoidHeart
import settingdust.calypsos_void_heart.util.Entrypoint
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(CalypsosVoidHeart.ID)
object CalypsosVoidHeartForge {
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