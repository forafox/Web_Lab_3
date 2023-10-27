plugins {
    id("java")
    id("war")
}

group = "org.forafox"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.0.0")
//    compileOnly("jakarta.servlet.jsp:jakarta.servlet.jsp-api:3.1.1")
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    compileOnly ("org.projectlombok:lombok:1.18.20")
    annotationProcessor ("org.projectlombok:lombok:1.18.20")
    compileOnly("jakarta.enterprise:jakarta.enterprise.cdi-api:4.0.1")
    compileOnly("jakarta.platform:jakarta.jakartaee-web-api:10.0.0")
    implementation("org.eclipse.persistence:org.eclipse.persistence.jpa:4.0.2")
    implementation("org.primefaces", "primefaces", "13.0.1", "", "jakarta")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}