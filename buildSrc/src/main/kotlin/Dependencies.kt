object Versions {

    const val compileSdkVersion = 31
    const val buildToolsVersion = "30.0.3"
    const val minSdkVersion = 21
    const val targetSdkVersion = 31

    const val appCompat = "1.3.0"
    const val constraintLayout = "2.0.4"
    const val recyclerView = "1.2.1"
    const val material = "1.4.0"
    const val cardView = "1.0.0"
    const val coreKtx = "1.6.0-beta01"
    const val activityKtx = "1.3.0-rc01"
    const val fragmentKtx = "1.4.0-alpha04"
    const val dagger = "2.37"
    const val coroutines = "1.5.0"
    const val lifecycle = "2.4.0-alpha02"
    const val kotlin = "1.5.21"
    const val glide = "4.12.0"
    const val okhttp = "5.0.0-alpha.2"
    const val retrofit = "2.9.0"
    const val retrofitConverterGson = "2.9.0"
    const val viewBindingPropertyDelegate = "1.4.6"
    const val roundedImageView = "2.3.0"
    const val timber = "4.7.1"
    const val leakcanary = "2.4"
    const val room = "2.3.0"
    const val navigation = "2.3.5"
    const val annotation = "1.2.0"
}

object Dependencies {

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
    const val annotation = "androidx.annotation:annotation:${Versions.annotation}"
    const val viewBindingPropertyDelegate = "com.github.kirich1409:viewbindingpropertydelegate-noreflection:${Versions.viewBindingPropertyDelegate}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val roundedImageView = "com.makeramen:roundedimageview:${Versions.roundedImageView}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanary}"

    object Navigation {
        const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    }

    object Dagger {
        const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object Lifecycle {
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val lifecycleCommonJava = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
        const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    }

    object OkHttp {
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitConverterGson}"
       }

    object Room {
        const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    }

}