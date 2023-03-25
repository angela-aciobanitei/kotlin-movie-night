plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.ang.acb.movienight"

    compileSdk = Versions.compile_sdk

    defaultConfig {
        applicationId = "com.ang.acb.movienight"
        minSdk = Versions.min_sdk
        targetSdk = Versions.target_sdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            type = "String",
            name = "TMDB_API_KEY",
            value = project.properties["TMDB_API_KEY"] as String
        )
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

    kotlinOptions {
        jvmTarget = Versions.jvm_target
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_compiler
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin_coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlin_coroutines}")

    // Hilt
    implementation("com.google.dagger:hilt-android:${Versions.di_hilt}")
    kapt("com.google.dagger:hilt-compiler:${Versions.di_hilt}")

    // AndroidX
    implementation("androidx.core:core-ktx:${Versions.androidx_core}")
    implementation("androidx.activity:activity-ktx:${Versions.activity}")
    implementation("androidx.activity:activity-compose:${Versions.activity}")
    implementation("androidx.annotation:annotation:${Versions.annotations}")
    implementation("androidx.appcompat:appcompat:${Versions.appcompat}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle_view_model_compose}")
    implementation("androidx.navigation:navigation-compose:${Versions.navigation_compose}")
    implementation("androidx.hilt:hilt-navigation-compose:${Versions.hilt_navigation_compose}")
    implementation("androidx.paging:paging-compose:${Versions.paging_compose}")
    implementation("io.coil-kt:coil-compose:${Versions.coil_compose}")

    // Compose BOM
    implementation(platform("androidx.compose:compose-bom:${Versions.compose_bom_version}"))
    implementation("androidx.compose.runtime:runtime")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Google
    implementation("com.google.android.material:material:${Versions.material}")
    implementation("com.google.accompanist:accompanist-flowlayout:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-insets:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}")

    // Room
    implementation("androidx.room:room-runtime:${Versions.room}")
    implementation("androidx.room:room-ktx:${Versions.room}")
    kapt("androidx.room:room-compiler:${Versions.room}")

    // Networking
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.retrofit}")
    implementation("com.squareup.retrofit2:converter-scalars:${Versions.retrofit}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_logging}")

    // Utils
    implementation("com.jakewharton.threetenabp:threetenabp:${Versions.three_ten_bp}")
    implementation("com.jakewharton.timber:timber:${Versions.timber}")

    // Testing
    testImplementation("junit:junit:${Versions.testing_junit}")
    testImplementation("androidx.room:room-testing:${Versions.room}")
    testImplementation("com.google.dagger:hilt-android-testing:${Versions.di_hilt}")
    kaptTest("com.google.dagger:hilt-compiler:${Versions.di_hilt}")
    androidTestImplementation("com.google.dagger:hilt-android-testing:${Versions.di_hilt}")
    kaptAndroidTest("com.google.dagger:hilt-compiler:${Versions.di_hilt}")
    androidTestImplementation("androidx.test.ext:junit: ${Versions.testing_junit_ext}")
    androidTestImplementation("androidx.test.espresso:espresso-core: ${Versions.testing_espresso}")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
