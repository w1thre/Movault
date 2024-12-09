# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keep class com.codewithre.core.ui.** { *; }
-keep class com.codewithre.core.domain.** { *; }
-keep class com.codewithre.core.di.** { *; }
-keep class com.codewithre.core.data.Resource { *; }

-keepclassmembers class com.codewithre.core.ui.** { *; }
-keepclassmembers class com.codewithre.core.domain.** { *; }
-keepclassmembers class com.codewithre.core.di.** { *; }
-keepclassmembers class com.codewithre.core.data.Resource$* { *; }

-keep class java.lang.String { *; }
-keep class java.lang.invoke.** { *; }
-dontwarn java.lang.invoke.StringConcatFactory
-keepclassmembers class androidx.lifecycle.LiveData { *; }
-keepclassmembers class androidx.lifecycle.ViewModel { *; }

# Keep test classes
-keep class *Test { *; }
-keep class org.junit.** { *; }
-keep class org.mockito.** { *; }
-keep class androidx.test.** { *; }
