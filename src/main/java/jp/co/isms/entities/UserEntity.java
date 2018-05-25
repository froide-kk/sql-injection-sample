package jp.co.isms.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 会員エンティティ
 *
 */
@Data
@Entity
@Table(name = "user")
public class UserEntity implements Serializable{

	private static final long serialVersionUID = -2483558781995248596L;

	@Id
	@Column(name = "user_id")
	private String userId;

	@Column(name = "password")
	private String password;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "age")
	private Integer age;

	@Column(name = "address")
	private String address;
}
