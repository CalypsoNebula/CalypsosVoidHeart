package settingdust.calypsos_void_heart.v1_20.item.mining_laser.render

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.util.Mth
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import org.joml.Quaternionf
import settingdust.calypsos_void_heart.mining_laser.MiningLaserDataManager
import settingdust.calypsos_void_heart.mining_laser.MiningLaserItem
import settingdust.calypsos_void_heart.mining_laser.render.MiningLaserToolRenderLayer
import software.bernie.geckolib.cache.`object`.GeoBone
import software.bernie.geckolib.renderer.GeoItemRenderer
import software.bernie.geckolib.util.RenderUtils

class MiningLaserToolRenderLayer(private val renderer: GeoItemRenderer<MiningLaserItem>) :
    MiningLaserToolRenderLayer(renderer) {
    class Factory : MiningLaserToolRenderLayer.Factory {
        override fun create(renderer: GeoItemRenderer<MiningLaserItem>): MiningLaserToolRenderLayer =
            settingdust.calypsos_void_heart.v1_20.item.mining_laser.render.MiningLaserToolRenderLayer(renderer)
    }

    override fun getStackForBone(bone: GeoBone, item: MiningLaserItem): ItemStack? {
        return if (bone.name == "tool fastener") {
            MiningLaserDataManager.getDelegateTool(renderer.currentItemStack)
        } else {
            super.getStackForBone(bone, item)
        }
    }

    override fun getTransformTypeForStack(bone: GeoBone, stack: ItemStack, item: MiningLaserItem?) =
        ItemDisplayContext.NONE

    override fun renderForBone(
        poseStack: PoseStack,
        animatable: MiningLaserItem?,
        bone: GeoBone,
        renderType: RenderType,
        bufferSource: MultiBufferSource,
        buffer: VertexConsumer,
        partialTick: Float,
        packedLight: Int,
        packedOverlay: Int
    ) {
        if (animatable == null) return
        val stack = getStackForBone(bone, animatable)
        val blockState = getBlockForBone(bone, animatable)

        if (stack == null && blockState == null) return

        poseStack.pushPose()
        RenderUtils.translateAndRotateMatrixForBone(poseStack, bone)

        if (stack != null) {
            poseStack.translate(1f / 16, 2f / 16, -2f / 16)

            poseStack.mulPose(
                Quaternionf()
                    .rotateY(90 * Mth.DEG_TO_RAD)
                    .rotateZ(0 * Mth.DEG_TO_RAD)
            )

            renderStackForBone(
                poseStack,
                bone,
                stack,
                animatable,
                bufferSource,
                partialTick,
                packedLight,
                packedOverlay
            )
        }

        if (blockState != null) renderBlockForBone(
            poseStack,
            bone,
            blockState,
            animatable,
            bufferSource,
            partialTick,
            packedLight,
            packedOverlay
        )

        bufferSource.getBuffer(renderType)

        poseStack.popPose()
    }
}