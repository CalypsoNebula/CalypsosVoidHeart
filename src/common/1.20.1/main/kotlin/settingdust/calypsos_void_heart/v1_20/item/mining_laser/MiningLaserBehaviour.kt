package settingdust.calypsos_void_heart.v1_20.item.mining_laser

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.ai.attributes.AttributeMap
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import settingdust.calypsos_void_heart.CalypsosVoidHeartItems
import settingdust.calypsos_void_heart.item.mining_laser.MiningLaserAttribute
import settingdust.calypsos_void_heart.item.mining_laser.MiningLaserBehaviour

class MiningLaserBehaviour : MiningLaserBehaviour {
    companion object {
        const val TAG_DELEGATE_TOOL = "DelegateTool"
        const val TAG_ATTRIBUTES = "Attributes"
        const val TAG_FUELS = "Fuels"
    }

    override val attributeSupplier =
        AttributeSupplier.builder()
            .add(MiningLaserAttribute.Damage)
            .add(MiningLaserAttribute.Energy)
            .add(MiningLaserAttribute.MaxRange)
            .add(MiningLaserAttribute.BestRange)
            .add(MiningLaserAttribute.Speed)
            .add(MiningLaserAttribute.ModuleSlot)
            .build()

    override fun getAttributes(stack: ItemStack): AttributeMap {
        require(stack.`is`(CalypsosVoidHeartItems.MINING_LASER))
        val itemTag = stack.orCreateTag.getList(TAG_ATTRIBUTES, 10)
        return AttributeMap(attributeSupplier).also { it.load(itemTag) }
    }

    override fun setAttributes(
        stack: ItemStack,
        attributes: AttributeMap
    ) {
        require(stack.`is`(CalypsosVoidHeartItems.MINING_LASER))
        stack.orCreateTag.put(TAG_ATTRIBUTES, attributes.save())
    }

    override fun getFuels(stack: ItemStack): TagKey<Item> {
        return stack.getOrCreateTagElement(TAG_FUELS).getString("Fuels")
            .takeUnless { it.isBlank() }
            ?.let { TagKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation(it)) }
            ?: MiningLaserBehaviour.defaultFuels
    }

    override fun setFuels(
        stack: ItemStack,
        fuels: TagKey<Item>
    ) {
        stack.getOrCreateTagElement(TAG_FUELS).putString("Fuels", fuels.location().toString())
    }

    override fun setDelegateTool(stack: ItemStack, tool: ItemStack) {
        tool.save(stack.getOrCreateTagElement(TAG_DELEGATE_TOOL))
    }

    override fun getDelegateTool(stack: ItemStack): ItemStack {
        val itemTag = stack.getOrCreateTagElement(TAG_DELEGATE_TOOL)
        return ItemStack.of(itemTag)
    }
}