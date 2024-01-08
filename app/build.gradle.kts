plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    // Add the Google services Gradle plugin
    // 目前這邊是給Firebase的自動發布部署20240108
    id("com.google.gms.google-services")
    // Add the App Distribution Gradle plugin
    id("com.google.firebase.appdistribution")
}

android {
    namespace = "com.weiyou.zoo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.weiyou.zoo"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

//            firebaseAppDistribution {
//                releaseNotes = "更新了 xxx 功能 "
//                testers = "a@example.com, b@example.com, c@example.com"
//            }
//
//            firebaseAppDistribution {
//                releaseNotes=" 更新了 xxx 功能 "
//                groups="qa-team"
//            }
        }

        debug {
            firebaseAppDistribution {
                appId = "1:748942500139:android:06482e92797823806c656e"
                releaseNotes = "測試appdistribution "
                testers = "wow800915@gmail.com"
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
//navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")
//BottonNavigation及BottomNavigation
    implementation("androidx.compose.material:material:1.3.1")
// Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
// Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
// Log interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
// LiveData
    implementation("androidx.compose.runtime:runtime-livedata:1.4.0-beta01")
//  Was thinking about using glide ,but it is still in beta
//    implementation("com.github.bumptech.glide:compose:1.0.0-beta01")
    implementation("io.coil-kt:coil-compose:2.5.0")
//  UnitTest
    testImplementation("org.mockito:mockito-inline:5.2.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")

    testImplementation("androidx.arch.core:core-testing:2.1.0")

// 目前這邊是給Firebase的自動發布部署20240108
    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    // TODO: Add the dependencies for Firebase products you want to use
    // When using the BoM, don't specify versions in Firebase dependencies
    implementation("com.google.firebase:firebase-analytics")
}