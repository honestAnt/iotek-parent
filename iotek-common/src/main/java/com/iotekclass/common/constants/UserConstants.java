package com.iotekclass.common.constants;

public final class UserConstants {
	private UserConstants() {
	}

	/* 登录用户信息存入session的key */
	public static final String LOGIN_USER_KEY = "login_user";
	/* 登录用户ID存入session的key */
	public static final String LOGIN_USER_ID_KEY = "login_user_id";
	/* 登录用户详细信息存入session的key */
	public static final String LOGIN_USER_INFO_KEY = "login_user_info";
	/* 学生信息存入session的key */
	public static final String LOGIN_STU_ACCOUNT_KEY = "login_stu_account";
	/* 教师信息存入session的key */
	public static final String LOGIN_TEACHER_ACCOUNT_KEY = "login_teacher_account";
	/* 验证码存入session的key */
	public static final String VERIFY_CODE_KEY = "verify_code";
	
	/**
	 * 存被json转换过的用户
	 */
	public static final String JSON_LOGIN_USER_KEY = "json_login_user";
	
	/**
	 * 存被json转换过的用户详情
	 */
	public static final String JSON_LOGIN_USER_INFO_KEY = "json_login_user_info";
	
	/**
	 * 后台账号角色
	 */
	public static final String ACCOUNT_ROLE_KEY = "account_role";
	
	/**
	 * 后台用户权限后缀
	 */
	public static final String ACCOUNT_PERMISSION_KEY = "account_permission";
	
	 /*
     * 金币交易原因
     */
    public interface DealSource {

        public static final int REGISTER = 1; // 注册
        public static final int MOBILE   = 2; // 绑定手机
        public static final int EMAIL    = 3; // 绑定邮箱
        public static final int COMMENT  = 4; //评价商品
        public static final int GIFT     = 5; // 购买商品赠送
        public static final int LOGON    = 6; // 登陆
        public static final int ANSWER   = 7; // 答题赠送
        public static final int REWARD   = 8; // 获得悬赏
        public static final int ASK      = 9; // 提问扣分
    }

    public interface DealType {

        public static final int BOTH  = 0; //
        public static final int GAIN  = 1; // 获取金币
        public static final int SPEND = 2; // 花费金币
    }

    public interface SystemGold {

        public static final int    REGISTER     = 50;        // 注册奖励50个金币
        public static final int    MOBILE       = 50;        // 绑定手机奖励50个金币
        public static final int    EMAIL        = 50;        // 绑定邮箱奖励50个金币
        public static final int    COMMENT      = 50;        // 评价商品赠送50个金币
        public static final int    LOGON        = 20;        // 每天登陆赠送20个金币
        public static final int    ANSWER       = 5;         // 每次答题赠送5个金币

        public static final String REGISTER_MSG = "注册奖励金币";
        public static final String MOBILE_MSG   = "绑定手机奖励金币";
        public static final String EMAIL_MSG    = "绑定邮箱奖励金币";
        public static final String COMMENT_MSG  = "评价商品赠送金币";
        public static final String LOGON_MSG    = "每天登陆赠送金币";
        public static final String ANSWER_MSG   = "每次答题赠送金币";
    }

    /*
     * 奖励用户商品总价百分比金币数
     */
    public interface GoodsGift {

        public static final int    GOODS_PRICE_PERCENT = 10;
        public static final String GOODS_GIFT_MSG      = "购买商品赠送金币";
    }

    public static final String ASK_DECREASE_GOLD_MSG = "提问扣除金币";
    public static final String REWARD_FOR_BEST       = "获取悬赏因为最佳答案";
}
