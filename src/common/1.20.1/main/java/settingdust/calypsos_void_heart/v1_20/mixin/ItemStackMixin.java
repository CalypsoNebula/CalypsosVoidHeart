package settingdust.calypsos_void_heart.v1_20.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import settingdust.calypsos_void_heart.CalypsosVoidHeartItems;
import settingdust.calypsos_void_heart.mining_laser.data.MiningLaserAttributes;
import settingdust.calypsos_void_heart.mining_laser.item.MiningLaserBehaviour;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract Item getItem();

    @Shadow
    public abstract boolean is(Item item);

    @WrapMethod(method = "getMaxDamage")
    private int calypsos_void_heart$handleItem(Operation<Integer> original) {
        if (is(CalypsosVoidHeartItems.INSTANCE.getMINING_LASER())) {
            return (int) MiningLaserBehaviour.Companion.getAttributes((ItemStack) (Object) this)
                    .getValue(MiningLaserAttributes.Energy);
        }
        return original.call();
    }

    @WrapMethod(method = "isCorrectToolForDrops")
    private boolean calypsos_void_heart$handleItem(BlockState state, Operation<Boolean> original) {
        if (is(CalypsosVoidHeartItems.INSTANCE.getMINING_LASER())) {
            return CalypsosVoidHeartItems.INSTANCE.getMINING_LASER()
                    .isCorrectToolForDrops((ItemStack) (Object) this, state);
        }
        return original.call(state);
    }
}
