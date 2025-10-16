package settingdust.calypsos_void_heart.v1_20.item.mining_laser

import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import settingdust.calypsos_void_heart.mining_laser.MiningLaserItem
import software.bernie.geckolib.animatable.SingletonGeoAnimatable


class MiningLaserItem : MiningLaserItem() {
    override fun hurtNoBreak(
        stack: ItemStack,
        user: LivingEntity,
        amount: Int
    ) {
        stack.hurt(amount, user.random, user as? ServerPlayer)
    }

    init {
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }
}