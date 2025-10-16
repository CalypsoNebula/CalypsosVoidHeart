package settingdust.calypsos_void_heart.v1_20.item.mining_laser

import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.ListTag
import net.minecraft.world.entity.ai.attributes.AttributeMap
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.item.ItemStack
import settingdust.calypsos_void_heart.CalypsosVoidHeartItems
import settingdust.calypsos_void_heart.mining_laser.MiningLaserBehaviour
import settingdust.calypsos_void_heart.mining_laser.data.MiningLaserAttributes

class MiningLaserBehaviour : MiningLaserBehaviour {
    companion object {
        const val TAG_ATTRIBUTES = "Attributes"
        const val TAG_DELEGATE_TOOL = "DelegateTool"
        const val TAG_CRYSTAL = "Crystal"
        const val TAG_GENERATOR = "Generator"
        const val TAG_MODULES = "Modules"
    }

    override val attributeSupplier =
        AttributeSupplier.builder()
            .add(MiningLaserAttributes.Damage.value())
            .add(MiningLaserAttributes.Energy.value())
            .add(MiningLaserAttributes.MaxRange.value())
            .add(MiningLaserAttributes.BestRange.value())
            .add(MiningLaserAttributes.Speed.value())
            .add(MiningLaserAttributes.ModuleSlot.value())
            .build()

    override fun getAttributes(stack: ItemStack): AttributeMap {
        require(stack.`is`(CalypsosVoidHeartItems.MiningLaser))
        val itemTag = stack.orCreateTag.getList(TAG_ATTRIBUTES, 10)
        return AttributeMap(attributeSupplier).also { it.load(itemTag) }
    }

    override fun setAttributes(
        stack: ItemStack,
        attributes: AttributeMap
    ) {
        require(stack.`is`(CalypsosVoidHeartItems.MiningLaser))
        stack.orCreateTag.put(TAG_ATTRIBUTES, attributes.save())
    }

    override fun getDelegateTool(stack: ItemStack): ItemStack {
        val itemTag = stack.getOrCreateTagElement(TAG_DELEGATE_TOOL)
        return ItemStack.of(itemTag)
    }

    override fun setDelegateTool(stack: ItemStack, tool: ItemStack) {
        stack.orCreateTag.put(TAG_DELEGATE_TOOL, tool.save(CompoundTag()))
    }

    override fun getModules(stack: ItemStack): List<ItemStack> {
        val itemTag = stack.orCreateTag
        return itemTag.getList(TAG_MODULES, 10).map { ItemStack.of(it as CompoundTag) }
    }

    override fun setModules(
        stack: ItemStack,
        modules: List<ItemStack>
    ) {
        val itemTag = stack.orCreateTag
        itemTag.put(
            TAG_MODULES,
            ListTag().also { list -> list.addAll(modules.map { it.save(CompoundTag()) }) }
        )
    }

    override fun getCrystal(stack: ItemStack): ItemStack {
        val itemTag = stack.getOrCreateTagElement(TAG_CRYSTAL)
        return ItemStack.of(itemTag)
    }

    override fun setCrystal(stack: ItemStack, crystal: ItemStack) {
        stack.orCreateTag.put(TAG_CRYSTAL, crystal.save(CompoundTag()))
    }

    override fun getGenerator(stack: ItemStack): ItemStack {
        val itemTag = stack.getOrCreateTagElement(TAG_GENERATOR)
        return ItemStack.of(itemTag)
    }

    override fun setGenerator(
        stack: ItemStack,
        generator: ItemStack
    ) {
        stack.orCreateTag.put(TAG_GENERATOR, generator.save(CompoundTag()))
    }
}