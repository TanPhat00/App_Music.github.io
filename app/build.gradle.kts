plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.da_app_music"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.da_app_music"
        minSdk = 24
        targetSdk = 33
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

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // thư viện picasso
    implementation("com.squareup.picasso:picasso:2.8")
    //circleindicator
    implementation("me.relex:circleindicator:2.1.4")
//    implementation("de.hdodenhof:circleimageview:3.1.0")
    //cardview
//    implementation("androidx.cardview:cardview:1.0.1")
//    implementation("com.android.volley:volley:1.2.1")

    //
//    implementation("androidx.recyclerview:recyclerview:1.3.1")
//    implementation("androidx.viewpager2:viewpager2:1.0.0")
    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")

    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")

    implementation ("androidx.cardview:cardview:1.0.0")


}