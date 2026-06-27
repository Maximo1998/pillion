package app.pillion.core

/**
 * Persists user preferences across launches. The UI depends on this abstraction (DIP); platforms
 * provide it (Android: SharedPreferences). A null store simply means "use defaults" (e.g. previews).
 */
interface SettingsStore {
    fun themeMode(): ThemeMode
    fun setThemeMode(mode: ThemeMode)

    /** Whether the user has opted into "dedicated dash display" mode (completed onboarding). */
    fun dashEnabled(): Boolean
    fun setDashEnabled(enabled: Boolean)

    /** Virtual display resolution for the dedicated dash helper. */
    fun dashResolution(): DashResolution
    fun setDashResolution(resolution: DashResolution)

    /**
     * Logical width (dp) the dash display lays out as. Lower = larger, fewer on-screen elements,
     * which reads far better on the tiny panel. Drives the helper VirtualDisplay's density
     * *independently* of [dashResolution]: resolution is the sharpness/supersampling knob, this is
     * the content-size knob. iOS keeps the default (the dedicated dash is Android-only).
     */
    fun dashAnchorDp(): Int = DEFAULT_DASH_ANCHOR_DP
    fun setDashAnchorDp(dp: Int) {}

    companion object {
        // Logical width (dp) the dash lays out as. Higher = smaller/denser (≈ the old fixed-160-dpi
        // look at the high end), lower = larger UI. Calibrated so the usable size range is centered
        // around a readable default for the 480x240 panel.
        const val DEFAULT_DASH_ANCHOR_DP = 700
        const val MIN_DASH_ANCHOR_DP = 400
        const val MAX_DASH_ANCHOR_DP = 1100
    }
}
