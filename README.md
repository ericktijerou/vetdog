<img alt="Icon" src="presentation/src/main/res/mipmap-xxhdpi/ic_launcher.png?raw=true" align="left" hspace="1" vspace="1">

<a alt='Try it on Google Play' href='https://play.google.com/store/apps/details?id=erick.mobile.veterinary' target='_blank' align='right'><img
align='right' height='36' style='border:0px;height:36px;' src='https://developer.android.com/images/brand/en_generic_rgb_wo_60.png' border='0' /></a>
# Vetdog

Pet project using Clean Architecture + MVVM + Reactive Extensions + Android Architecture Components.</br>
The data is fetched from <a href='https://thedogapi.com/'>TheDogAPI</a>.</br>

<p align="center">
  <img alt='Sample' src="https://raw.githubusercontent.com/ericktijerou/mvvm-architecture-components/master/art/vetdog.gif"></br>
  <i>*Data from <a href='https://thedogapi.com/'>TheDogAPI</a></i></br>
</p>

## Libraries and tools used in the project

### Android

* [Android Support Library](https://developer.android.com/topic/libraries/support-library/index.html)
Provides additional convenience classes and features not available in the standard Framework API for easier development and support across more devices.
* [Data Binding](https://developer.android.com/topic/libraries/data-binding)
Write declarative layouts and minimize the glue code necessary to bind application logic and layouts.
* [Android KTX](https://github.com/android/android-ktx)
A set of Kotlin extensions for Android app development.

### Architecture and Design

* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)
A collection of libraries that help you design robust, testable, and maintainable apps.
Start with classes for managing your UI component lifecycle and handling data persistence.
* [Dagger]
A fully static, compile-time dependency injection framework for both Java and Android.

### Reactive

* [RX Java](https://github.com/ReactiveX/RxJava)
A library for composing asynchronous and event-based programs using observable sequences for the Java VM.
* [RX Kotlin](https://github.com/ReactiveX/RxKotlin)
RxJava bindings for Kotlin.
* [RX Android](https://github.com/ReactiveX/RxAndroid)
RxJava bindings for Android.

### View and Image

* [ConstraintLayout](https://developer.android.com/training/constraint-layout/index.html)
Allows you to create large and complex layouts with a flat view hierarchy (no nested view groups).
* [RecyclerView](http://developer.android.com/reference/android/support/v7/widget/RecyclerView.html)
A flexible view for providing a limited window into a large data set.
* [Glide](https://github.com/bumptech/glide)
An image loading and caching library for Android focused on smooth scrolling

### Data Request

* [Retrofit](http://square.github.io/retrofit/)
A type-safe HTTP client for Android and Java.
* [OkHttp](http://square.github.io/okhttp/)
An HTTP & HTTP/2 client for Android and Java applications.
* [Moshi](https://github.com/square/moshi)
A modern JSON library for Android and Java.

### Persistence

* [Room](https://developer.android.com/topic/libraries/architecture/room.html)
The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.
