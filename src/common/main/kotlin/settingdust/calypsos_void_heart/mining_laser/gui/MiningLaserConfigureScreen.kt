package settingdust.calypsos_void_heart.mining_laser.gui

import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.player.Inventory

class MiningLaserConfigureScreen(menu: MiningLaserConfigureMenu, inventory: Inventory, title: Component) :
    AbstractContainerScreen<MiningLaserConfigureMenu>(menu, inventory, title) {

    init {
        imageHeight = 186
    }

    override fun render(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        super.render(guiGraphics, mouseX, mouseY, partialTick)
        this.renderTooltip(guiGraphics, mouseX, mouseY)
    }

    override fun renderBg(context: GuiGraphics, partialTick: Float, mouseX: Int, mouseY: Int) {
    }
}