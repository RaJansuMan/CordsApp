// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.hiltDagger) apply false
    alias(libs.plugins.androidksp) apply false
//    alias(libs.plugins.gmsService)apply false
//    alias(libs.plugins.mapBoxService) apply false
}