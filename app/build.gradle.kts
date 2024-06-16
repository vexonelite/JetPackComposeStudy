plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.gmail.vexonelite.jetpack.study"

    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.gmail.vexonelite.jetpack.study"

        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
//    kotlinOptions {
//        jvmTarget = "1.8"
//    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompilerExt.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.7")

    implementation(libs.coreKtx)

    val composeBom = platform(libs.composeBoM)
    implementation(composeBom)

    // Material Design 3
    implementation(libs.composeMaterial3)

    implementation(libs.composeUI)
    implementation(libs.composeUiGraphics)

    // Android Studio Preview support
    implementation(libs.composeUiToolingPreview)
    debugImplementation(libs.composeUiTooling)

    // Optional - Integration with activities
    implementation(libs.activityCompose)

    // Optional - Integration with ViewModels
    implementation(libs.lifecycleViewmodelCompose)
    implementation(libs.lifecycleRuntimeCompose)
    implementation(libs.lifecycleRuntimeKtx)
    // Optional - Integration with LiveData
    implementation(libs.livedataRuntimeCompose)

    // Navigation Compose
    implementation(libs.navigationCompose)

    // Coil - Image Loader
    implementation(libs.coilCompose)

    // Optional - Included automatically by material, only add when you need
    // the icons but not the material library (e.g. when using Material3 or a
    // custom design system based on Foundation)
    implementation(libs.composeMaterialIconsCore)
    // Optional - Add full set of material icons
    implementation(libs.composeMaterialiconsExt)
    // Optional - Add window size utils
    implementation(libs.composeMaterial3WindowSize)

    implementation(libs.sain)

    // UI Tests
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
//    debugImplementation("androidx.compose.ui:ui-test-manifest")

    testImplementation(libs.junit)
    androidTestImplementation(libs.testExtJunit)
    androidTestImplementation(libs.testEspresso)

    androidTestImplementation(composeBom)

}