# 📱 MAD Assignments — Siddharth Ranka

A collection of **4 Android applications** built as part of the Mobile Application Development (MAD) course assignments. Each project demonstrates a distinct Android API domain — currency conversion, media playback, sensor interaction, and media file management.

---

## 📚 Table of Contents

- [Overview](#overview)
- [Q1 — Currency Converter](#q1--currency-converter)
- [Q2 — Media Player](#q2--media-player)
- [Q3 — Sensor Monitor](#q3--sensor-monitor)
- [Q4 — MediaVault (Photo Gallery)](#q4--mediavault-photo-gallery)
- [Tech Stack](#tech-stack)
- [Build & Run](#build--run)
- [Author](#author)

---

## Overview

| #  | Project Folder | App Name | Core Topic |
|----|----------------|----------|------------|
| Q1 | `Q1/Q1_CurrencyConverter_SIDDHARTH` | Currency Converter | UI, SharedPreferences, Theme Switching |
| Q2 | `Q2/Q2_MediaPlayer_Siddharth` | Media Player | MediaPlayer API, VideoView, Live Streaming |
| Q3 | `Q3/Q3_SENSOR_SIDDHARTH` | Sensor Monitor | SensorManager, Hardware Sensors |
| Q4 | `Q4/Q4_MediaVault_SIDDHARTH` | MediaVault | Camera, FileProvider, Scoped Storage, RecyclerView |

All four projects share:

- **Language:** Java
- **Min SDK:** 24 (Android 7.0 Nougat)
- **Target SDK:** 36
- **Build System:** Gradle (Kotlin DSL — `.kts`)
- **IDE:** Android Studio

---

## Q1 — Currency Converter

### 📂 Path
```
Q1/Q1_CurrencyConverter_SIDDHARTH/
```

### 📝 Description
A simple, offline currency converter that supports four major currencies — **INR, USD, EUR, and JPY** — using static exchange rates. It also features a **Dark/Light theme toggle** persisted via `SharedPreferences`.

### ✨ Features
- Convert between INR, USD, JPY, and EUR using hardcoded rates (as of April 2026)
- **Swap button** to instantly reverse the source and target currencies
- **Settings screen** (`SettingsActivity`) to switch between Dark Mode and Light Mode
- Theme preference is saved across app restarts using `SharedPreferences`

### 🗂️ Key Classes

| Class | Description |
|-------|-------------|
| `MainActivity` | Handles conversion logic, currency selection via Spinners, and swap functionality |
| `SettingsActivity` | Provides a RadioGroup UI to toggle and persist the app theme |

### 🏗️ Screens
- **Main Screen:** Amount input field, two Spinners (From/To currencies), Convert button, Swap button, Result display
- **Settings Screen:** Dark Mode / Light Mode radio buttons

### 📦 Dependencies
- `appcompat`, `material`, `constraintlayout`, `cardview`

---

## Q2 — Media Player

### 📂 Path
```
Q2/Q2_MediaPlayer_Siddharth/
```

### 📝 Description
A dual-mode media player that can play **audio files from local disk** and **stream live video from a URL**. It uses Android's built-in `MediaPlayer` and `VideoView` APIs.

### ✨ Features
- **Open Audio File:** Launches a system file picker to select an audio file from device storage and plays it via `MediaPlayer`
- **Stream Video from URL:** Loads and plays a remote MP4 stream using `VideoView` with live buffering status feedback
- **Playback Controls:** Play, Pause, Stop, and Restart buttons for both audio and video
- **Adaptive UI:** Hides/shows Album Art and VideoView depending on current playback mode
- **Error Handling:** Gracefully handles network errors during video streaming

### 🗂️ Key Classes

| Class | Description |
|-------|-------------|
| `MainActivity` | All media logic — file picker, streaming, and playback control |

### 🔊 Supported Modes

| Mode | API Used | Source |
|------|----------|--------|
| Audio Playback | `MediaPlayer` | Local File (Storage) |
| Video Streaming | `VideoView` + `MediaPlayer.OnInfoListener` | Remote URL (HTTP MP4) |

### 📦 Dependencies
- `appcompat`, `material`, `constraintlayout`

---

## Q3 — Sensor Monitor

### 📂 Path
```
Q3/Q3_SENSOR_SIDDHARTH/
```

### 📝 Description
A real-time **hardware sensor dashboard** that monitors three device sensors simultaneously and displays live readings on screen.

### ✨ Features
- **Accelerometer:** Displays real-time X, Y, Z axis values (in m/s²)
- **Light Sensor:** Shows ambient light level in lux (lx)
- **Proximity Sensor:** Shows `NEAR 🛑` (red) or `FAR ✅` (green) based on object distance; gracefully handles devices without a proximity sensor (`Hardware Missing ❌`)
- Sensor listeners registered/unregistered properly on `onResume`/`onPause` to save battery

### 🗂️ Key Classes

| Class | Description |
|-------|-------------|
| `MainActivity` | Implements `SensorEventListener`; registers all 3 sensors and updates TextViews on change |

### 📡 Sensors Used

| Sensor | Android Constant | Output |
|--------|-----------------|--------|
| Accelerometer | `TYPE_ACCELEROMETER` | X / Y / Z in m/s² |
| Light | `TYPE_LIGHT` | Value in lux (lx) |
| Proximity | `TYPE_PROXIMITY` | NEAR / FAR with color coding |

### 📦 Dependencies
- `appcompat`, `material`, `constraintlayout`

---

## Q4 — MediaVault (Photo Gallery)

### 📂 Path
```
Q4/Q4_MediaVault_SIDDHARTH/
```

### 📝 Description
A full-featured **photo vault / gallery app** that lets users capture photos with the camera and browse images from any folder on the device. It leverages **Scoped Storage**, **FileProvider**, and the `DocumentFile` API for Android 10+ compatibility.

### ✨ Features
- **Capture Photo:** User selects a destination folder first, then the camera is launched; the captured photo is automatically saved to the chosen folder
- **Browse Folder:** Opens an `ACTION_OPEN_DOCUMENT_TREE` folder picker and loads all images in a 3-column RecyclerView grid
- **Image Detail View:** Tap any image thumbnail to open a detail screen showing image name, URI, size (KB), and last modified date
- **Delete Image:** Confirmation dialog + scoped-storage-safe deletion via `DocumentFile.delete()`
- **Persistent URI Permissions:** Folder access is retained across sessions using `takePersistableUriPermission`

### 🗂️ Key Classes

| Class | Description |
|-------|-------------|
| `MainActivity` | Camera flow, folder picker, image loading, RecyclerView setup |
| `ImageAdapter` | RecyclerView adapter for the image grid; handles tap→Detail navigation |
| `DetailActivity` | Full-screen image view with metadata display and delete functionality |

### 🔐 Permissions

| Permission | Purpose |
|-----------|---------|
| `CAMERA` | Capture photos |
| `READ_EXTERNAL_STORAGE` | Legacy storage access (API ≤ 32) |
| `READ_MEDIA_IMAGES` | Media access on API 33+ |
| `WRITE_EXTERNAL_STORAGE` | Write access for API ≤ 28 |

### 📦 Dependencies
- `appcompat`, `material`, `constraintlayout`
- `androidx.documentfile:documentfile:1.0.1`

---

## Tech Stack

| Technology | Details |
|------------|---------|
| **Language** | Java |
| **Platform** | Android (Jetpack + AndroidX) |
| **Min SDK** | API 24 (Android 7.0) |
| **Target SDK** | API 36 |
| **Build System** | Gradle Kotlin DSL (`.kts`) |
| **UI Toolkit** | XML Layouts, ConstraintLayout, Material Design |
| **Storage** | SharedPreferences (Q1), Scoped Storage + DocumentFile (Q4) |
| **Media** | MediaPlayer, VideoView (Q2) |
| **Sensors** | SensorManager (Q3) |
| **Camera** | CameraIntent + FileProvider (Q4) |

---

## Build & Run

### Prerequisites
- Android Studio **Meerkat** or later
- Android SDK Platform **36** installed
- A physical device or emulator running **Android 7.0+**

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/siddharthranka18/MAD-ASSIGNMENTS_SIDDHARTH_RANKA.git
   cd MAD-ASSIGNMENTS_SIDDHARTH_RANKA
   ```

2. **Open a project in Android Studio**
   - Navigate to the relevant sub-folder (e.g., `Q1/Q1_CurrencyConverter_SIDDHARTH`)
   - Click *Open* in Android Studio and select that folder

3. **Sync Gradle**
   - Android Studio will prompt you to sync; click **Sync Now**

4. **Run the app**
   - Connect a device or start an emulator
   - Click the **Run ▶** button

> **Note:** Each question is an independent Android Studio project and must be opened separately.

### Quick Reference

| Project | Open Folder |
|---------|-------------|
| Q1 — Currency Converter | `Q1/Q1_CurrencyConverter_SIDDHARTH/` |
| Q2 — Media Player | `Q2/Q2_MediaPlayer_Siddharth/` |
| Q3 — Sensor Monitor | `Q3/Q3_SENSOR_SIDDHARTH/` |
| Q4 — MediaVault | `Q4/Q4_MediaVault_SIDDHARTH/` |

---

## Author

**Siddharth Ranka**
Mobile Application Development — Assignment Submission
