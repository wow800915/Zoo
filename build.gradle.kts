// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    // Add the dependency for the Google services Gradle plugin
    // 目前這邊是給Firebase的自動發布部署20240108
    id("com.google.gms.google-services") version "4.4.0" apply false
    // Add the dependency for the App Distribution Gradle plugin
    id("com.google.firebase.appdistribution") version "4.0.0" apply false
}