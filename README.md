# ecommerce-project

## プロジェクト概要

このプロジェクトは、Spring Bootを使用したEコマースウェブアプリケーションです。ユーザー認証、商品管理、ショッピングカート機能などを提供します。

## 技術スタック

- Java 17
- Spring Boot 3.4.3
- Spring Security
- Spring Data JPA
- Thymeleaf
- MySQL/H2 Database
- Maven

## ローカル環境のセットアップ

### 前提条件

- Java 17以上がインストールされていること
- Mavenがインストールされていること
- GitがインストールされていてGitHubアカウントがあること

### 手順

#### 1. リポジトリのクローン

```bash
git clone https://github.com/noguchisatoru/ecommerce-project.git
```


プロジェクトディレクトリに移動
cd ecommerce-project

#### 2. データベースの設定

デフォルトでは、アプリケーションはH2インメモリデータベースを使用します。MySQL/PostgreSQLなどを使用する場合は、`application.properties`ファイルを編集してください。

MySQL設定例
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

#### 3. アプリケーションの起動

Mavenでプロジェクトをビルド
mvn clean install

アプリケーションを実行
mvn spring-boot:run


または、IDEからプロジェクトを開いて実行することもできます。

#### 4. アプリケーションへのアクセス

アプリケーションが起動したら、ブラウザで以下のURLにアクセスしてください：

http://localhost:8080

### テストユーザー

アプリケーション起動時に、以下のテストユーザーが自動的に作成されます：

1. 一般ユーザー
   - ユーザー名: user
   - パスワード: password

2. 管理者ユーザー
   - ユーザー名: admin
   - パスワード: admin

## 開発ガイドライン

### ブランチ戦略

- `main`: 本番環境用のブランチ
- `develop`: 開発用のブランチ
- 機能追加は `feature/機能名` ブランチで行う
- バグ修正は `bugfix/バグ内容` ブランチで行う

### コミットメッセージの規約

[タイプ]: 変更内容の要約
詳細な説明（必要な場合）

### コーディング規約

- インデントは4スペース
- メソッド名は小文字で始める

タイプ:
- `feat`: 新機能
- `fix`: バグ修正
- `docs`: ドキュメントのみの変更
- `style`: コードの意味に影響を与えない変更（空白、フォーマットなど）
- `refactor`: バグ修正や機能追加ではないコード変更
- `test`: テストの追加・修正
- `chore`: ビルドプロセスやツールの変更

### プルリクエスト

1. 作業用ブランチを作成
2. 変更を実装
3. テストを実行
4. 変更をコミット
5. プルリクエストを作成
6. レビュー後にマージ

## トラブルシューティング

### よくある問題と解決策

1. **データベース接続エラー**
   - データベースが起動していることを確認
   - 接続情報（URL、ユーザー名、パスワード）が正しいことを確認

2. **ビルドエラー**
   - Javaのバージョンが17以上であることを確認
   - Mavenの依存関係をクリーンアップ: `mvn clean`

3. **認証エラー**
   - デフォルトのユーザー情報でログインできない場合は、データベースをリセット
   - ロールが正しく設定されているか確認

4. **外部キー制約エラー**
   - データベースのリレーションシップを確認
   - 削除操作の前に関連するエンティティを適切に処理