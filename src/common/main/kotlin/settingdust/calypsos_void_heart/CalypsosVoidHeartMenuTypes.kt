package settingdust.calypsos_void_heart

import net.minecraft.client.gui.screens.MenuScreens
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.MenuType
import settingdust.calypsos_void_heart.mining_laser.gui.MiningLaserConfigureMenu
import settingdust.calypsos_void_heart.mining_laser.gui.MiningLaserConfigureScreen

object CalypsosVoidHeartMenuTypes {
    val MiningLaserConfigure by lazy { BuiltInRegistries.MENU.get(CalypsosVoidHeartKeys.MiningLaserConfigure) as MenuType<MiningLaserConfigureMenu> }

    fun register(register: (ResourceLocation, MenuType<out AbstractContainerMenu>, MenuScreens.ScreenConstructor<out AbstractContainerMenu, AbstractContainerScreen<out AbstractContainerMenu>>) -> Unit) {
        register(
            CalypsosVoidHeartKeys.MiningLaserConfigure,
            MenuType(::MiningLaserConfigureMenu, FeatureFlags.DEFAULT_FLAGS),
            { menu, inventory, component ->
                MiningLaserConfigureScreen(
                    menu as MiningLaserConfigureMenu,
                    inventory,
                    component
                )
            })
    }
}