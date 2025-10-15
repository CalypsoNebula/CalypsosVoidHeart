package settingdust.calypsos_void_heart.util.minecraft

import net.minecraft.core.Holder
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeMap
import settingdust.calypsos_void_heart.CalypsosVoidHeart
import settingdust.kinecraft.util.ServiceLoaderUtil

interface AttributeAdapter {
    companion object : AttributeAdapter by ServiceLoaderUtil.findService(logger = CalypsosVoidHeart.LOGGER)

    fun AttributeMap.getValue(attribute: Holder<Attribute>): Double

    fun AttributeMap.clearModifiers()
}