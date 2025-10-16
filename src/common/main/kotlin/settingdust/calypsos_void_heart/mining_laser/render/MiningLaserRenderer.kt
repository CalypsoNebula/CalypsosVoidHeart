package settingdust.calypsos_void_heart.mining_laser.render

import settingdust.calypsos_void_heart.CalypsosVoidHeartKeys
import settingdust.calypsos_void_heart.mining_laser.MiningLaserItem
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoItemRenderer

class MiningLaserRenderer :
    GeoItemRenderer<MiningLaserItem>(DefaultedItemGeoModel(CalypsosVoidHeartKeys.MiningLaser)) {

}