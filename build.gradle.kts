buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = uri("https://kotlin.bintray.com/kotlinx/"))
        maven(url = uri("https://jitpack.io"))
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
