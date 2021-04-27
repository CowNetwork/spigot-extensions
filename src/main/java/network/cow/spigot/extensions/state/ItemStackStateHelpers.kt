package network.cow.spigot.extensions.state

import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Benedikt Wüller
 */

fun ItemStack.setState(source: Class<out JavaPlugin>, key: String, value: Any) = Registry.setState(this, source, key, value)

fun ItemStack.clearState(source: Class<out JavaPlugin>, key: String) = Registry.clearState(this, source, key)
fun ItemStack.setState(source: Class<out JavaPlugin>) = Registry.clearState(this, source)

fun <T : Any> ItemStack.getState(source: Class<out JavaPlugin>, key: String) = Registry.getState<T>(this, source, key)
fun <T : Any> ItemStack.getState(source: Class<out JavaPlugin>, key: String, default: T) = Registry.getState(this, source, key, default)
