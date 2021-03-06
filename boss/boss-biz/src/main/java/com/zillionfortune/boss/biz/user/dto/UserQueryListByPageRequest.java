/*
 * Copyright (c) ${year}, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 */
package com.zillionfortune.boss.biz.user.dto;

import com.zillionfortune.boss.common.dto.BasePageRequest;

/**
 * ClassName: UserQueryListByPageRequest <br/>
 * Function: 用户列表_请求参数对象. <br/>
 * Date: 2017年2月21日 下午4:58:36 <br/>
 *
 * @author kaiyun@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
public class UserQueryListByPageRequest extends BasePageRequest {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 */
	private static final long serialVersionUID = 1L;

	/** 邮箱  非必输  */
	private String email;
	
	/** 手机号  非必输  */
	private String mobile;
	
	/** 用户名  非必输  */
	private String userName;

	/** set/get()  */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


}
