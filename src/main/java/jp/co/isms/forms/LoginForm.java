package jp.co.isms.forms;

import java.io.Serializable;

import lombok.Data;

/**
 * ログインフォーム
 *
 */
@Data
public class LoginForm implements Serializable {

	private static final long serialVersionUID = -756903488569732470L;

	private String userId;

	private String password;
}
