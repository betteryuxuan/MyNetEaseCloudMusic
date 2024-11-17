# 基本页面介绍

## 登录页

登录页较为简单

* 使用webView展示隐私政策

* 使用overridePendingTransition方法设置进入动画

![Screenrecorder-2024-11-17-18-59-52-713](https://raw.githubusercontent.com/betteryuxuan/Image/main/Screenrecorder-2024-11-17-18-59-52-713.gif)

## 播放页

* 使用前台服务：实现了退出应用也能播放，并在通知栏中显示当前歌曲

* 使用本地广播：在服务中每次播放音乐广播当前播放的音乐，实现了退出播放页后再次进入可以根据不同情况同步播放进度或者播放新的音乐
* 专辑样式：在FrameLayout中使用了三个CircleImageView叠加出类似黑胶效果。旋转动画使用了属性动画，并在服务中根据播放情况控制旋转效果。
* 播放歌单：使用了BottomSheetDialogFragment，fragment内使用了RecyclerView，并且可以在fragment中该切换不同歌曲



![Screenrecorder-2024-11-17-18-49-23-668](https://raw.githubusercontent.com/betteryuxuan/Image/main/Screenrecorder-2024-11-17-18-49-23-668.gif)

* 播放栏：window知识还未学习，使用了FloatingActionButton实现简易的播放栏

![Screenrecorder-2024-11-17-18-16-03-161](https://raw.githubusercontent.com/betteryuxuan/Image/main/Screenrecorder-2024-11-17-18-16-03-161.gif)

## 推荐页

* 侧滑菜单：使用DrawerLayout
* 底部导航栏：使用BottomNavigationView
* 标题栏上部：使用AppBarLayout和Toolbar来展示侧栏按钮+搜索框+听歌识曲
* 发现页主要制作了三个recycleview，recycleview内item的样式都采用了cardView

其中顶部的布局使用了第三方库实现高斯模糊

>  [centerzx/ShapeBlurView: 高斯模糊蒙层库 (github.com)](https://github.com/centerzx/ShapeBlurView)

![Screenrecorder-2024-11-17-18-16-48-793](https://raw.githubusercontent.com/betteryuxuan/Image/main/Screenrecorder-2024-11-17-18-16-48-793.gif)

## 发现页

发现页内容和推荐页下方内容类似，只实现了banner轮播图，下面为贴图

> [youth5201314/banner: 🔥🔥🔥Banner 2.0 来了！Android广告图片轮播控件，内部基于ViewPager2实现，Indicator和UI都可以自定义。 (github.com)](https://github.com/youth5201314/banner)

![Screenrecorder-2024-11-17-18-12-57-813](https://raw.githubusercontent.com/betteryuxuan/Image/main/Screenrecorder-2024-11-17-18-12-57-813.gif)

## 动态页

* 上方使用了TabLayout来展示两个界面

* 关注界面乐迷团使用横向的recyclerview，下方自己绘制了两则动态的布局include进来
* 推荐界面和关注界面下方动态一样

![Screenrecorder-2024-11-17-18-13-19-195](https://raw.githubusercontent.com/betteryuxuan/Image/main/Screenrecorder-2024-11-17-18-13-19-195.gif)

## 我的页

我的页下方仍采用了TabLayout来展示三个界面，并且做了切换不同item背景不同的效果

![Screenrecorder-2024-11-17-18-13-34-120](https://raw.githubusercontent.com/betteryuxuan/Image/main/Screenrecorder-2024-11-17-18-13-34-120.gif)

## 搜索页

搜索页和侧滑菜单页面较为简陋

![1731840135845](https://raw.githubusercontent.com/betteryuxuan/Image/main/1731840135845.jpg)

# 不足之处

1. 使用了死数据，只能播放内置的七首歌曲，想要播放更多的要改代码，内聚低
2. mvp架构的处理不够完善，有些地方只写了mvp框架
3. 播放功能可以加入随机播放，循环播放的功能
4. 侧滑菜单布局还可以美化





---



感谢您的阅读
