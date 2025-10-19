package settingdust.calypsos_void_heart.mining_laser.render

import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.resources.TextureAtlasHolder
import net.minecraft.resources.ResourceLocation
import settingdust.calypsos_void_heart.CalypsosVoidHeart

object LaserSprites : TextureAtlasHolder(
    Minecraft.getInstance().textureManager,
    CalypsosVoidHeart.id("textures/atlas/laser.png"),
    CalypsosVoidHeart.id("laser")
) {
    public override fun getSprite(location: ResourceLocation): TextureAtlasSprite {
        return super.getSprite(location)
    }
}