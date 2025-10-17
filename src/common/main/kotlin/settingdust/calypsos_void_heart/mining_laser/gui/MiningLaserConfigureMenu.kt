package settingdust.calypsos_void_heart.mining_laser.gui

import net.minecraft.world.Container
import net.minecraft.world.SimpleContainer
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack
import settingdust.calypsos_void_heart.CalypsosVoidHeartItems
import settingdust.calypsos_void_heart.CalypsosVoidHeartMenuTypes
import settingdust.calypsos_void_heart.mining_laser.MiningLaserDataManager
import settingdust.calypsos_void_heart.mining_laser.data.MiningLaserAttributes
import settingdust.calypsos_void_heart.mining_laser.data.MiningLaserComponents
import settingdust.calypsos_void_heart.mining_laser.data.MiningLaserSlot
import settingdust.calypsos_void_heart.mixin.SimpleContainerAccessor
import settingdust.calypsos_void_heart.util.minecraft.AttributeAdapter.Companion.getValue
import settingdust.calypsos_void_heart.util.minecraft.ItemStackAdapter.Companion.isSameItemSameComponents

class MiningLaserConfigureMenu(
    containerId: Int,
    private val inventory: Inventory
) : AbstractContainerMenu(CalypsosVoidHeartMenuTypes.MiningLaserConfigure, containerId) {
    companion object {
        const val SLOT_SIZE = 18

        const val SLOT_COUNT = 11

        const val INV_SLOT_COUNT = 27
        const val INV_SLOT_START = SLOT_COUNT
        const val INV_SLOT_END = INV_SLOT_START + INV_SLOT_COUNT

        const val HOTBAR_SLOT_COUNT = 9
        const val HOTBAR_SLOT_START = INV_SLOT_END
        const val HOTBAR_SLOT_END = HOTBAR_SLOT_START + HOTBAR_SLOT_COUNT

        const val MINING_LASER_SLOT_INDEX = 0
        const val TOOL_SLOT_INDEX = 1
        const val CRYSTAL_SLOT_INDEX = 2
        const val GENERATOR_SLOT_INDEX = 3
        const val MODULE_SLOT_COUNT = 6
        const val MODULE_SLOT_INDEX_START = 4
        const val MODULE_SLOT_INDEX_END = MODULE_SLOT_INDEX_START + MODULE_SLOT_COUNT
        const val FUEL_SLOT_INDEX = 10
    }

    val miningLaserContainer = object : SimpleContainer(1) {
        override fun setChanged() {
            super.setChanged()
            slotsChanged(this)
        }
    }

    val toolContainer = object : SimpleContainer(1) {
        override fun setChanged() {
            super.setChanged()
            slotsChanged(this)
        }
    }

    val crystalContainer = object : SimpleContainer(1) {
        override fun setChanged() {
            super.setChanged()
            slotsChanged(this)
        }
    }

    val generatorContainer = object : SimpleContainer(1) {
        override fun setChanged() {
            super.setChanged()
            slotsChanged(this)
        }
    }

    val moduleContainer = object : SimpleContainer(MODULE_SLOT_COUNT) {
        override fun setChanged() {
            super.setChanged()
            slotsChanged(this)
        }
    }

    val fuelContainer = object : SimpleContainer(1) {
        override fun setChanged() {
            super.setChanged()
            slotsChanged(this)
        }
    }

    private var initializing = true

    init {
        val miningLaser = inventory.getSelected()

        addSlot(object : Slot(miningLaserContainer, 0, 8 + SLOT_SIZE + 2, 20) {
            override fun mayPlace(stack: ItemStack) = false

            override fun mayPickup(player: Player) = false

            override fun isHighlightable() = false
        }).set(miningLaser)

        addSlot(object : Slot(toolContainer, 0, 8, 20) {
            override fun mayPlace(stack: ItemStack): Boolean {
                if (stack.isSameItemSameComponents(item)) return false
                val components = MiningLaserComponents.itemToComponents[stack.item]
                return components.any { (slots) -> MiningLaserSlot.Tool in slots }
            }

            override fun safeInsert(stack: ItemStack, increment: Int): ItemStack {
                return super.safeInsert(stack, 1)
            }
        }).set(MiningLaserDataManager.getDelegateTool(miningLaser))

        addSlot(object : Slot(crystalContainer, 0, 8, 20 + SLOT_SIZE) {
            override fun mayPlace(stack: ItemStack): Boolean {
                if (stack.isSameItemSameComponents(item)) return false
                val components = MiningLaserComponents.itemToComponents[stack.item]
                return components.any { (slots) -> MiningLaserSlot.Crystal in slots }
            }

            override fun safeInsert(stack: ItemStack, increment: Int): ItemStack {
                return super.safeInsert(stack, 1)
            }
        }).set(MiningLaserDataManager.getCrystal(miningLaser))

        addSlot(object : Slot(generatorContainer, 0, 8, 20 + SLOT_SIZE * 2) {
            override fun mayPlace(stack: ItemStack): Boolean {
                if (stack.isSameItemSameComponents(item)) return false
                val components = MiningLaserComponents.itemToComponents[stack.item]
                return components.any { (slots) -> MiningLaserSlot.Generator in slots }
            }

            override fun safeInsert(stack: ItemStack, increment: Int): ItemStack {
                return super.safeInsert(stack, 1)
            }
        }).set(MiningLaserDataManager.getGenerator(miningLaser))

        val moduleStacks = MiningLaserDataManager.getModules(miningLaser)
        repeat(MODULE_SLOT_COUNT) { time ->
            addSlot(object : Slot(moduleContainer, time, 8 + SLOT_SIZE * time, 20 + SLOT_SIZE * 3) {
                override fun mayPlace(stack: ItemStack): Boolean {
                    if (stack.isSameItemSameComponents(item)) return false
                    val slotAmount = MiningLaserDataManager.getAttributes(miningLaser)
                        .getValue(MiningLaserAttributes.ModuleSlot).toInt()
                    if (time >= slotAmount) return false
                    val components = MiningLaserComponents.itemToComponents[stack.item]
                    return components.any { (slots) -> MiningLaserSlot.Module in slots }
                }

                override fun isActive(): Boolean {
                    val slotAmount = MiningLaserDataManager.getAttributes(miningLaser)
                        .getValue(MiningLaserAttributes.ModuleSlot).toInt()
                    return time < slotAmount
                }

                override fun safeInsert(stack: ItemStack, increment: Int): ItemStack {
                    return super.safeInsert(stack, 1)
                }
            }).set(moduleStacks.getOrNull(time) ?: ItemStack.EMPTY)
        }

        addSlot(object : Slot(fuelContainer, 0, 8 + SLOT_SIZE * 6, 20 + SLOT_SIZE * 3) {
            override fun mayPlace(stack: ItemStack): Boolean {
                return stack.item in MiningLaserDataManager.getFuels(miningLaser)
            }
        })

        for (y in 0..2) {
            for (x in 0..8) {
                this.addSlot(Slot(inventory, x + y * 9 + 9, 8 + x * 18, 84 + y * 18 + 12))
            }
        }

        for (x in 0..8) {
            this.addSlot(Slot(inventory, x, 8 + x * 18, 142 + 12))
        }

        initializing = false
    }

    override fun quickMoveStack(player: Player, quickMovedSlotIndex: Int): ItemStack {
        val slot = slots[quickMovedSlotIndex]
        if (!slot.hasItem()) return ItemStack.EMPTY
        val itemInSlot = slot.item
        val newItemStack = itemInSlot.copy()
        when (quickMovedSlotIndex) {
            TOOL_SLOT_INDEX,
            CRYSTAL_SLOT_INDEX,
            GENERATOR_SLOT_INDEX,
            in MODULE_SLOT_INDEX_START..<MODULE_SLOT_INDEX_END,
            FUEL_SLOT_INDEX -> {
                if (!moveItemStackTo(itemInSlot, INV_SLOT_START, HOTBAR_SLOT_END, true))
                    return ItemStack.EMPTY
                slot.onQuickCraft(itemInSlot, newItemStack)
            }

            in INV_SLOT_START..HOTBAR_SLOT_END -> {
                if (!moveItemStackTo(itemInSlot, TOOL_SLOT_INDEX, INV_SLOT_START, true))
                    return ItemStack.EMPTY
            }
        }
        if (itemInSlot.isEmpty) {
            slot.setByPlayer(ItemStack.EMPTY)
        } else {
            slot.setChanged()
        }

        if (itemInSlot.count == newItemStack.count) return ItemStack.EMPTY

        slot.onTake(player, itemInSlot)

        return newItemStack
    }

    override fun stillValid(player: Player): Boolean {
        return player.mainHandItem.`is`(CalypsosVoidHeartItems.MiningLaser)
    }

    override fun slotsChanged(container: Container) {
        if (initializing) return
        if (!inventory.getSelected().`is`(CalypsosVoidHeartItems.MiningLaser)) return

        val miningLaser = this.miningLaserContainer.getItem(0)
        val tool = this.toolContainer.getItem(0)
        val crystal = this.crystalContainer.getItem(0)
        val generator = this.generatorContainer.getItem(0)

        MiningLaserDataManager.setDelegateTool(miningLaser, tool)
        MiningLaserDataManager.setCrystal(miningLaser, crystal)
        MiningLaserDataManager.setGenerator(miningLaser, generator)
        MiningLaserDataManager.setModules(
            miningLaser,
            (moduleContainer as SimpleContainerAccessor).items
        )
        MiningLaserDataManager.applyComponents(miningLaser)

        super.slotsChanged(container)
    }
}