plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}
repositories {
    mavenCentral()
}
java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}