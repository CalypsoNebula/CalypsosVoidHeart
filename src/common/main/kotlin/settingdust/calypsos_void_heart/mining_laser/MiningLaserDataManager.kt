package settingdust.calypsos_void_heart.mining_laser

import net.minecraft.world.entity.ai.attributes.AttributeMap
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.item.ItemStack
import settingdust.calypsos_void_heart.CalypsosVoidHeart
import settingdust.calypsos_void_heart.mining_laser.data.MiningLaserComponent
import settingdust.calypsos_void_heart.mining_laser.data.MiningLaserComponents
import settingdust.calypsos_void_heart.util.minecraft.AttributeAdapter.Companion.clearModifiers
import settingdust.kinecraft.util.ServiceLoaderUtil

interface MiningLaserDataManager {
    companion object :
        MiningLaserDataManager by ServiceLoaderUtil.findService<MiningLaserDataManager>(logger = CalypsosVoidHeart.LOGGER) {

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

        fun getFuels(stack: ItemStack) = getComponents(stack).flatMap { it.fuels }.map { it.value() }.toList()
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

    fun getHeat(stack: ItemStack): Int

    fun setHeat(stack: ItemStack, heat: Int)
}