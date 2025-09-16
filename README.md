# SWIPI - Android App build on SWAPI-API

Thanks for checking out SWIPI (Swipe-pee). It's a KMP Android app built upon the SWAPI Star Wars API.

SWIPI is grouping the data by characters. You can swipe through them (hence the name) and see all available information about these characters.

By compiling and running this app, you accept to send your IP to https://swapi.py4e.com/ .

Shout outs to ShyFoundry for the great SF Distant Galaxy font: https://www.fontspace.com/sf-distant-galaxy-font-f6436 .

The app is build upon the official [KMP App Templative Native](https://github.com/Kotlin/KMP-App-Template-Native) template.

https://github.com/user-attachments/assets/cd6c5d7c-eee1-49e3-bfd7-e5bf477d6981





## Technologies

This Android app is using KMM for its business logic and native Jetpack Compose for the Android UI

The app uses the following multiplatform dependencies in its implementation:

- [Ktor](https://ktor.io/) for networking
- [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) for JSON handling
- [Koin](https://github.com/InsertKoinIO/koin) for dependency injection
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous programming
- [Jetpack Compose](https://developer.android.com/jetpack/compose) for UI
- [Navigation component](https://developer.android.com/jetpack/compose/navigation) for UI navigation
- [kotlin.test](https://kotlinlang.org/api/core/kotlin-test/) for Unit Tests

## How To Set Up

Just open and run the app in Android Studio.

## Future Features

* More UI/Unit tests
* Search and filter characters
* Download your favourite characters to check them offline
* Cross-Reference Movies/Vehicles/... to see their connections
* Putting all text in a single strings.xml and adding more languages
