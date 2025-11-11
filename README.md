# EyeSense - Settings Module

This module is part of the Android application. It implements the Settings screen, which centralizes user preferences, application information, and useful resources for both users and developers. The feature has been designed following the principles of Material Design and Android best practices to provide a consistent and intuitive experience.

## Overview

The Settings screen allows users to configure different aspects of the application. It includes sections for notifications, developers, external resources, project links, contact options, and version information. The structure is modular, scalable, and designed to integrate easily with future backend services.

## Technical Description

The main entry point is `MainActivity.kt`, which hosts the `MySettingsFragment` through a `FrameLayout` container defined in `activity_main.xml`. The fragment extends `PreferenceFragmentCompat` and loads the preferences from `res/xml/preferences.xml`.

The logic is divided into multiple setup functions that handle specific preferences. Notifications, developer information, external resources, project links, contact email, sharing options, and privacy policy are each configured through dedicated setup methods.

The screen uses `MaterialAlertDialogBuilder` to display modal dialogs with dynamic content. The data models `Developers.kt` and `ExternalResource.kt` define the structure for developer and resource data respectively. Both are currently fetched through simulated API calls using `lifecycleScope` and Kotlin coroutines, which can later be replaced by real network requests.

To ensure a good user experience, error handling and input validation are implemented for all actions, including opening external links, sending emails, and sharing app content. Toast messages and log statements are used to simulate and monitor user interactions.

## Architecture

The module follows a clean and maintainable structure organized by features:

- `presentation`: contains UI-related classes such as `MainActivity`, `MySettingsFragment`, and data models.
- `res/xml`: defines the structure and behavior of the preferences.
- `res/layout`: contains the activity layout file hosting the fragment container.

Although the current implementation is based on local logic, it is prepared for future integration with a backend through repositories or use cases. This separation allows developers to scale the feature without affecting the presentation layer.

## Future Improvements

Planned improvements include replacing mock data with real API responses, aligning XML preference keys with Kotlin code, and adding UI tests for stability. Additional enhancements may include a dark mode setting, dynamic language selection, and user analytics for preference usage.

## Dependencies

The module relies on **AndroidX Preferences**, **Material Design Components**, and **Kotlin Coroutines**.  
All dependencies are declared in the app-level `build.gradle` file and follow Android’s latest compatibility versions.

## Screenshot

<img width="338" height="655" alt="settings" src="https://github.com/user-attachments/assets/c674b4bf-e6d4-42cd-97ab-16b0205d1993" />

