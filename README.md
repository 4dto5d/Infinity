# Infinity Rae

Infinity Rae is an Android APK project for a Human Design-flavored AI chat companion.

## Build the debug APK

1. Open the **Actions** tab.
2. Run **Build Android APK**, or push to `main`.
3. Open the workflow run.
4. Download the artifact named `infinity-rae-debug-apk`.
5. Unzip it and install `app-debug.apk` on Android.

## Signed release APK

Use **Build Signed Release APK** only after adding these repository secrets:

- `ANDROID_KEYSTORE_BASE64`
- `ANDROID_KEYSTORE_PASSWORD`
- `ANDROID_KEY_ALIAS`
- `ANDROID_KEY_PASSWORD`

The signed artifact is named `infinity-rae-signed-release-apk`.

## API key note

The app lets you enter an OpenAI API key locally in Settings. Do not publish a production key inside app source code. For a real public app, use a backend relay.
