package settingdust.calypsos_void_heart.v1_20.item.mining_laser

import settingdust.calypsos_void_heart.mining_laser.MiningLaserItem
import software.bernie.geckolib.animatable.SingletonGeoAnimatable


class MiningLaserItem : MiningLaserItem() {
    init {
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }
}