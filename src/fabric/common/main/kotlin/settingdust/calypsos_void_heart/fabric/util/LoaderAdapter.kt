package settingdust.calypsos_void_heart.fabric.util

import net.fabricmc.api.EnvType
import net.fabricmc.fabric.api.event.client.player.ClientPreAttackCallback
import net.fabricmc.fabric.api.event.player.AttackBlockCallback
import net.fabricmc.fabric.api.event.player.AttackEntityCallback
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.client.Minecraft
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.phys.EntityHitResult
import settingdust.calypsos_void_heart.util.LoaderAdapter

class LoaderAdapter : LoaderAdapter {
    override val isClient = FabricLoader.getInstance().environmentType === EnvType.CLIENT

    override fun isModLoaded(modId: String) = FabricLoader.getInstance().isModLoaded(modId)
    override fun onAttackBlock(callback: (Player, Level, InteractionHand, BlockPos, Direction) -> InteractionResult) {
        AttackBlockCallback.EVENT.register(callback)
    }

    override fun onClientPreAttack(callback: (Minecraft, Player, Int) -> Unit) {
        ClientPreAttackCallback.EVENT.register { client, player, clickCount ->
            callback(client, player, clickCount)
            return@register false
        }
    }

    override fun onAttackEntity(callback: (Player, Level, InteractionHand, Entity, EntityHitResult?) -> InteractionResult) {
        AttackEntityCallback.EVENT.register(callback)
    }

    fun onPlayerTick(callback: (Player) -> Unit) {

    }
}