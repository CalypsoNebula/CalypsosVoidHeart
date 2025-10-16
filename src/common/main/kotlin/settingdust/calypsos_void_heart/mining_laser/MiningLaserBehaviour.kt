package settingdust.calypsos_void_heart.mining_laser

import net.minecraft.world.entity.ai.attributes.AttributeMap
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import settingdust.calypsos_void_heart.CalypsosVoidHeart
import settingdust.calypsos_void_heart.CalypsosVoidHeartItems
import settingdust.calypsos_void_heart.mining_laser.data.MiningLaserComponent
import settingdust.calypsos_void_heart.mining_laser.data.MiningLaserComponents
import settingdust.calypsos_void_heart.util.minecraft.AttributeAdapter.Companion.clearModifiers
import settingdust.kinecraft.util.ServiceLoaderUtil

interface MiningLaserBehaviour {
    companion object : MiningLaserBehaviour by ServiceLoaderUtil.findService<MiningLaserBehaviour>(logger = CalypsosVoidHeart.LOGGER) {
        fun Player?.isUsingMiningLaser() = this?.uuid in CalypsosVoidHeartItems.MiningLaser.usingPlayers

        fun getComponents(stack: ItemStack): Sequence<MiningLaserComponent> {
            val tool = getDelegateTool(stack)
            val crystal = getCrystal(stack)
            val generator = getGenerator(stack)
            val modules = getModules(stack)

            return sequence {
                yield(tool.item)
                yield(crystal.item)
                yield(generator.item)
                yieldAll(modules.map { it.item })
            }.flatMap {
                MiningLaserComponents.itemToComponents[it] ?: error("Item isn't valid component: $it")
            }
        }

        fun getFuels(stack: ItemStack) = getComponents(stack).flatMap { it.fuels }.map { it.value() }.toList()

        fun applyComponents(stack: ItemStack) {
            val attributes = getAttributes(stack)
            attributes.clearModifiers()

            getComponents(stack).forEach {
                it.modifiers.forEach { attribute, modifier ->
                    (attributes.getInstance(attribute) ?: error("Attribute not found: $attribute"))
                        .addPermanentModifier(modifier)
                }
            }

            setAttributes(stack, attributes)
        }
    }

    val attributeSupplier: AttributeSupplier

    fun getAttributes(stack: ItemStack): AttributeMap

    fun setAttributes(stack: ItemStack, attributes: AttributeMap)

    fun getDelegateTool(stack: ItemStack): ItemStack

    fun setDelegateTool(stack: ItemStack, tool: ItemStack)

    fun getCrystal(stack: ItemStack): ItemStack

    fun setCrystal(stack: ItemStack, crystal: ItemStack)

    fun getGenerator(stack: ItemStack): ItemStack

    fun setGenerator(stack: ItemStack, generator: ItemStack)

    fun getModules(stack: ItemStack): List<ItemStack>

    fun setModules(stack: ItemStack, modules: List<ItemStack>)
}