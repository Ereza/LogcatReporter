# LogcatReporter library

This library sends the Logcat log to Crashlytics when your app crashes. Having more information about a crash is never a bad thing, right?

Yes, you could change all your `Log.x()` commands to `Crashlytics.log()`, but that's a waste of time.
Also, what about system logs or third-party libraries which you can not change? This library fixes this problem.

## How to use

### 1. Add a dependency

Add the following dependency to your build.gradle:
```gradle
dependencies {
    compile 'cat.ereza:logcatreporter:1.1.0'
}
```

### 2. Set up your application

On your application class, use this snippet:
```java
    @Override
    public void onCreate() {
        super.onCreate();

        //Initialize Crashlytics as normal (you probably already have this code)
        Fabric.with(this, new Crashlytics());

        //Initialize the LogcatReporter
        LogcatReporter.install();
    }
```

**WARNING!** You **MUST** install LogcatReporter **AFTER** initializing Crashlytics. If you don't, it will not work.

### 3. Test it

Make the app crash by using something like this in your code:
```java
throw new RuntimeException("Boom!");
```

A new Crashlytics issue should appear, and should show the application log if you go to the issue details.

### Tracking non-fatal crashes

You can report non-fatal crashes to Crashlytics with the Logcat log attached by using `LogcatReporter.reportExceptionWithLogcat(Throwable)` instead of `Crashlytics.logException(Throwable)`.

### Optional: Parameters

You can provide additional parameters to the `install()` method, they are the following:
* `lineCount`: Maximum lines of Logcat output to send to Crashlytics. The default value is 1000.
* `waitTimeInMillis`: Time that the library will wait for the process to send the log to Crashlytics. Don't set it too high because this blocks the main thread. The default value is 500.

## Using Proguard?

No need to add special rules, the library should work even with obfuscation.

## Inner workings

This library relies on the `Thread.setDefaultUncaughtExceptionHandler` method.
When an exception is caught by the library's `UncaughtExceptionHandler` it does the following:

1. Read the Logcat for the application
2. Log it to Crashlytics via `Crashlytics.log()`
3. Execute the next `UncaughtExceptionHandler` in the chain (normally the one from Crashlytics)

## Incompatibilities

On API<16, you will get no logs reported by default. If you really want to report logs from those devices,
you must declare the `READ_LOGS` permission on your manifest. We do **not** recommend enabling this since it can expose user private information or data from other apps.

## Disclaimers

* There is a small possibility that this might stop working on newer Crashlytics SDKs.
* There is no guarantee that this will work on every device (hint: it doesn't!)
* This library will not make you toast for breakfast :)

## Contributing & license

Any contribution in order to make this library better will be welcome!

The library is licensed under the [Apache License 2.0](https://github.com/Ereza/LogcatReporter/blob/master/LICENSE).