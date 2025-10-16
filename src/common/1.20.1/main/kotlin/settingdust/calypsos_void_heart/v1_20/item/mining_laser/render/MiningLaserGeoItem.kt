package settingdust.calypsos_void_heart.v1_20.item.mining_laser.render

import settingdust.calypsos_void_heart.mining_laser.render.MiningLaserGeoItem
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.util.GeckoLibUtil

abstract class MiningLaserGeoItem : MiningLaserGeoItem {
    private val animatableCache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)

    override fun registerControllers(registrar: AnimatableManager.ControllerRegistrar) {
    }

    override fun getAnimatableInstanceCache() = animatableCache
}