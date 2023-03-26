plugins {
    id("com.github.johnrengelman.shadow").version("8.1.1")
    id("java")
    application
}

group = "me.zdziszkee.isfsystem"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.14.2")
    implementation("junit:junit:4.13.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

}
application {
    mainClass.set("me.zdziszkee.isfsystem.ISFSystem")
}
tasks.getByName<Test>("test") {
    useJUnitPlatform()
}