goat-reader-2-android-prototype
===============================

RSSリーダです。Kotlinで書いています。

<img src="https://raw.githubusercontent.com/sys1yagi/goat-reader-2-android-prototype/master/art/images/app_image.png" height="300px"/>

### ATTENTION
本アプリではアプリ内でRSSフィードを取得しに行っています。電池がヤバイです。
最終的にバックエンドを作ってそっちでデータの更新や管理をする予定です。
その辺りまで行ったらprivate repository(既に別途あります)の方に開発を移行します。

## Setup

今のところ以下の環境です。

- Android Studio 0.8.1(beta)
- Kotlin plugin 0.8.11

### 追加する予定の機能
詳しくは[issue](https://github.com/sys1yagi/goat-reader-2-android-prototype/issues)で。

- 要素をスワイプして、左だとbad, 右だとfavにする
- NavigationDrawerでall, unread, fav, badの画面に遷移する
- フィード管理画面を作る
- Pull-To-Refresh的なのを導入する
- CardView, RecyclerViewを導入する(support-v4が更新されたら)
