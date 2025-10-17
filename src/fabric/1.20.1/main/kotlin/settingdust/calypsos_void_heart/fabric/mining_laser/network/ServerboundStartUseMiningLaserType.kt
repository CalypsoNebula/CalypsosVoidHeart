package settingdust.calypsos_void_heart.fabric.mining_laser.network

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import settingdust.calypsos_void_heart.mining_laser.network.ServerboundStartUseMiningLaser

class ServerboundStartUseMiningLaserType : ServerboundStartUseMiningLaser.Type {
    init {
        ServerPlayNetworking.registerGlobalReceiver(ServerboundStartUseMiningLaser.Id) { server, player, _, _, _ ->
            ServerboundStartUseMiningLaser.Type.handle(server, player)
        }
    }

    override fun send() {
        ClientPlayNetworking.send(ServerboundStartUseMiningLaser.Id, PacketByteBufs.empty())
    }
}