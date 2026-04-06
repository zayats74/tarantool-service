import com.google.protobuf.gradle.id

plugins {
	java
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.google.protobuf") version "0.9.5"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

extra["springGrpcVersion"] = "1.0.2"

dependencies {
    compileOnly("javax.annotation:javax.annotation-api:1.3.2")
    implementation("jakarta.annotation:jakarta.annotation-api:2.1.1")
    //grpc
    implementation("io.grpc:grpc-netty:1.75.0")
    implementation("io.grpc:grpc-protobuf:1.59.0")
    implementation("io.grpc:grpc-stub:1.59.0")
    implementation("io.grpc:grpc-services:1.59.0")
    implementation("net.devh:grpc-server-spring-boot-starter:2.15.0.RELEASE")

    //protobuf
    implementation("com.google.protobuf:protobuf-java:3.24.0")
    implementation("com.google.protobuf:protobuf-java-util:3.24.0")

    //tarantool
    implementation("io.tarantool:tarantool-java-sdk:1.5.0")
    implementation("io.netty:netty-all:4.1.104.Final")
    implementation("io.tarantool:cartridge-driver:0.13.0")
    implementation("io.tarantool:tarantool-client:1.5.0")
    implementation("io.tarantool:spring-data-tarantool:0.6.1")
    implementation("org.springframework.data:spring-data-keyvalue:3.2.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.0")

    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    testCompileOnly("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	//testImplementation("org.springframework.grpc:spring-grpc-test")
	testCompileOnly("org.projectlombok:lombok")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testAnnotationProcessor("org.projectlombok:lombok")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.grpc:spring-grpc-dependencies:${property("springGrpcVersion")}")
	}
}

protobuf {
	protoc {
		artifact = "com.google.protobuf:protoc:3.24.0"
	}
	plugins {
		id("grpc") {
			artifact = "io.grpc:protoc-gen-grpc-java:1.59.0"
		}
	}
	generateProtoTasks {
		all().forEach {
			it.plugins {
				id("grpc") {
					option("@generated=omit")
				}
			}
		}
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
