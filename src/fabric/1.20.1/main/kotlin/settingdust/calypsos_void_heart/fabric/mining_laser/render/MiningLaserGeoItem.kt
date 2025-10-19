package settingdust.calypsos_void_heart.fabric.mining_laser.render

import settingdust.calypsos_void_heart.CalypsosVoidHeart
import settingdust.calypsos_void_heart.mining_laser.render.MiningLaserRenderer
import settingdust.calypsos_void_heart.v1_20.item.mining_laser.render.MiningLaserGeoItem
import settingdust.kinecraft.util.ServiceLoaderUtil
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.animatable.client.RenderProvider
import java.util.function.Consumer
import java.util.function.Supplier


class MiningLaserGeoItem : MiningLaserGeoItem() {
    private val renderProvider: Supplier<Any> = GeoItem.makeRenderer(this)

    override fun createRenderer(consumer: Consumer<in Any>) {
        consumer.accept(object : RenderProvider {
            private val renderer by lazy { ServiceLoaderUtil.findService<MiningLaserRenderer>(logger = CalypsosVoidHeart.LOGGER) }

            override fun getCustomRenderer() = renderer
        })
    }

    override fun getRenderProvider() = renderProvider
}