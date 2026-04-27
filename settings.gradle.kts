#!/usr/bin/env kotlin

pluginManagement {
	repositories {
		mavenLocal()
		mavenCentral()
		gradlePluginPortal()
		maven("https://maven.fabricmc.net/")
		maven("https://maven.kikugie.dev/snapshots") { name = "KikuGie Snapshots" }
	}
}

plugins {
	id("dev.kikugie.stonecutter").version("0.9.2")
}

stonecutter {
	create(rootProject) {
		versions("1.21", "1.21.5", "1.21.10").buildscript("build.gradle.kts")
//		version("26.1").buildscript("unobfuscated.gradle.kts")
		vcsVersion = "1.21.5"
	}
}