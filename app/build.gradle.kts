plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "ut3.act2.api_android"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "ut3.act2.api_android"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Implementación de Retrofit (cliente para hacer peticiones HTTP)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Implementación de GSON (para convertir JSON a objetos)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
}