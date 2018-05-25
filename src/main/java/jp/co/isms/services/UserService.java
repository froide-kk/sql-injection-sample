package jp.co.isms.services;

import jp.co.isms.entities.UserEntity;
import jp.co.isms.repositories.isms.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * 会員サービスクラス
 *
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	@Resource(name = "ismsEntityManager")
	private EntityManagerFactory entityManagerFactory;

	/**
	 * SQLインジェクション対策できてない会員取得
	 * @param userId
	 * @param password
	 * @return UserEntity
	 */
	public UserEntity findByUserIdAndPassword(String userId, String password) {

		/*
		 * 例えば画面から
		 * ユーザーID：test
		 * パスワード：' OR 'a' = 'a
		 * と入力すると
		 *
		 * SELECT * FROM user WHERE user_id = 'test' AND password = '' OR 'a' = 'a'
		 *
		 * が実行されてしまう
		 */
		String searchUser = "SELECT * FROM user"
						+ " WHERE"
						+ "  user_id = '" + userId + "'"
						+ "  AND"
						+ "  password = '" + password + "'";

		try {
			EntityManager em = this.entityManagerFactory.createEntityManager();
			Query query = em.createNativeQuery(searchUser, UserEntity.class);
			return (UserEntity)query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * SQLインジェクション対策済みの会員取得
	 * @param userId
	 * @param password
	 * @return UserEntity
	 */
	public UserEntity findByUserIdAndPassword2(String userId, String password) {

		return this.userRepository.findByUserIdAndPassword(userId, password);
	}

}
