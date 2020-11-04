/*
Copyright 2020 Adobe. All rights reserved.
This file is licensed to you under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License. You may obtain a copy
of the License at http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under
the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR REPRESENTATIONS
OF ANY KIND, either express or implied. See the License for the specific language
governing permissions and limitations under the License.
*/

package com.adobe.marketing.mobile.flutter;

import android.util.Log;

import com.adobe.marketing.mobile.Griffon;


import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** FlutterGriffonPlugin */
public class FlutterGriffonPlugin implements MethodCallHandler {

  private static final String TAG = "FlutterGriffonPlugin";

  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_griffon");
    channel.setMethodCallHandler(new FlutterGriffonPlugin());
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if ("extensionVersion".equals(call.method)) {
      result.success(Griffon.extensionVersion());
    } else if ("startSession".equals(call.method)) {
      String url = (String) call.arguments;
      if (url.isEmpty()) {
        Log.e(TAG, "Unable to start griffon session, argument parsing failed");
        return;
      }
      Griffon.startSession(url);
      result.success(null);
    } else {
      result.notImplemented();
    }
  }
}
