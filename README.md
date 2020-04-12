**Android端 MVVM + Jetpack + 组件化 demo。**

##### 主要技术点  
[AndroidX](https://developer.android.google.cn/jetpack/androidx)  
[Jetpack MVVM](https://developer.android.google.cn/jetpack/)  
[material-components-android](https://github.com/material-components/material-components-android)  
[Kotlin](https://www.kotlincn.net/)  
[Arouter](https://github.com/alibaba/ARouter)  
[Rxjava](https://github.com/ReactiveX/RxJava)  
[Retrofit](http://square.github.io/retrofit/)  

## 各模块间关系：
- `app` ：宿主工程
- `lib_base` ：三方依赖库和VM_base基类库
- `lib_common` : 公共库utils，view ，Activity，Fragment，utils 基类库
- `lib_toolbar` : titlebar封装
- `lib_webView`: 基于腾讯X5的webview封装库
- `moudle_xxxx`: moudle开头_xx的业务模块，依赖主工程

## ARouter串联模块
使用`ARouter`来跳转`Activity`和获取`Fragment`等

# Thanks to
[KunMinX](https://www.kunminx.com/)
[JessYan](https://github.com/JessYanCoding)

