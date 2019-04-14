# GDGAndroid-atPangyo-2019-Navigation-Sample

## Navigation Graph Overview

![architecture](https://github.com/fornewid/GDGAndroid-atPangyo-2019-Navigation-Sample/blob/master/screens/00_nav_graph.jpeg?raw=true)

![](https://github.com/fornewid/GDGAndroid-atPangyo-2019-Navigation-Sample/blob/master/screens/00_nav_graph_mermaid.png?raw=true)

<details><summary>Source code</summary><p>

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
