City Switcher - Android project

هذه نسخة تجريبية بسيطة لمساعدتك على الوصول إلى إعدادات الوقت والموقع.

المدن:
- Texas (معظم المدن الكبرى): America/Chicago
- Los Angeles: America/Los_Angeles
- Egypt: Africa/Cairo

طريقة الاستخدام:
1. افتح المشروع في Android Studio.
2. انتظر انتهاء Gradle Sync.
3. وصّل هاتف Android وفعّل USB debugging، أو استخدم محاكي Android.
4. اضغط Run لتثبيت التطبيق.

لبناء APK:
من Android Studio اختر Build > Build Bundle(s) / APK(s) > Build APK(s).
ستجد الملف عادةً في app/build/outputs/apk/debug/app-debug.apk.

مهم:
- هذا المشروع لا يغيّر المنطقة الزمنية بنفسه؛ Android لا يسمح للتطبيق العادي بذلك دون صلاحية خاصة.
- عند اختيار مدينة، يفتح صفحة Date & time لتختار المنطقة الزمنية يدويًا.
- فعّل Set time automatically في إعدادات الهاتف ليضبط الوقت من الشبكة.
- زر Location Settings يفتح إعدادات الموقع فقط؛ لا يعيّن مدينة وهمية ولا يغيّر GPS.
- لا يغيّر البروكسي أو IP.
