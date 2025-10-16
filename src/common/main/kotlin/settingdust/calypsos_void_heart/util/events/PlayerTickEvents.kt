package settingdust.calypsos_void_heart.util.events

import net.minecraft.world.entity.player.Player
import settingdust.kinecraft.event.Event

object PlayerTickEvents {
    @JvmField
    val POST: Event<Post> = Event { listeners -> Post { player -> listeners.forEach { it.onPost(player) } } }

    fun interface Post {
        fun onPost(player: Player)
    }
}