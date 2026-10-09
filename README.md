# NOVA Home Revamp

Updated first-stage Android APK project for the NOVA Home concept. It keeps the NOVA name while using a new repository/project name, **NOVA Home Revamp**.

## Included
- Futuristic dark-blue dashboard inspired by the supplied NOVA mockup.
- One Hey Nova orb at the bottom-left only (removed the other two microphone/orb buttons).
- Simplified scrolling layout, weather/news/photo cards, in-app browser controls and streaming-service shortcuts with recognizable logo-like marks.
- Floating rounded-square music widget.
- Android WebView crash fallback screen and renderer-crash recovery attempt.
- GitHub Actions workflow to build a debug APK.

## Build
Upload the extracted project contents to the root of a new GitHub repository, including the hidden `.github` folder. Open Actions, select **Build NOVA Home Revamp APK**, and run the workflow. Download artifact `NOVA-Home-Revamp-debug-APK`, extract it, and install `app-debug.apk`.

## Important honesty about functionality
This is a UI and Android-shell milestone. The browser uses the Android System WebView Chromium engine, not a separate full Chrome binary. Some sites prevent embedding, and streaming services may require their official app. The music widget is a visual prototype and is not yet connected to other apps' MediaSession controls. The local AI model, always-on wake word, real live news/weather, Google Photos OAuth, notification reading, email integrations, and phone-call handoff are not yet wired up. The interface retains entry points for future integrations, but they should not be treated as already working.

The prior crash cannot be diagnosed with certainty without logs. This rebuild removes the earlier iframe-based browsing approach and adds a native fallback if WebView fails, but this is a best-effort fix, not a guarantee. Android WebView can fail for system-component or device-specific reasons too.
