# kotlin-extension-android-model

## 说明

这个是一个干净的安卓模块工程，可以直接以模块的方式引用到您所在的项目中。同时他也是[kotlin-extension-android](https://github.com/sollyu/kotlin-extension-android)工程的核心代码。

如若想以依赖库的方式引用，可直接使用[kotlin-extension-android](https://github.com/sollyu/kotlin-extension-android)模块即可。

## 使用

引用模块之后，直接使用即可。如`String.md5()`，Android Studio会自动导入相关包。

## 功能

详细的函数说明，请直接查阅函数的文档，以下列表可能不是很全。


```
# String
String.random
String.mosaic
String.base64Encode
String.base64Decode
String.md5
String.isEmailValid
String.isContainChinese
String.isPhoneNumber

# Throwable
Throwable.i18nMessage

# File
File.readAsText
File.md5
File.writeStringToFile
File.deleteIfExist
File.clear
File.notExists

# Date
Date.format
Date.isToday
Date.isFuture
Date.isTomorrow
Date.isAfterTomorrow
Date.add
Date.sub
Date.toCalendar
Date.dayMonth
Date.day
Date.toTime

# InputStream
InputStream.toString
InputStream.toByteArray
InputStream.toCharArray
InputStream.equal

# Dialog
Dialog.show

# Context
Context.queryUsageStats
Context.getTelephonyManager
Context.getSystemString
Context.launchAppByPackage
Context.gotoMarket
Context.gotoUsageAccessSettings
Context.gotoAppDetailsSettings
Context.gotoHome
Context.installedPackageList
Context.isPackageInstalled
Context.getApplicationInfo
Context.getApplicationSignature
Context.getPackageSignature
Context.getClipboardString
Context.setClipboardString
Context.setClipboardHtmlText
Context.dp2px
Context.sp2px
Context.px2dp
Context.px2sp

# SharedPreferences
SharedPreferences.has
SharedPreferences.getJson

# ApplicationInfo
ApplicationInfo.isSystemApp
ApplicationInfo.isSystemUpdateApp
ApplicationInfo.isUserApp

# Bitmap
Bitmap.convertToByteArray
Bitmap.length
Bitmap.flipping
Bitmap.rotate
Bitmap.zoom
Bitmap.zoomByWidthAndHeight
Bitmap.resize
Bitmap.save
Bitmap.convert
Bitmap.crop

# Handler
Handler.postDelayed

# EditText
EditText.onImeAction
EditText.onImeActionDone
EditText.onImeActionNext
EditText.onImeActionSend
EditText.onImeActionSearch
EditText.onImeActionGo

# TextView
TextView.hideSoftInputFromWindow

# View
View.showSoftInput
View.getClipboardString
View.setClipboardString
View.setClipboardHtmlText

```

## LICENSE

```text
Copyright 2018-2019 Sollyu, Wonium

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
