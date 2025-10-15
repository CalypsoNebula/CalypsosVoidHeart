package settingdust.calypsos_void_heart.mining_laser.gui

import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.player.Inventory

class MiningLaserConfigureScreen(menu: MiningLaserConfigureMenu, inventory: Inventory, title: Component) :
    AbstractContainerScreen<MiningLaserConfigureMenu>(menu, inventory, title) {
    override fun renderBg(context: GuiGraphics, partialTick: Float, mouseX: Int, mouseY: Int) {
        TODO("Not yet implemented")
    }
}