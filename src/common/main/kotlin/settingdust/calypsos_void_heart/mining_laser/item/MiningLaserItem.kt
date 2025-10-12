package settingdust.calypsos_void_heart.mining_laser.item

import net.minecraft.core.BlockPos
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import settingdust.calypsos_void_heart.util.LoaderAdapter
import java.util.*

abstract class MiningLaserItem : Item(properties) {
    companion object {
        val properties = Properties().stacksTo(1)
    }

    val usingPlayers: MutableSet<UUID> = hashSetOf()

    init {
        with(LoaderAdapter) {
            onAttackBlock { player, _, hand, _, _ ->
                if (hand !== InteractionHand.MAIN_HAND) return@onAttackBlock InteractionResult.PASS
                val itemInHand = player.getItemInHand(hand)
                if (!itemInHand.`is`(this@MiningLaserItem)) {
                    usingPlayers -= player.uuid
                    return@onAttackBlock InteractionResult.PASS
                }
                usingPlayers += player.uuid
                return@onAttackBlock InteractionResult.PASS
            }

            onAttackEntity { player, _, hand, _, _ ->
                if (hand !== InteractionHand.MAIN_HAND) return@onAttackEntity InteractionResult.PASS
                val itemInHand = player.getItemInHand(hand)
                if (!itemInHand.`is`(this@MiningLaserItem)) {
                    usingPlayers -= player.uuid
                    return@onAttackEntity InteractionResult.PASS
                }
                usingPlayers += player.uuid
                return@onAttackEntity InteractionResult.PASS
            }
        }
    }

    abstract override fun hurtEnemy(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean

    abstract override fun mineBlock(
        stack: ItemStack,
        level: Level,
        state: BlockState,
        pos: BlockPos,
        entity: LivingEntity
    ): Boolean

    fun isCorrectToolForDrops(stack: ItemStack, state: BlockState): Boolean {
        return MiningLaserBehaviour.getDelegateTool(stack).isCorrectToolForDrops(state)
    }

}