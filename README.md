# WeexDebugWSClient
An extension of weex's debug websocket client



# download

```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

```groovy
dependencies {
	compile 'com.github.zjutkz:WeexDebugWSClient:1.0.5'
}
```



# usage

```java
WeexInspector.overrideWebSocketClient(new CustomerSocketClient());
```