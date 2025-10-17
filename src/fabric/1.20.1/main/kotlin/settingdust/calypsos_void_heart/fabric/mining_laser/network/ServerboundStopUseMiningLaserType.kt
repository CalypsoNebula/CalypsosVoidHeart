package settingdust.calypsos_void_heart.fabric.mining_laser.network

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import settingdust.calypsos_void_heart.mining_laser.network.ServerboundStopUseMiningLaser

class ServerboundStopUseMiningLaserType : ServerboundStopUseMiningLaser.Type {
    init {
        ServerPlayNetworking.registerGlobalReceiver(ServerboundStopUseMiningLaser.Type.Id) { server, player, _, _, _ ->
            server.execute {
                ServerboundStopUseMiningLaser.Type.handle(server, player)
            }
        }
    }

    override fun send() {
        ClientPlayNetworking.send(ServerboundStopUseMiningLaser.Type.Id, PacketByteBufs.empty())
    }
}