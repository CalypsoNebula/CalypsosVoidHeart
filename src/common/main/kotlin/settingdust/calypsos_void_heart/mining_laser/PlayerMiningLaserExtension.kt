package settingdust.calypsos_void_heart.mining_laser

import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.world.entity.player.Player

object PlayerMiningLaserExtension {
    @JvmField
    val DATA_USING_MINING_LASER =
        SynchedEntityData.defineId(Player::class.java, EntityDataSerializers.BOOLEAN) as EntityDataAccessor<Boolean>

    var Player.isUsingMiningLaser: Boolean
        get() = entityData.get(DATA_USING_MINING_LASER)
        set(value) = entityData.set(DATA_USING_MINING_LASER, value)
}