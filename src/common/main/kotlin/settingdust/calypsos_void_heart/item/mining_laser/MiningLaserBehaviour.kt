package settingdust.calypsos_void_heart.item.mining_laser

import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.ai.attributes.AttributeMap
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import settingdust.calypsos_void_heart.CalypsosVoidHeartItems
import settingdust.calypsos_void_heart.util.ServiceLoaderUtil

interface MiningLaserBehaviour {
    companion object : MiningLaserBehaviour by ServiceLoaderUtil.findService<MiningLaserBehaviour>() {
        val defaultFuels = ItemTags.COALS
        fun Player?.isUsingMiningLaser() = this?.uuid in CalypsosVoidHeartItems.MINING_LASER.usingPlayers
    }

    val attributeSupplier: AttributeSupplier

    fun getAttributes(stack: ItemStack): AttributeMap

    fun setAttributes(stack: ItemStack, attributes: AttributeMap)

    fun getFuels(stack: ItemStack): TagKey<Item>

    fun setFuels(stack: ItemStack, fuels: TagKey<Item> = defaultFuels)

    fun setDelegateTool(stack: ItemStack, tool: ItemStack)

    fun getDelegateTool(stack: ItemStack): ItemStack
}