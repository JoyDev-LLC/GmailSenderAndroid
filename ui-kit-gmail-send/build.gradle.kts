plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    namespace = "ru.joydev.ui_kit_gmail_send"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        // Тесты
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    publishing {
        multipleVariants {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    //Gmail
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("library") {
                from(components["release"])
                groupId = "ru.joydev"
                artifactId = "gmail-sender"
                version = "1.0.0"
            }
        }
    }
}
