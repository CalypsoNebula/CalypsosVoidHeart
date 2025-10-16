package settingdust.calypsos_void_heart.util.events

import net.minecraft.core.RegistryAccess
import net.minecraft.server.ReloadableServerResources
import net.minecraft.server.packs.resources.PreparableReloadListener
import settingdust.kinecraft.event.Event

object AddServerResourcesListenerCallback {
    @JvmField
    val CALLBACK: Event<Callback> = Event { eventListeners ->
        Callback { resources, registry ->
            eventListeners.flatMap { listener ->
                listener.callback(resources, registry)
            }
        }
    }

    fun interface Callback {
        fun callback(
            resources: ReloadableServerResources,
            registry: RegistryAccess
        ) : List<PreparableReloadListener>
    }
}