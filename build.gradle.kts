plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.kotlin.multiplatform.library")

    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.kotlin.plugin.serialization")
}

kotlin {

    // Target declarations - add or remove as needed below. These define
    // which platforms this KMP module supports.
    // See: https://kotlinlang.org/docs/multiplatform-discover-project.html#targets
    androidLibrary {
        namespace = "com.shared.core"
        compileSdk = 36
        minSdk = 24

        withHostTestBuilder {
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    // For iOS targets, this is also where you should
    // configure native binary output. For more information, see:
    // https://kotlinlang.org/docs/multiplatform-build-native-binaries.html#build-xcframeworks

    // A step-by-step guide on how to include this library in an XCode
    // project can be found here:
    // https://developer.android.com/kotlin/multiplatform/migrate
    val xcfName = "coreKit"

    iosX64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    sourceSets {

        androidMain {
            dependencies {
                api(compose.preview)
                api("androidx.activity:activity-compose:1.11.0")

                api("io.ktor:ktor-client-android:3.3.0")
                api("io.ktor:ktor-client-okhttp:3.3.0")

                val lifecycle = "2.9.3"
                api("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle")
                api("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle")
                api("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle")
                api("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle")
                api("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle")
                api("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle")
                api("androidx.lifecycle:lifecycle-common-java8:$lifecycle")
                api("androidx.lifecycle:lifecycle-service:$lifecycle")
            }
        }

        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib:2.2.10")

                //Lifecycle
                api("org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:2.9.4")
                api("org.jetbrains.androidx.lifecycle:lifecycle-runtime-compose:2.9.4")

                //Navigation
                api("org.jetbrains.androidx.navigation:navigation-compose:2.9.0")

                //Serialization
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")

                //Koin
                val koinVersion = "4.1.1"
                api("io.insert-koin:koin-compose:$koinVersion")
                api("io.insert-koin:koin-compose-viewmodel:$koinVersion")
                api("io.insert-koin:koin-compose-viewmodel-navigation:$koinVersion")

                //Kotor
                val ktorVersion = "3.3.0"
                api("io.ktor:ktor-client-core:$ktorVersion")
                api("io.ktor:ktor-client-logging:$ktorVersion")
                api("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                api("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                api("io.ktor:ktor-client-websockets:$ktorVersion")


                //Coroutines
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")


                api(compose.runtime)
                api(compose.foundation)
                api(compose.material3)
                api(compose.ui)
                api(compose.components.uiToolingPreview)

                implementation(compose.components.resources)
            }
        }


        iosMain {
            dependencies {
                api("io.ktor:ktor-client-darwin:3.3.0")
            }
        }


        commonTest {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test:2.2.10")
            }
        }



        getByName("androidDeviceTest") {
            dependencies {
                implementation("androidx.test:runner:1.7.0")
                implementation("androidx.test:core:1.7.0")
                implementation("androidx.test.ext:junit:1.3.0")
            }
        }
    }

}