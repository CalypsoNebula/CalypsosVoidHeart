package settingdust.calypsos_void_heart.v1_20.mixin;

import java.util.Map;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AttributeMap.class)
public interface AttributeMapAccessor {
    @Accessor
    Map<Attribute, AttributeInstance> getAttributes();
}
