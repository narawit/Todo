import org.gradle.api.JavaVersion
import java.util.*

object Config {
    const val minimumSdkVersion = 21
    const val compileSdkVersion = 30
    const val targetSdkVersion = 30
    const val buildToolsVersion = "30.0.2"
    const val applicationId = "com.narawit.todo"
    val versionCode = ((Date().time - 1613103400000) / 60000).toInt()
    const val versionName = "1.0.0"
    const val testInstrumentation = "androidx.test.runner.AndroidJUnitRunner"
    const val multiDexEnabled = true

    val javaVersion = JavaVersion.VERSION_1_8

    const val sourceMain = "src/main/kotlin"
    const val sourceDevelop = "src/develop/kotlin"
    const val sourceProduction = "src/production/kotlin"

    const val viewBinding = true
}

object Versions {

    // <editor-fold desc="tools">
    const val gradleAndroid = "4.1.2"
    const val kotlin = "1.4.30"
    const val gms = "4.3.5"
    const val checkDependency = "1.2.2"
    // </editor-fold>

    // <editor-fold desc="core">
    const val ktx = "1.3.2"
    const val activity = "1.2.0"
    const val fragment = "1.3.0"
    const val lifecycle = "2.3.0"
    const val navigation = "2.3.3"
    const val room = "2.2.6"
    const val worker = "2.5.0"
    const val appcompat = "1.1.0"
    const val material = "1.3.0"
    const val constraintLayout = "2.0.4"
    const val swipe = "1.1.0"
    const val cardView = "1.0.0"
    const val drawer = "1.1.1"
    const val viewpager = "1.0.0"
    const val playCore = "1.9.1"
    const val multiDex = "2.0.1"
    // </editor-fold>

    // <editor-fold desc="data">
    const val hawk = "2.0.1"
    //</editor-fold>

    // <editor-fold desc="data">
    const val coil = "1.1.1"
    //</editor-fold>

    // <editor-fold desc="network">
    const val retrofit = "2.9.0"
    const val loging = "4.9.1"
    //</editor-fold>

    // <editor-fold desc="DI">
    const val koin = "2.2.2"
    //</editor-fold>

    // <editor-fold desc="permission">
    const val dexter = "6.2.2"
    //</editor-fold>

    // <editor-fold desc="firebase">
    const val analytics = "18.0.2"
    const val storage = "19.2.1"
    const val messaging = "21.0.1"
    const val gappdistribution = "2.0.1"
    const val gcrashlytics = "2.4.1"
    const val crashlytics = "17.3.1"
    //</editor-fold>

    // <editor-fold desc="test">
    const val junit = "4.12"
    const val mockito = "3.1.0"
    const val archtest = "2.1.0"
    const val androidxcore = "1.2.0"
    const val androidxjunit = "1.1.1"
    const val androidxrule = "1.2.0"
    const val espresso = "3.2.0"
    const val truth = "0.44"
    const val coroutines = "1.2.1"
    // </editor-fold>
    const val threetenabp ="1.3.0"
    const val calendarview  = "2.0.1"
}

object Deps {

    const val gradleAndroid = "com.android.tools.build:gradle:${Versions.gradleAndroid}"
    const val gradleKotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val gradleCheckDependency = "name.remal:gradle-plugins:${Versions.checkDependency}"
    const val gradleKoin =
        "org.koin:koin-gradle-plugin:${Versions.koin}"

    const val gradleGMS = "com.google.gms:google-services:${Versions.gms}"
    const val gradleAppDistribution =
        "com.google.firebase:firebase-appdistribution-gradle:${Versions.gappdistribution}"
    const val gradleCrashlytics =
        "com.google.firebase:firebase-crashlytics-gradle:${Versions.gcrashlytics}"
    const val kotlinLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val kotlinRuntime =
        "org.jetbrains.kotlin:kotlin-android-extensions-runtime:${Versions.kotlin}"


    const val androidCore = "androidx.core:core-ktx:${Versions.ktx}"
    const val activity = "androidx.activity:activity-ktx:${Versions.activity}"
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val androidAppcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"

    const val navigationRuntime =
        "androidx.navigation:navigation-runtime-ktx:${Versions.navigation}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val cardview = "androidx.cardview:cardview:${Versions.cardView}"
    const val swipe = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipe}"
    const val viewpager = "androidx.viewpager2:viewpager2:${Versions.viewpager}"
    const val drawer = "androidx.drawerlayout:drawerlayout:${Versions.drawer}"

    const val hawk = "com.orhanobut:hawk:${Versions.hawk}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomExt = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    const val multiDex = "androidx.multidex:multidex:${Versions.multiDex}"

    const val worker = "androidx.work:work-runtime-ktx:${Versions.worker}"

    const val dexter = "com.karumi:dexter:${Versions.dexter}"

    const val playcore = "com.google.android.play:core:${Versions.playCore}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val loging = "com.squareup.okhttp3:logging-interceptor:${Versions.loging}"

    const val koin = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"

    const val coil = "io.coil-kt:coil-base:${Versions.coil}"
    const val analytics = "com.google.firebase:firebase-analytics-ktx:${Versions.analytics}"
    const val messaging = "com.google.firebase:firebase-messaging-ktx:${Versions.messaging}"
    const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx:${Versions.crashlytics}"

    const val threetenabp = "com.jakewharton.threetenabp:threetenabp:${Versions.threetenabp}"
    const val calendarview = "com.github.prolificinteractive:material-calendarview:${Versions.calendarview}"

}

