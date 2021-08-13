plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {

    compileSdk = Versions.compileSdkVersion
    buildToolsVersion = Versions.buildToolsVersion

    defaultConfig {
        minSdk = Versions.minSdkVersion
        targetSdk = Versions.targetSdkVersion
        applicationId = "ru.test.app.picsum"
        versionName = "1.0"
        versionCode = 2
        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true

        kapt {
            arguments {
                arg("room.incremental", "true")
                arg("room.schemaLocation", "$projectDir/schemas")
                arg("room.expandProjection", "true")
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

}

dependencies {

    implementation(Dependencies.kotlin)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.cardView)
    implementation(Dependencies.recyclerView)
    implementation(Dependencies.activityKtx)
    implementation(Dependencies.fragmentKtx)
    implementation(Dependencies.annotation)

    // Navigation
    implementation(Dependencies.Navigation.navigationFragmentKtx)
    implementation(Dependencies.Navigation.navigationUiKtx)

    // View
    implementation(Dependencies.glide)
    implementation(Dependencies.roundedImageView)

    // Coroutines
    implementation(Dependencies.Coroutines.core)
    implementation(Dependencies.Coroutines.android)

    // Androidx Lifecycle
    implementation(Dependencies.Lifecycle.lifecycleRuntime)
    implementation(Dependencies.Lifecycle.lifecycleViewModel)
    kapt(Dependencies.Lifecycle.lifecycleCompiler)

    // Network
    implementation(project(":core-network"))

    // DI
    implementation(Dependencies.Dagger.dagger)
    kapt(Dependencies.Dagger.daggerCompiler)

    // ViewBindingPropertyDelegates
    implementation(Dependencies.viewBindingPropertyDelegate)

    // Room
    implementation(Dependencies.Room.roomKtx)
    implementation(Dependencies.Room.roomRuntime)
    kapt(Dependencies.Room.roomCompiler)

    implementation(Dependencies.timber)
}