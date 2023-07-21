object Versions {
    const val min_sdk = 31
    const val target_sdk = 33
    const val compile_sdk = 33

    const val jvm_target = "17"
    const val jvm_inject = "1"

    const val gradle = "8.0.2"
    const val di_hilt = "2.45"

    const val kotlin_lang = "1.8.10"
    const val kotlin_coroutines = "1.6.4"

    // Note when updating Kotlin or Compose Compiler version, make sure that they match
    // https://developer.android.com/jetpack/androidx/releases/compose-kotlin#pre-release_kotlin_compatibility
    const val compose_compiler = "1.4.4"
    const val compose_bom_version = "2023.03.00"

    const val activity = "1.7.0"
    const val appcompat = "1.6.1"
    const val androidx_core = "1.9.0"
    const val annotations = "1.6.0"
    const val accompanist = "0.30.0"
    const val coil_compose = "2.2.2"
    const val lifecycle = "2.6.1"
    const val lifecycle_view_model_compose = "2.6.1"
    const val material = "1.8.0"
    const val navigation_compose = "2.5.3"
    const val hilt_navigation_compose = "1.0.0"
    const val paging_compose = "1.0.0-alpha18"
    const val room = "2.5.1"
    const val retrofit = "2.9.0"
    const val okhttp_logging = "4.10.0"
    const val three_ten_bp = "1.4.4"
    const val timber = "5.0.1"

    // Testing
    const val testing_androidx_core = "1.5.0"
    const val testing_arch_core = "2.2.0"
    const val testing_junit_ext = "1.1.5"
    const val testing_runner = "1.5.2"
    const val testing_rules = "1.5.0"
    const val testing_truth_ext = "1.5.0"
    const val testing_espresso = "3.5.1"
    const val testing_hamcrest = "2.2"
    const val testing_junit = "4.13.2"
    const val testing_mockito = "5.0.0"
    const val testing_mock_web_server = "4.10.0"
}