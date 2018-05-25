package jp.co.isms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.MessageFormat;

import jp.co.isms.entities.UserEntity;
import jp.co.isms.forms.LoginForm;
import jp.co.isms.services.UserService;

/**
 * SQLインジェクションお遊び
 *
 */
@Controller
public class SqlInjectionSampleController {

	private static final String TEMPLATE_LOGIN = "isms/login";

	private static final String TEMPLATE_LOGIN_2 = "isms/login2";

	private static final String REDIRECT_DETAIL = "redirect:/sqlinjection/detail?userId={0}&password={1}";

	/**
	 * 会員サービスクラス
	 */
	@Autowired
	private UserService userService;

	/**
	 * ログイン画面表示
	 * @param model
	 * @param form
	 * @param bindingResult
	 * @return 遷移先
	 */
	@RequestMapping("/sqlinjection/login")
	public String login(Model model, @ModelAttribute("login") LoginForm form,
					BindingResult bindingResult) {

		model.addAttribute("errmsg", null);
		model.addAttribute("user", form);

		return TEMPLATE_LOGIN;
	}

	/**
	 * ログイン実行（SQLインジェクション非対応）
	 * @param model
	 * @param form
	 * @param bindingResult
	 * @return リダイレクト先
	 */
	@RequestMapping(value = "/sqlinjection/login", method = RequestMethod.POST)
	public String loginExec(Model model, @ModelAttribute("login") LoginForm form,
					BindingResult bindingResult) {

		UserEntity entity = null;

		try {
			entity = this.userService.findByUserIdAndPassword(form.getUserId(), form.getPassword());
		} catch (Exception e) {
			throw e;
		}

		if (entity == null) {
			model.addAttribute("errmsg", "IDまたはパスワードが一致してません。残念ですね。");
			model.addAttribute("user", form);
			return TEMPLATE_LOGIN;
		}
		model.addAttribute("user", entity);

		return MessageFormat.format(REDIRECT_DETAIL, entity.getUserId(), entity.getPassword());
	}

	/**
	 * ログイン画面表示
	 * @param model
	 * @param form
	 * @param bindingResult
	 * @return 遷移先
	 */
	@RequestMapping("/sqlinjection/login2")
	public String login2(Model model, @ModelAttribute("login") LoginForm form,
					BindingResult bindingResult) {

		model.addAttribute("errmsg", null);
		model.addAttribute("user", form);

		return TEMPLATE_LOGIN_2;
	}

	/**
	 * ログイン実行（SQLインジェクション対応）
	 * @param model
	 * @param form
	 * @param bindingResult
	 * @return リダイレクト先
	 */
	@RequestMapping(value = "/sqlinjection/login2", method = RequestMethod.POST)
	public String loginExec2(Model model, @ModelAttribute("login") LoginForm form,
					BindingResult bindingResult) {

		UserEntity entity = null;

		try {
			entity = this.userService.findByUserIdAndPassword2(form.getUserId(), form.getPassword());
		} catch (Exception e) {
			throw e;
		}

		if (entity == null) {
			model.addAttribute("errmsg", "IDまたはパスワードが一致してません。残念ですね。");
			model.addAttribute("user", form);
			return TEMPLATE_LOGIN_2;
		}
		model.addAttribute("user", entity);

		return MessageFormat.format(REDIRECT_DETAIL, entity.getUserId(), entity.getPassword());
	}

	/**
	 * 詳細画面表示
	 * @param model
	 * @param userId
	 * @param password
	 * @return 遷移先
	 */
	@RequestMapping("/sqlinjection/detail")
	public String detail(Model model, @RequestParam String userId, @RequestParam String password) {

		UserEntity entity = this.userService.findByUserIdAndPassword(userId, password);
		model.addAttribute("user", entity);

		return "isms/detail";
	}

}
