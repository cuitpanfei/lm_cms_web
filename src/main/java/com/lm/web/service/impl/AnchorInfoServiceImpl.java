package com.lm.web.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lm.web.configuration.redis.cache.IdGlobalGenerator;
import com.lm.web.entity.po.AnchorInfo;
import com.lm.web.entity.vo.AnchorInfoVO;
import com.lm.web.repository.dao.AnchorInfoRepositoryDao;
import com.lm.web.repository.queryFilter.RoleQueryFilter;
import com.lm.web.service.AnchorInfoService;

@Service("anchorInfoService")
public class AnchorInfoServiceImpl implements AnchorInfoService{
	
	@Autowired
	private AnchorInfoRepositoryDao anchorInfoRepositoryDao;
	@Autowired
	private IdGlobalGenerator idGlobalGenerator;
	
	@Override
	public Page<AnchorInfo> getPageAnchorInfo(AnchorInfoVO vo, Integer pageNo, Integer pageSize, String sortField) {

		// 查询条件
		RoleQueryFilter query = new RoleQueryFilter();
		if (StringUtils.isNotEmpty(vo.getNickName())) {
			query.setRoleName(vo.getNickName());
		}

		// 过滤掉是否删除字段
		//query.setIsDelete(IsDeleteEnum.UN_DELETE.getVal());
		// 排序
		Sort sort = new Sort(Direction.DESC, sortField);
		// 分页条件
		Pageable page = new PageRequest(pageNo, pageSize, sort);
		// 分页查询
		Page<AnchorInfo> pageData = anchorInfoRepositoryDao.findAll(query, page);
                                
		return pageData;
	}

	@Override
	public void saveAnchorInfo(AnchorInfo anchorInfo) {
		AnchorInfo anchor = new AnchorInfo();
		anchor.setId(idGlobalGenerator.getSeqId(AnchorInfo.class));
		anchor.setAnchorSex(anchorInfo.getAnchorSex()); //性别
		anchor.setNickName(anchorInfo.getNickName()); //昵称
		anchor.setMobile(anchorInfo.getMobile()); //手机号
		anchor.setRemark(anchorInfo.getRemark()); //备注
		anchorInfoRepositoryDao.save(anchorInfo); //保存
	}

	@Override
	public AnchorInfo queryAnchorById(Long id) {
		return anchorInfoRepositoryDao.findOne(id);
	}

	@Override
	public void updateAnchorInfo(AnchorInfo anchorInfo) {
		//先查询是否存在
		AnchorInfo oriAnchorInfo = anchorInfoRepositoryDao.findOne(anchorInfo.getId());
		if(null != oriAnchorInfo){
			oriAnchorInfo.setNickName(anchorInfo.getNickName());
			oriAnchorInfo.setAnchorSex(anchorInfo.getAnchorSex());
			oriAnchorInfo.setMobile(anchorInfo.getMobile());
			oriAnchorInfo.setRemark(anchorInfo.getRemark());
			anchorInfoRepositoryDao.save(oriAnchorInfo);
		}
	}

	@Override
	public void deleteBatchAnchor(Long[] ids) {
		if(null != ids || ids.length > 0){
			for(Long id : ids){
				anchorInfoRepositoryDao.delete(id);
			}
		}
	}
	
	
	
	
	
	
}
