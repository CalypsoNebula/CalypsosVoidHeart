package settingdust.calypsos_void_heart.mining_laser.render

import settingdust.calypsos_void_heart.mining_laser.MiningLaserItem
import software.bernie.geckolib.renderer.GeoItemRenderer
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer

abstract class MiningLaserToolRenderLayer(renderer: GeoItemRenderer<MiningLaserItem>) : BlockAndItemGeoLayer<MiningLaserItem>(renderer) {
    interface Factory {
        fun create(renderer: GeoItemRenderer<MiningLaserItem>): MiningLaserToolRenderLayer
    }
}