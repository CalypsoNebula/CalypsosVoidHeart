package settingdust.calypsos_void_heart.mining_laser

import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.SimpleMenuProvider
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import settingdust.calypsos_void_heart.mining_laser.gui.MiningLaserConfigureMenu
import settingdust.calypsos_void_heart.mining_laser.render.MiningLaserGeoItem
import settingdust.calypsos_void_heart.util.LoaderAdapter
import settingdust.calypsos_void_heart.util.events.PlayerTickEvents
import settingdust.kinecraft.util.ServiceLoaderUtil
import software.bernie.geckolib.animatable.GeoItem
import java.util.*


abstract class MiningLaserItem : Item(properties), GeoItem by ServiceLoaderUtil.findService<MiningLaserGeoItem>() {
    companion object {
        val properties = Properties().stacksTo(1)
    }

    val usingPlayers: MutableSet<UUID> = hashSetOf()

    init {
        with(LoaderAdapter) {
            onAttackBlock { player, _, hand, _, _ ->
                if (hand !== InteractionHand.MAIN_HAND) return@onAttackBlock InteractionResult.PASS
                if (!player.mainHandItem.`is`(this@MiningLaserItem)) {
                    usingPlayers -= player.uuid
                    return@onAttackBlock InteractionResult.PASS
                }
                usingPlayers += player.uuid
                return@onAttackBlock InteractionResult.PASS
            }

            onAttackEntity { player, _, hand, _, _ ->
                if (hand !== InteractionHand.MAIN_HAND) return@onAttackEntity InteractionResult.PASS
                if (!player.mainHandItem.`is`(this@MiningLaserItem)) {
                    usingPlayers -= player.uuid
                    return@onAttackEntity InteractionResult.PASS
                }
                usingPlayers += player.uuid
                return@onAttackEntity InteractionResult.PASS
            }
        }
        PlayerTickEvents.POST.register { player ->
            if (player.uuid in usingPlayers) {
                val mainHandItem = player.mainHandItem
                if (!mainHandItem.`is`(this@MiningLaserItem)
                    || mainHandItem.maxDamage <= mainHandItem.damageValue
                ) {
                    usingPlayers -= player.uuid
                    return@register
                }
                hurtNoBreak(mainHandItem, player)
            }
        }
    }

    abstract fun hurtNoBreak(stack: ItemStack, user: LivingEntity, amount: Int = 1)

    fun isCorrectToolForDrops(stack: ItemStack, state: BlockState): Boolean {
        return MiningLaserBehaviour.getDelegateTool(stack).isCorrectToolForDrops(state)
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