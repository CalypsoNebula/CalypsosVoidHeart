package settingdust.calypsos_void_heart.item.mining_laser

import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.item.Item
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
}