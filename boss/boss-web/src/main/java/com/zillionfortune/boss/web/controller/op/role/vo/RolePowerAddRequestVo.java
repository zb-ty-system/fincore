/*
 * Copyright (c) ${year}, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 */
package com.zillionfortune.boss.web.controller.op.role.vo;

import java.util.List;

import com.zillionfortune.boss.common.dto.BaseRequest;

/**
 * ClassName: RolePowerAddVo <br/>
 * Function:  新增角色分配权限请求参数对象. <br/>
 * Date: 2017年2月20日 下午3:22:47 <br/>
 *
 * @author zhengrunlong@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
public class RolePowerAddRequestVo extends BaseRequest {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 */
	private static final long serialVersionUID = 1L;

	/** 角色Id */
	private Integer roleId;
	
	/** 权限Id列表  */
	private List<Integer> powerIds;
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public List<Integer> getPowerIds() {
		return powerIds;
	}

	public void setPowerIds(List<Integer> powerIds) {
		this.powerIds = powerIds;
	}

}
