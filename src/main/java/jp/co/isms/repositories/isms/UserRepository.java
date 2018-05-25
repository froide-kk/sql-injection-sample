package jp.co.isms.repositories.isms;

import jp.co.isms.entities.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 会員用リポジトリクラス
 *
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

	/**
	 * 会員検索
	 *
	 * SpringBootが内包するSpringDataはJpaRepositoryを提供している。↓のようなメソッドを定義すればメソッド名がそのままSQLを表すことになる。
	 * 以下の場合は SELECT * FROM user WHERE user_id = ●●● AND password = ×××;
	 * が内部的に実行されるようになっている。
	 *
	 * @param userId
	 * @param password
	 * @return UserEntity
	 */
	public UserEntity findByUserIdAndPassword(String userId, String password);
}
