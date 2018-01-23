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

package cat.ereza.logcatreporter.sample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import java.util.UUID;

import cat.ereza.logcatreporter.LogcatReporter;
import cat.ereza.logcatreporter.sample.R;
import cat.ereza.logcatreporter.sample.SampleCrashingApplication;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button logButton = (Button) findViewById(R.id.button_log);
        Button crashButton = (Button) findViewById(R.id.button_crash);

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loggedString = "This is a test log! - " + UUID.randomUUID().toString();
                Log.e(SampleCrashingApplication.TAG, loggedString);
                Toast.makeText(MainActivity.this, getString(R.string.logged_toast, loggedString), Toast.LENGTH_SHORT).show();
            }
        });

        crashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                throw new RuntimeException("Hi! I'm an exception and I made the app crash!");
            }
        });

        logUser();
    }

    private void logUser() {
        // You can call any combination of these three methods
        Crashlytics.setUserIdentifier("678910");
        Crashlytics.setUserEmail("user10@fabric.io");
        Crashlytics.setUserName("Test User 10");

        // Or set some key/value manually
        Crashlytics.setInt("CompanyId", 130);

        Log.i(SampleCrashingApplication.TAG, "Lc: CL settings OK");

        // After submit to crashlytics, strings logged by Crashlytics.log are cleaned.
        Crashlytics.log("User data saved.");
        Crashlytics.log("Dados do usuário salvos... 8");
        LogcatReporter.reportExceptionWithLogcat(new Exception("This is not a crash, only an issue."));

        Log.i(SampleCrashingApplication.TAG, "Lc: CL log OK");
    }
}