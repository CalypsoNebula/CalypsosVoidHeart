package settingdust.calypsos_void_heart.mining_laser

import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.SimpleMenuProvider
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.HitResult
import settingdust.calypsos_void_heart.mining_laser.PlayerMiningLaserExtension.isUsingMiningLaser
import settingdust.calypsos_void_heart.mining_laser.gui.MiningLaserConfigureMenu
import settingdust.calypsos_void_heart.mining_laser.network.ServerboundStartUseMiningLaser
import settingdust.calypsos_void_heart.mining_laser.network.ServerboundStopUseMiningLaser
import settingdust.calypsos_void_heart.mining_laser.render.MiningLaserGeoItem
import settingdust.calypsos_void_heart.util.LoaderAdapter
import settingdust.calypsos_void_heart.util.events.ClientAttackEvents
import settingdust.calypsos_void_heart.util.events.PlayerTickEvents
import settingdust.calypsos_void_heart.util.minecraft.ItemStackAdapter.Companion.hurtNoBreak
import settingdust.kinecraft.util.ServiceLoaderUtil
import software.bernie.geckolib.animatable.GeoItem


abstract class MiningLaserItem : Item(properties), GeoItem by ServiceLoaderUtil.findService<MiningLaserGeoItem>() {
    companion object {
        val properties = Properties().stacksTo(1)
    }

    init {
        with(LoaderAdapter) {
            onAttackBlock { player, _, hand, _, _ ->
                if (hand !== InteractionHand.MAIN_HAND) return@onAttackBlock InteractionResult.PASS
                if (!player.mainHandItem.`is`(this@MiningLaserItem) || !isUsable(player.mainHandItem)) {
                    player.isUsingMiningLaser = false
                    return@onAttackBlock InteractionResult.PASS
                }
                player.isUsingMiningLaser = true
                return@onAttackBlock InteractionResult.PASS
            }

            onAttackEntity { player, _, hand, _, _ ->
                if (hand !== InteractionHand.MAIN_HAND) return@onAttackEntity InteractionResult.PASS
                if (!player.mainHandItem.`is`(this@MiningLaserItem) || !isUsable(player.mainHandItem)) {
                    player.isUsingMiningLaser = false
                    return@onAttackEntity InteractionResult.PASS
                }
                player.isUsingMiningLaser = true
                return@onAttackEntity InteractionResult.PASS
            }
        }

        ClientAttackEvents.START.register { client, player ->
            if (
                client.hitResult?.type === HitResult.Type.MISS
                || !player.mainHandItem.`is`(this@MiningLaserItem)
                || !isUsable(player.mainHandItem)
            ) return@register
            if (!player.isUsingMiningLaser) ServerboundStartUseMiningLaser.Type.send()
            player.isUsingMiningLaser = true
        }

        ClientAttackEvents.STOP.register { _, player ->
            if (player.isUsingMiningLaser)
                ServerboundStopUseMiningLaser.Type.send()
            player.isUsingMiningLaser = false
        }

        PlayerTickEvents.POST.register { player ->
            if (!player.isUsingMiningLaser) return@register
            val mainHandItem = player.mainHandItem
            if (!mainHandItem.`is`(this@MiningLaserItem) || !isUsable(mainHandItem)) {
                player.isUsingMiningLaser = false
                return@register
            }

            if (mainHandItem.hurtNoBreak(player, 1)) {
                player.isUsingMiningLaser = false
            }
        }
    }

    fun isUsable(stack: ItemStack): Boolean {
        return stack.damageValue < stack.maxDamage
    }

    fun isCorrectToolForDrops(stack: ItemStack, state: BlockState): Boolean {
        return MiningLaserDataManager.getDelegateTool(stack).isCorrectToolForDrops(state)
    }

    override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResultHolder<ItemStack?> {
        if (!level.isClientSide && player is ServerPlayer) {
            player.openMenu(
                SimpleMenuProvider(
                    { id, inventory, _ -> MiningLaserConfigureMenu(id, inventory) },
                    Component.translatable("gui.calypsos_void_heart.mining_laser_configure")
                )
            )
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide)
    }
}