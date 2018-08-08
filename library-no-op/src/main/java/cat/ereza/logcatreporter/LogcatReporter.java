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

package cat.ereza.logcatreporter;

public class LogcatReporter {

    private static final String TAG = "LogcatReporter";

    private static final int DEFAULT_WAIT_TIME_IN_MILLIS = 500;
    private static final int DEFAULT_LINE_COUNT = 1000;

    private static int lineCount;

    public static void install() {
    }

    public static void install(int lineCount) {
    }

    public static void install(final int lineCount, final int waitTimeInMillis) {
    }

    public static void reportExceptionWithLogcat(Throwable t) {
    }

    private static void logLogcat() {
    }
}
