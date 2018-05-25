SQLインジェクション実践

【前置き】

SQLインジェクションできるところが見れればOKなんでHTMLの見てくれや、ロジック内の細かい処理（バリデーションとかその他諸々）は度外視。

【ビルド方法】

本プロジェクトのルートフォルダで

`gradlew build -x test`


【DB準備】
```
CREATE DATABASE `isms` /*!40100 COLLATE 'utf8_general_ci' */

use isms

CREATE TABLE user (
 user_id VARCHAR(128) NOT NULL,
 password VARCHAR(128) NOT NULL,
 user_name VARCHAR(128) NOT NULL,
 age INT(3) NULL DEFAULT NULL,
 address VARCHAR(128) NOT NULL,
 PRIMARY KEY (user_id)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
```


【ローカル起動】
```
１．Eclipse起動
２．プロジェクトインポート
３．active.profile=localでSpringBoot起動
４．http://localhost:10080/isms/sqlinjection/login
```