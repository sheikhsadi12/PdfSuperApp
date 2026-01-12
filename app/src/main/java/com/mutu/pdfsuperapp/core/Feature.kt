package com.pdfsuperapp.core

import android.content.Context

/**
 * Interface representing a pluggable unit of functionality within the PDF Super App.
 */
interface Feature {
    val id: String
    val name: String
    val description: String
    val license: License
    val enabledByDefault: Boolean

    /**
     * Called when the feature is first added to the system.
     * Used for database migrations, resource allocation, or initial setup.
     */
    fun onInstall(appContext: AppContext)

    /**
     * Called when the feature is toggled to an active state.
     * Used to register listeners, start background processes, or inject UI components.
     */
    fun onEnable()

    /**
     * Called when the feature is toggled to an inactive state.
     * Must release non-persistent resources and unregister listeners.
     */
    fun onDisable()

    /**
     * Called when the feature is being removed from the system.
     * Used to purge local data and perform final cleanup.
     */
    fun onUninstall()
}

/**
 * Abstraction of the application environment provided during feature installation.
 */
interface AppContext {
    val androidContext: Context
}

/**
 * Definition of the licensing tiers required to unlock specific features.
 */
sealed interface License {
    object Free : License
    object Premium : License
    data class Subscription(val sku: String) : License
}
