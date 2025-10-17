package settingdust.calypsos_void_heart.v1_20.mixin.mining_laser;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import settingdust.calypsos_void_heart.CalypsosVoidHeartItems;

@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin {
    @Shadow private ItemStack mainHandItem;

    @Shadow @Final private Minecraft minecraft;

    @Definition(id = "mainHandItem", field = "Lnet/minecraft/client/renderer/ItemInHandRenderer;mainHandItem:Lnet/minecraft/world/item/ItemStack;")
    @Definition(id = "matches", method = "Lnet/minecraft/world/item/ItemStack;matches(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)Z")
    @Expression("matches(this.mainHandItem, ?)")
    @ModifyExpressionValue(method = "tick", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean calypsos_void_heart$cancelAnimation(
            boolean original,
            @Share("sameLaser") LocalBooleanRef sameLaser) {
        ItemStack newMainStack = this.minecraft.player.getMainHandItem();
        if (!newMainStack.is(mainHandItem.getItem())
                || !mainHandItem.is(CalypsosVoidHeartItems.INSTANCE.getMiningLaser())) {
            return original;
        }
        if (original) {
            sameLaser.set(true);
            return original;
        }
        var oldMainStackTag = mainHandItem.getTag();
        var newMainStackTag = newMainStack.getTag();
        if (oldMainStackTag == null || newMainStackTag == null) {
            return original;
        }
        oldMainStackTag = oldMainStackTag.copy();
        newMainStackTag = newMainStackTag.copy();
        oldMainStackTag.remove("Damage");
        newMainStackTag.remove("Damage");
        sameLaser.set(oldMainStackTag.equals(newMainStackTag));
        return sameLaser.get();
    }

    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getAttackStrengthScale(F)F"))
    private float calypsos_void_heart$cancelAttackStrengthScale(
            LocalPlayer instance,
            float f,
            Operation<Float> original,
            @Share("sameLaser") LocalBooleanRef sameLaser) {
        if (sameLaser.get()) {
            return 1.0F;
        }
        return original.call(instance, f);
    }
}
