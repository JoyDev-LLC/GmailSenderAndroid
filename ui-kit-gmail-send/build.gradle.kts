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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
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
            //TODO Чтобы отправить пакет на сервер нужно добавить в gradle.properties токен // Костян Зюков tg@gra_dus
//            repositories {
//                maven {
//                    setUrl("https://gitlab.joy-dev.com/api/v4/projects/88/packages/maven")
//                    credentials(HttpHeaderCredentials::class) {
//                        name = "Private-Token"
//                        value = findProperty("gitLabPrivateToken") as String?
//                    }
//                    authentication {
//                        create("header", HttpHeaderAuthentication::class)
//                    }
//                }
//            }
        }
    }
}
