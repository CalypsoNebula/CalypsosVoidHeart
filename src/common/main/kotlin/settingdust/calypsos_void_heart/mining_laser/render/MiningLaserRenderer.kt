package settingdust.calypsos_void_heart.mining_laser.render

import settingdust.calypsos_void_heart.CalypsosVoidHeart
import settingdust.calypsos_void_heart.CalypsosVoidHeartKeys
import settingdust.calypsos_void_heart.mining_laser.MiningLaserItem
import settingdust.kinecraft.util.ServiceLoaderUtil
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoItemRenderer

abstract class MiningLaserRenderer :
    GeoItemRenderer<MiningLaserItem>(DefaultedItemGeoModel(CalypsosVoidHeartKeys.MiningLaser)) {
    init {

        addRenderLayer(
            ServiceLoaderUtil
                .findService<MiningLaserToolRenderLayer.Factory>(logger = CalypsosVoidHeart.LOGGER)
                .create(this)
        )
    }
}