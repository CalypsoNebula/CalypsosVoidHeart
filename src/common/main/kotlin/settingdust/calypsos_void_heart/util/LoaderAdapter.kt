package settingdust.calypsos_void_heart.util

import net.minecraft.client.Minecraft
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.phys.EntityHitResult

interface LoaderAdapter {
    companion object : LoaderAdapter by ServiceLoaderUtil.findService()

    val isClient: Boolean

    fun isModLoaded(modId: String): Boolean

    fun onAttackBlock(callback: (Player, Level, InteractionHand, BlockPos, Direction) -> InteractionResult)

    fun onClientPreAttack(callback: (Minecraft, Player, Int) -> Unit)

    fun onAttackEntity(callback: (Player, Level, InteractionHand, Entity, EntityHitResult?) -> InteractionResult)
}