object Versions {
    const val min_sdk = 31
    const val target_sdk = 33
    const val compile_sdk = 33

    const val jvm_target = "11"
    const val jvm_inject = "1"

    const val gradle = "7.3.1"

    const val di_hilt = "2.44"

    // Note when updating Kotlin or Compose Compiler version, make sure that they match
    // https://developer.android.com/jetpack/androidx/releases/compose-kotlin#pre-release_kotlin_compatibility
    const val kotlin_lang = "1.7.20"
    const val compose_compiler = "1.3.2"

    // Note that the Compose Compiler library is not included in the BOM
    // See https://developer.android.com/jetpack/compose/setup#using-the-bom
    const val compose_bom_version = "2022.12.00"
    const val compose_activity = "1.6.1"
    const val compose_navigation = "2.5.3"
    const val compose_hilt_navigation = "1.0.0"
    const val compose_view_model = "2.5.1"
    const val compose_paging = "1.0.0-alpha17"
    const val compose_coil = "2.2.2"

    const val kotlin_coroutines = "1.6.4"
    const val room = "2.4.3"
    const val androidx_core = "1.9.0"
    const val androidx_activity = "1.6.1"
    const val annotations = "1.5.0"
    const val appcompat = "1.5.1"
    const val lifecycle = "2.5.1"
    const val material = "1.7.0"
    const val accompanist = "0.21.1-beta"
    const val glide = "0.15.0"
    const val retrofit = "2.9.0"
    const val okhttp_logging = "4.9.1"
    const val three_ten_bp = "1.3.1"
    const val timber = "5.0.1"

    // Testing
    const val testing_arch_core = "2.1.0"
    const val testing_androidx_core = "1.5.0"
    const val testing_junit_ext = "1.1.4"
    const val testing_runner = "1.5.1"
    const val testing_rules = "1.5.0"
    const val testing_truth_ext = "1.5.0"
    const val testing_espresso = "3.4.0"
    const val testing_hamcrest = "2.2"
    const val testing_junit = "4.13.2"
    const val testing_mockito = "2.25.0"
    const val testing_mock_web_server = "4.4.0"
}