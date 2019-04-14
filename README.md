# GDGAndroid-atPangyo-2019-Navigation-Sample

[GDG Android at Pangyo! (4월 밋업)](https://www.festa.io/events/251)의 'Navigation 살펴보기' 세션 샘플 프로젝트입니다.

동작을 확인하려면 [APK 다운로드](https://github.com/fornewid/GDGAndroid-atPangyo-2019-Navigation-Sample/releases/download/sample_1.1/GDG-Navigation-Sample_1.1.apk)를 눌러 샘플 앱을 확인해주세요.

#### 참고사항

> 이 프로젝트는 [Navigation Arch. Component](https://developer.android.com/guide/navigation)를 살펴보는 것이 주 목적입니다.
>
> - MVP, MVVM과 같은 Architecture를 사용하지 않고 View 만으로 구성되어 있습니다.(ex. Fragment, Activity)
>
> - ViewModel, LiveData과 같은 다른 Architecture Components를 사용하지 않습니다.
>
> - RxJava, Dagger2와 같은 학습곡선이 큰 라이브러리를 사용하지 않습니다.
>
> - 다만 이미지 로딩 부분에 [Glide](https://github.com/bumptech/glide)를 사용하였습니다.
>
> - MIN SDK 버전이 21(Lollipop, L OS)입니다.

### NavGraph Overview

![architecture](https://github.com/fornewid/GDGAndroid-atPangyo-2019-Navigation-Sample/blob/master/screens/00_nav_graph.jpeg?raw=true)

![](https://github.com/fornewid/GDGAndroid-atPangyo-2019-Navigation-Sample/blob/master/screens/00_nav_graph_mermaid.png?raw=true)

<details><summary>소스코드 보기</summary><p>

_Made by [mermaid](https://mermaidjs.github.io/)_
```
graph TD
subgraph nav_graph
   Splash(SplashFragment)
   Login(LoginFragment)
   Home(HomeFragment)

   subgraph nav_graph_home
      Main(MainFragment)
      Bookmark(BookmarkFragment)
   end

   Detail(DetailFragment)
   Settings(SettingsFragment)
   Web(WebActivity)
   Dialog(LoginConfirmDialogFragment)

  subgraph nav_graph_profile
     Profile(ProfileFragment)
  end
end

Shortcut --> Splash
Splash --> |Pop| Login
Splash --> |Pop| Home
Login --> |Pop| Home
Home --> |header| Profile
Home --> |tab| Main
Home --> |tab| Bookmark
Home --> |tab| Settings
Home --> |tab| Web
Main --> Detail
Bookmark --> Detail
Dialog --> Login
```
</p>
</details>
