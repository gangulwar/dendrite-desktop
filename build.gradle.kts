import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

group = "com.gangulwar"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality

    implementation("org.jetbrains.androidx.navigation:navigation-compose:2.7.0-alpha07")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.2.2")

    // Koin for Kotlin
    implementation("io.insert-koin:koin-core:3.4.0")
//    implementation("io.insert-koin:koin-core-ext:3.2.0")

    // Koin for Compose
//    implementation("io.insert-koin:koin-compose:3.2.0")

    // Koin for testing (optional)
    testImplementation("io.insert-koin:koin-test:3.2.0")

    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Dendrite-Desktop"
            packageVersion = "1.0.0"
        }
    }
}
