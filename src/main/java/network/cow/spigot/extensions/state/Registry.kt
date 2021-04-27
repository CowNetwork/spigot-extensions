package network.cow.spigot.extensions.state

import org.bukkit.plugin.java.JavaPlugin
import java.util.WeakHashMap

/**
 * @author Benedikt Wüller
 */
object Registry {

    private val states = WeakHashMap<Any, MutableMap<Class<out JavaPlugin>, MutableMap<String, Any>>>()

    private fun getStates(target: Any) = this.states.getOrPut(target) { mutableMapOf() }

    private fun getStates(target: Any, source: Class<out JavaPlugin>) = this.getStates(target).getOrPut(source) { mutableMapOf() }

    fun setState(target: Any, source: Class<out JavaPlugin>, key: String, value: Any) {
        this.getStates(target, source)[key] = value
    }

    fun <T : Any> getState(target: Any, source: Class<out JavaPlugin>, key: String) : T? = this.getStates(target, source)[key] as T?

    fun <T : Any> getState(target: Any, source: Class<out JavaPlugin>, key: String, defaultValue: T) : T = this.getState(target, source, key) ?: defaultValue

    fun clearState(target: Any, source: Class<out JavaPlugin>, key: String) {
        val sourceStates = this.getStates(target, source)
        sourceStates.remove(key)

        if (sourceStates.isEmpty()) {
            this.clearState(target, source)
        }
    }

    fun clearState(target: Any, source: Class<out JavaPlugin>) {
        val states = this.getStates(target)
        states.remove(source)

        if (states.isEmpty()) {
            this.states.remove(target)
        }
    }

}
