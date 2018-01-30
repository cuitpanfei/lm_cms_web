package com.lm.web.repository.queryFilter;

import com.lm.web.configuration.query.Where;
import com.lm.web.configuration.query.annotation.QBindAttrField;
import com.lm.web.configuration.query.annotation.QBindEntity;
import com.lm.web.configuration.query.core.BaseQuery;
import com.lm.web.entity.po.Role;

/**
 * 
 * (用户查询构造条件) 
 * @ClassName UserQuery 
 * @author ShenZiYang 
 * @date 2018年1月8日 下午3:57:38
 */
@QBindEntity(entityClass = Role.class)
public class RoleQueryFilter extends BaseQuery{
	
	@QBindAttrField(fieldName = "roleName", where = Where.equal)
	private String roleName;
	
	@QBindAttrField(fieldName = "isDelete", where = Where.equal)
	private Short isDelete;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}
	
	
}
