package settingdust.calypsos_void_heart.v1_20.util.minecraft

import net.minecraft.core.Holder
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeMap
import settingdust.calypsos_void_heart.util.minecraft.AttributeAdapter
import settingdust.calypsos_void_heart.v1_20.mixin.AttributeMapAccessor

class AttributeAdapter : AttributeAdapter {
    override fun AttributeMap.getValue(attribute: Holder<Attribute>) = getValue(attribute.value())

    override fun AttributeMap.clearModifiers() {
        for ((_, instance) in (this as AttributeMapAccessor).attributes) {
            instance.removeModifiers()
        }
    }
}