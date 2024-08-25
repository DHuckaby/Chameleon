package dev.huckaby.camelion.ui.theme

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager

enum class ThemeVariant(val componentName: String) {
    DEFAULT(".LauncherDefault"),
    DARK(".LauncherDark")
}

private enum class ComponentState(val flag: Int) {
    DEFAULT(PackageManager.COMPONENT_ENABLED_STATE_DEFAULT),
    DISABLED(PackageManager.COMPONENT_ENABLED_STATE_DISABLED),
    ENABLED(PackageManager.COMPONENT_ENABLED_STATE_ENABLED),
    UNKNOWN(-1)
}

fun Context.getThemeVariant(): ThemeVariant {
    ThemeVariant.entries.forEach { variant ->
        if (variant.getState(this) == ComponentState.ENABLED) {
            return variant
        }
    }
    return ThemeVariant.DEFAULT
}

fun Context.setThemeVariant(targetVariant: ThemeVariant) {
    ThemeVariant.entries.forEach { variant ->
        if (targetVariant == variant) {
            if (variant == ThemeVariant.DEFAULT) {
                variant.setState(this, ComponentState.DEFAULT)
            } else {
                variant.setState(this, ComponentState.ENABLED)
            }
        } else {
            if (variant == ThemeVariant.DEFAULT) {
                variant.setState(this, ComponentState.DISABLED)
            } else {
                variant.setState(this, ComponentState.DEFAULT)
            }
        }
    }
}

private fun ThemeVariant.setState(context: Context, state: ComponentState) {
    val componentName = toComponentName(context)
    context.packageManager.setComponentEnabledSetting(
        componentName,
        state.flag,
        PackageManager.DONT_KILL_APP
    )
}

private fun ThemeVariant.getState(context: Context): ComponentState {
    val componentName = toComponentName(context)
    val rawState = context.packageManager.getComponentEnabledSetting(componentName)
    ComponentState.entries.forEach { state ->
        if (state.flag == rawState) {
            return state
        }
    }
    return ComponentState.UNKNOWN
}

private fun ThemeVariant.toComponentName(context: Context): ComponentName {
    return ComponentName(context, "${context.packageName}$componentName")
}
