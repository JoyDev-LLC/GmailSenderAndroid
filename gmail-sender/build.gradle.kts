plugins {
    id(Dependencies.Plugins.library)
    id(Dependencies.Plugins.kotlin)
    id(Dependencies.Plugins.parcelize)
}

android {
    namespace = "ru.joydev.ui_kit_gmail_sender"
    compileSdk = Config.Sdk.compile

    defaultConfig {
        minSdk = Config.Sdk.min
        targetSdk = Config.Sdk.target

        // Тесты
        testInstrumentationRunner = Config.Test.instrumentationRunner
    }

    buildTypes {
        release {
            isMinifyEnabled = Config.Release.minifyEnabled
            proguardFiles(
                getDefaultProguardFile(Config.Release.Proguard.name),
                Config.Release.Proguard.rules
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Config.Kotlin.Options.jvmTarget
    }
}

dependencies {
    //Gmail
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}