-keep class java.lang.String { *; }
-keep class java.lang.invoke.** { *; }
-keep public class com.codewithre.core.data.** { *; }
-keep class com.codewithre.core.domain.repository.* { *; }

-keepclassmembers class com.codewithre.core.domain.repository.* { *; }
-keepclassmembers class com.codewithre.core.data.** { *; }

-keep class *Test { *; }
-keep class org.junit.** { *; }
-keep class org.mockito.** { *; }
-keep class androidx.test.** { *; }