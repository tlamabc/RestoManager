plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.droidfreshsquad.poly2023"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.droidfreshsquad.poly2023"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation ("com.google.android.gms:play-services-wallet:19.2.1")
    implementation ("com.google.android.gms:play-services-identity:18.0.1")


    implementation ("com.google.android.gms:play-services-measurement-impl:15.0.4")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel:2.5.0")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    implementation("com.squareup.retrofit2:retrofit:2.0.0")
    implementation("com.squareup.retrofit2:converter-gson:2.0.0")
    implementation ("com.google.firebase:firebase-auth:21.0.1")
    implementation("com.google.firebase:firebase-bom:32.2.2")
    implementation ("com.google.firebase:firebase-firestore:23.0.3") // Use the latest version
    implementation ("com.google.firebase:firebase-core:20.0.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    implementation ("com.google.firebase:firebase-database:20.0.2")// Include Firebase Core

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.cardview:cardview:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    //database
    implementation ("com.google.firebase:firebase-database:20.0.0")
            implementation ("com.github.ZEGOCLOUD:zego_uikit_prebuilt_call_android:+")   // Add this line to your module-level build.gradle file's dependencies, usually named [app].
            implementation ("com.github.ZEGOCLOUD:zego_uikit_signaling_plugin_android:+")

    implementation ("com.github.ZEGOCLOUD:zego_uikit_prebuilt_call_android:+")   // Add this line to your module-level build.gradle file's dependencies, usually named [app].
    implementation ("com.github.ZEGOCLOUD:zego_uikit_signaling_plugin_android:+")
    implementation(platform("com.google.firebase:firebase-bom:32.4.0"))

}