package settingdust.calypsos_void_heart.mining_laser.network

import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import settingdust.calypsos_void_heart.CalypsosVoidHeart
import settingdust.calypsos_void_heart.mining_laser.PlayerMiningLaserExtension.isUsingMiningLaser
import settingdust.kinecraft.util.ServiceLoaderUtil

data object ServerboundStartUseMiningLaser {
    interface Type {
        companion object : Type by ServiceLoaderUtil.findService(logger = CalypsosVoidHeart.LOGGER) {
            val Id = CalypsosVoidHeart.id("start_use_mining_laser")

            fun handle(server: MinecraftServer, player: ServerPlayer) {
                server.execute {
                    player.isUsingMiningLaser = true
                }
            }
        }

        fun send()
    }
}
