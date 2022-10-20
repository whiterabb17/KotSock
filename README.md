# Mobile Kotlin socket io
This is a Kotlin MultiPlatform library that provides real-time, event-based communication for iOS and Android.

## Table of Contents
- [Features](#features)
- [Requirements](#requirements)
- [Versions](#versions)
- [Installation](#installation)
- [Usage](#usage)
- [Samples](#samples)
- [Set Up Locally](#set-up-locally)
- [Contributing](#contributing)
- [License](#license)

## Features
- **Socket.IO in common code** - actual implementations is [socket.io-client-java](https://github.com/socketio/socket.io-client-java) and [socket.io-client-swift](https://github.com/socketio/socket.io-client-swift);

## Requirements
- Gradle version 7+
- Android API 16+
- iOS version 11.0+

## Installation
root build.gradle  
```groovy
allprojects {
    repositories {
        mavenCentral()
    }
}
```

project build.gradle
```groovy
dependencies {
    commonMainApi("dev.icerock.moko:socket-io:0.3.0")
    commonMainApi("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.1")
}

cocoaPods {
    podsProject = file("../ios-app/Pods/Pods.xcodeproj") // here should be path to Pods xcode project

    pod("mokoSocketIo", onlyLink = true)
}
```

Podfile
```ruby
pod 'mokoSocketIo', :git => 'https://github.com/whiterabb17/KotSock.git', :tag => 'release/0.3.0'
```

## Usage
`common`:
```kotlin
val socket = Socket(
    endpoint = "wss://my-super-server:8080",
    config = SocketOptions(
        queryParams = mapOf("token" to "MySuperToken"),
        transport = SocketOptions.Transport.WEBSOCKET
    )
) {
    on(SocketEvent.Connect) {
        println("connect")
    }

    on(SocketEvent.Connecting) {
        println("connecting")
    }

    on(SocketEvent.Disconnect) {
        println("disconnect")
    }

    on(SocketEvent.Error) {
        println("error $it")
    }

    on(SocketEvent.Reconnect) {
        println("reconnect")
    }

    on(SocketEvent.ReconnectAttempt) {
        println("reconnect attempt $it")
    }

    on(SocketEvent.Ping) {
        println("ping")
    }

    on(SocketEvent.Pong) {
        println("pong")
    }

    on("employee.connected") { data ->
        val serializer = DeliveryCar.serializer()
        val json = JSON.nonstrict
        val deliveryCar: DeliveryCar = json.parse(serializer, data)
        //...
    }
}
```

## Samples
Please see more examples in the [sample directory](sample).

## Set Up Locally 
- The [socket-io directory](socket-io) contains the `socket-io` library;
- The [sample directory](sample) contains sample apps for Android and iOS; plus the mpp-library connected to the apps;
