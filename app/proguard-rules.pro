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

# Retain Gson serialization/deserialization capabilities
# Keep Retrofit interfaces and methods

# Retrofit: Keep generic type metadata
-keepattributes Signature

# Keep Retrofit API interfaces
-keep interface * {
    @retrofit2.http.* <methods>;
}

# Prevent stripping classes annotated with Moshi's @Json or Gson's @SerializedName
-keepclassmembers class * {
    @com.squareup.moshi.Json <fields>;
    @com.google.gson.annotations.SerializedName <fields>;
}

# Keep your data classes used in the API requests/responses
-keep class com.selfproject.cordsapp.** { *; }

# Keep Moshi adapter factory for the converters
-keepclassmembers class * {
    @com.squareup.moshi.JsonClass <methods>;
}




-keep interface retrofit2.http.* { *; }
-keepattributes Signature
-keepattributes *Annotation*

# Keep Retrofit-related classes
-keep class retrofit2.Retrofit { *; }
-keep class retrofit2.** { *; }
-dontwarn retrofit2.**

# Keep Moshi-related classes
-keep class com.squareup.moshi.** { *; }
-keep @com.squareup.moshi.Json class * { *; }
-dontwarn com.squareup.moshi.**
-keepclassmembers,allowobfuscation class * {
    @com.squareup.moshi.* <fields>;
}

# Keep Response models and data classes
-keepclassmembers class com.selfproject.cordsapp.data.remote.ResponseCoordinates
-keepclassmembers class com.selfproject.cordsapp.data.remote.ErrorResponse

# Keep classes annotated with @Keep
-keep @androidx.annotation.Keep class * { *; }

-keep class com.google.gson.** { *; }
-keep class com.selfproject.cordsapp.domain.model.* { *; }
-keep class com.selfproject.cordsapp.data.remote.* { *; }
-keep class com.selfproject.cordsapp.data.* { *; }
-keep public class com.selfproject.cordsapp.** {
    public *;
}
-keep class com.selfproject.cordsapp.data.mapper.* {* ;}
-dontwarn sun.misc.**