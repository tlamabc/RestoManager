pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven ("https://storage.zego.im/maven" )
        maven ( "https://www.jitpack.io" )
        maven ( "https://maven.java.net/content/groups/public/" )
    }
}

rootProject.name = "RestoManager"
include(":app")
 