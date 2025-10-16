package settingdust.calypsos_void_heart.mining_laser.render

import settingdust.calypsos_void_heart.CalypsosVoidHeart
import settingdust.calypsos_void_heart.CalypsosVoidHeartKeys
import settingdust.calypsos_void_heart.mining_laser.MiningLaserItem
import software.bernie.geckolib.model.GeoModel

abstract class MiningLaserModel : GeoModel<MiningLaserItem>() {
    companion object {
        @JvmStatic
        protected val model = CalypsosVoidHeart.id("geo/item/${CalypsosVoidHeartKeys.MiningLaser.path}.geo.json")
        @JvmStatic
        protected val texture = CalypsosVoidHeart.id("textures/item/${CalypsosVoidHeartKeys.MiningLaser.path}.png")
        @JvmStatic
        protected val animation = CalypsosVoidHeart.id("animations/item/${CalypsosVoidHeartKeys.MiningLaser.path}.animation.json")
    }
}