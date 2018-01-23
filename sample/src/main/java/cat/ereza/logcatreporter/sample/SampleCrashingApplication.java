/*
 * Copyright 2016-2017 Eduard Ereza Martínez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cat.ereza.logcatreporter.sample;

import android.app.Application;
import android.util.Log;

import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;
import java.util.Arrays;

import cat.ereza.logcatreporter.LogcatReporter;
import io.fabric.sdk.android.Fabric;

public class SampleCrashingApplication extends Application{

    public static final String TAG = "CLTest";

    @Override
    public void onCreate() {
        super.onCreate();

        //Initialize Crashlytics as normal
        Fabric.with(this, new Crashlytics());

        //Install LogcatReporter
        //LogcatReporter.install();
        //LogcatReporter.install(2000, 500);
        //You can define some specific TAGs to log, otherwise all logs found will be logged
        LogcatReporter.install(new ArrayList<>(Arrays.asList( TAG, "ActivityThread" )));
        Log.i(TAG, "Lc: LogcatReporter.install()");
    }
}
