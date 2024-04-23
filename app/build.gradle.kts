plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.rn.livros"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rn.livros"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }



}

val glide_version = "4.16.0"
val parceler_version = "1.1.12"
val bom_version = "32.8.1"
val auth_version = "22.1.2"
val services_version = "4.3.15"
val core_version = "1.13.0"
val app_compat_version = "1.6.1"
val material_version = "1.11.0"
val constraint_layout_version = "2.1.4"
val junit_version = "4.13.2"
val test_junit_version = "1.1.5"
val espresso_core_version = "3.5.1"

dependencies {

    implementation("androidx.core:core-ktx:$core_version")
    implementation("androidx.appcompat:appcompat:$app_compat_version")
    implementation("com.google.android.material:material:$material_version")
    implementation("androidx.constraintlayout:constraintlayout:$constraint_layout_version")
    implementation("org.parceler:parceler-api:$parceler_version")
    implementation("com.google.android.gms:play-services-auth:21.1.0")
    kapt("org.parceler:parceler:$parceler_version")

    implementation("com.github.bumptech.glide:glide:$glide_version")
    kapt("com.github.bumptech.glide:compiler:$glide_version")

    implementation(platform("com.google.firebase:firebase-bom:$bom_version"))
    implementation("com.google.firebase:firebase-analytics")

    implementation("com.google.firebase:firebase-auth-ktx:$auth_version")
    implementation("com.google.gms:google-services:$services_version")

    testImplementation("junit:junit:$junit_version")
    androidTestImplementation("androidx.test.ext:junit:$test_junit_version")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espresso_core_version")
}