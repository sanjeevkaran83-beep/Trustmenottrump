# Synex Injector — Android UI / Game Launcher

A real Android app starter project with a premium black-and-cyan neon interface. It includes Home, Game Launcher, Script Manager (demo only), and Settings screens.

## Safety and feature scope
- The launcher opens the official Free Fire or Free Fire MAX app if installed.
- Script Manager and import controls are placeholders; they do not execute or inject code.
- This project does not bypass game protections or modify Free Fire.

## Build an APK using GitHub Actions (phone-friendly)
1. Download and unzip this project.
2. Create a new repository on GitHub, for example `SynexInjectorAndroid`.
3. Upload all files and folders from this project to the repository root. Make sure `.github/workflows/build-apk.yml` is included.
4. Open the repository's **Actions** tab and select **Build Synex APK**.
5. Tap **Run workflow** (or push a commit to `main`) and wait for the run to finish.
6. Open the completed workflow run, scroll to **Artifacts**, and download `SynexInjector-debug-apk`.
7. Extract the ZIP and install `app-debug.apk` on your Android phone. Android may ask you to allow installs from your browser or file manager.

## Build locally
Install Android Studio and JDK 17, open this folder, sync Gradle, then run **Build > Build APK(s)**.

## Package details
- Application ID: `com.synex.injector`
- Minimum Android: Android 6.0 (API 23)
- Target SDK: 28 for broad package-launch visibility compatibility
