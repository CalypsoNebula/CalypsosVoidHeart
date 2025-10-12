package settingdust.calypsos_void_heart.mining_laser.gui

import net.minecraft.world.Container
import net.minecraft.world.SimpleContainer
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.MenuType
import net.minecraft.world.item.ItemStack
import settingdust.calypsos_void_heart.CalypsosVoidHeartItems

class MiningLaserConfigureMenu(
    containerId: Int,
    inventory: Inventory
) : AbstractContainerMenu(TYPE, containerId) {
    companion object {
        val TYPE = MenuType<MiningLaserConfigureMenu>(
            ::MiningLaserConfigureMenu,
            FeatureFlags.DEFAULT_FLAGS
        )
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

    val componentContainer = object : SimpleContainer(6) {
        override fun setChanged() {
            super.setChanged()
            slotsChanged(this)
        }
    }

    override fun quickMoveStack(player: Player, quickMovedSlotIndex: Int): ItemStack {
        TODO("Not yet implemented")
    }

    override fun stillValid(player: Player): Boolean {
        return player.mainHandItem.`is`(CalypsosVoidHeartItems.MINING_LASER)
    }

    override fun slotsChanged(container: Container) {
        super.slotsChanged(container)
        val miningLaser = this.miningLaserContainer.getItem(0)
        val tool = this.toolContainer.getItem(0)
        val crystal = this.crystalContainer.getItem(0)
        val generator = this.generatorContainer.getItem(0)
        val components = this.componentContainer
    }
}