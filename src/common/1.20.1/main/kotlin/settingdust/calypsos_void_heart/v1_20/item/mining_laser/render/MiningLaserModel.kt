package settingdust.calypsos_void_heart.v1_20.item.mining_laser.render

import settingdust.calypsos_void_heart.mining_laser.MiningLaserItem
import settingdust.calypsos_void_heart.mining_laser.render.MiningLaserModel

class MiningLaserModel : MiningLaserModel() {
    override fun getModelResource(item: MiningLaserItem) = model

    override fun getTextureResource(item: MiningLaserItem) = texture

    override fun getAnimationResource(item: MiningLaserItem) = animation
}