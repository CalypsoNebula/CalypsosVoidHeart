package settingdust.calypsos_void_heart.v1_20.item.mining_laser

import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import settingdust.calypsos_void_heart.mining_laser.item.MiningLaserItem

class MiningLaserItem : MiningLaserItem() {
    override fun hurtEnemy(
        stack: ItemStack,
        target: LivingEntity,
        attacker: LivingEntity
    ): Boolean {
        stack.hurt(1, attacker.random, attacker as? ServerPlayer)
        return true
    }

    override fun mineBlock(
        stack: ItemStack,
        level: Level,
        state: BlockState,
        pos: BlockPos,
        entity: LivingEntity
    ): Boolean {
        if (!level.isClientSide && state.getDestroySpeed(level, pos) != 0f) {
            stack.hurt(1, entity.random, entity as? ServerPlayer)
        }
        return true
    }
}