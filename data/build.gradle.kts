plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "com.ang.acb.movienight.data"

    compileSdk = Versions.compile_sdk

    defaultConfig {
        minSdk = Versions.min_sdk

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
        jvmTarget = Versions.jvm_target
    }

    // Build fails after adding the test coroutines dependency
    // https://github.com/Kotlin/kotlinx.coroutines/issues/2023
    packagingOptions {
        packagingOptions.resources.excludes += setOf(
            // Exclude AndroidX version files
            "META-INF/*.version",
            // Exclude consumer proguard files
            "META-INF/proguard/*",
            // Exclude other random properties files
            "/*.properties",
            "META-INF/*.properties"
        )
    }

    testOptions {
        unitTests {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(project(":domain"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin_coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlin_coroutines}")
    implementation("javax.inject:javax.inject:${Versions.jvm_inject}")

    // Networking
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.retrofit}")
    implementation("com.squareup.retrofit2:converter-scalars:${Versions.retrofit}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_logging}")

    // Room
    implementation("androidx.room:room-runtime:${Versions.room}")
    implementation("androidx.room:room-ktx:${Versions.room}")
    kapt("androidx.room:room-compiler:${Versions.room}")

    // Utils
    implementation("com.jakewharton.timber:timber:${Versions.timber}")

    // Testing
    testImplementation("junit:junit:${Versions.testing_junit}")
    testImplementation("org.hamcrest:hamcrest-library:${Versions.testing_hamcrest}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlin_coroutines}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin_coroutines}")
    testImplementation("androidx.test:core:${Versions.testing_androidx_core}")
    testImplementation("androidx.test.ext:junit:${Versions.testing_junit_ext}")
    testImplementation("androidx.arch.core:core-testing:${Versions.testing_arch_core}")
    testImplementation("androidx.room:room-testing:${Versions.room}")
    testImplementation("com.squareup.okhttp3:mockwebserver:${Versions.testing_mock_web_server}")
    testImplementation("org.mockito:mockito-core:${Versions.testing_mockito}")
    testImplementation("com.google.dagger:hilt-android-testing:${Versions.di_hilt}")
    kaptTest("com.google.dagger:hilt-compiler:${Versions.di_hilt}")

    kaptAndroidTest("com.google.dagger:hilt-compiler:${Versions.di_hilt}")
    androidTestImplementation("com.google.dagger:hilt-android-testing:${Versions.di_hilt}")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlin_coroutines}")
    androidTestImplementation("androidx.test:core:${Versions.testing_androidx_core}")
    androidTestImplementation("androidx.test:runner:${Versions.testing_runner}")
    androidTestImplementation("androidx.test:rules:${Versions.testing_rules}")
    androidTestImplementation("androidx.test.ext:junit:${Versions.testing_junit_ext}")
    androidTestImplementation("androidx.test.ext:truth:${Versions.testing_truth_ext}")
    androidTestImplementation("org.hamcrest:hamcrest-library:${Versions.testing_hamcrest}")
    androidTestImplementation("androidx.arch.core:core-testing:${Versions.testing_arch_core}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.testing_espresso}")
}
