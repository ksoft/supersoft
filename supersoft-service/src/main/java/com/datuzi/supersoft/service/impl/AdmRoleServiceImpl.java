package com.datuzi.supersoft.service.impl;

import com.datuzi.supersoft.dao.AdmRoleMenuRepository;
import com.datuzi.supersoft.dao.AdmRoleRepository;
import com.datuzi.supersoft.dto.*;
import com.datuzi.supersoft.entity.AdmRole;
import com.datuzi.supersoft.service.AdmRoleService;
import com.datuzi.supersoft.utils.EntityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjianbo
 * @date 2017/12/8
 */
@Service
public class AdmRoleServiceImpl implements AdmRoleService {
    @Autowired
    private AdmRoleRepository admRoleRepository;
    @Autowired
    private AdmRoleMenuRepository admRoleMenuRepository;


    @Override
    public ResponseDto<AdmRoleDto> findById(Long id) {
        AdmRoleDto dto=new AdmRoleDto();
        AdmRole entity=admRoleRepository.findOne(id);
        if(entity!=null){
            BeanUtils.copyProperties(entity,dto);
            return ResponseDtoFactory.toSuccess(dto);
        }else{
            return ResponseDtoFactory.toError("未找到对应的信息");
        }
    }

    @Override
    public ResponseDto<List<AdmRoleDto>> findAll() {
        List<AdmRoleDto> list=new ArrayList<>();
        Iterable<AdmRole> ite=admRoleRepository.findAll();
        for(AdmRole entity:ite){
            AdmRoleDto dto=new AdmRoleDto();
            BeanUtils.copyProperties(entity,dto);
            list.add(dto);
        }
        return ResponseDtoFactory.toSuccess(list);
    }

    @Override
    public ResponseDto<Boolean> save(AdmRoleDto dto) {
        AdmRole entity=EntityUtil.translate(dto,AdmRole.class);
        admRoleRepository.save(entity);
        return ResponseDtoFactory.toSuccess("保存成功",Boolean.TRUE);
    }

    @Override
    public ResponseDto<Boolean> update(AdmRoleDto dto) {
        AdmRole entity=admRoleRepository.findOne(dto.getId());
        if(entity!=null) {
            EntityUtil.copyPropertiesIgnoreNull(dto, entity);
            admRoleRepository.save(entity);
            return ResponseDtoFactory.toSuccess("更新成功",Boolean.TRUE);
        }else{
            return ResponseDtoFactory.toError("找不到对应的数据");
        }
    }

    @Override
    public PageResultDto<List<AdmRoleDto>> findByPage(final BasePageDto searchDto) {
        Pageable pageable=new PageRequest(searchDto.getPage()-1,searchDto.getLimit());

        //查询条件构造
        Specification<AdmRole> spec = new Specification<AdmRole>() {
        @Override
        public Predicate toPredicate(Root<AdmRole> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            Path<String> name = root.get("name");
            cb.conjunction();
            Predicate p=null;
            if(!StringUtils.isEmpty(searchDto.getQueryParam())) {
                p= cb.like(name, "%" + searchDto.getQueryParam() + "%");
            }
            return p;
            }
         };
        Page<AdmRole> page=admRoleRepository.findAll(spec,pageable);
        List<AdmRoleDto> list=new ArrayList<>();
        for(AdmRole entity:page.getContent()){
            AdmRoleDto dto=new AdmRoleDto();
            BeanUtils.copyProperties(entity,dto);
            list.add(dto);
        }
        return PageResultDtoFactory.toSuccess("查询成功",list,page.getTotalElements(),page.getTotalPages());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDto<Boolean> deleteById(List<Long> ids) {
        for(Long id:ids) {
            admRoleRepository.delete(id);
            admRoleMenuRepository.deleteByRoleId(id);
        }
        return ResponseDtoFactory.toSuccess("删除成功",Boolean.TRUE);
    }
}
