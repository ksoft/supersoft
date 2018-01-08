package com.datuzi.supersoft.service.impl;

import com.datuzi.supersoft.dao.AdmRoleRepository;
import com.datuzi.supersoft.dto.*;
import com.datuzi.supersoft.entity.AdmRole;
import com.datuzi.supersoft.service.AdmUserService;
import com.datuzi.supersoft.dao.AdmUserRepository;
import com.datuzi.supersoft.entity.AdmUser;
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
public class AdmUserServiceImpl implements AdmUserService {
    @Autowired
    private AdmUserRepository admUserRepository;
    @Autowired
    private AdmRoleRepository admRoleRepository;


    @Override
    public  ResponseDto<AdmUserDto> findOne(LoginUserDto loginUserDto) {
        AdmUser entity=admUserRepository.findAdmUserByUserCodeAndPassword(loginUserDto.getUserCode(),loginUserDto.getPassword());
        if(entity!=null){
            AdmUserDto dto=new AdmUserDto();
            BeanUtils.copyProperties(entity,dto);
            return ResponseDtoFactory.toSuccess(dto);
        }else{
            return ResponseDtoFactory.toError("未找到对应的用户信息");
        }
    }

    @Override
    public ResponseDto<AdmUserDto> findById(Long id) {
        AdmUserDto dto=new AdmUserDto();
        AdmUser entity=admUserRepository.findOne(id);
        if(entity!=null){
            BeanUtils.copyProperties(entity,dto);
            return ResponseDtoFactory.toSuccess(dto);
        }else{
            return ResponseDtoFactory.toError("未找到对应的用户信息");
        }
    }

    @Override
    public ResponseDto<Boolean> save(AdmUserDto dto) {
        AdmUser entity=EntityUtil.translate(dto,AdmUser.class);
        admUserRepository.save(entity);
        return ResponseDtoFactory.toSuccess("保存成功",Boolean.TRUE);
    }

    @Override
    public ResponseDto<AdmUserDto> update(AdmUserDto dto) {
        AdmUser admUser=admUserRepository.findOne(dto.getId());
        if(admUser!=null) {
            EntityUtil.copyPropertiesIgnoreNull(dto, admUser);
            admUserRepository.save(admUser);
            BeanUtils.copyProperties(admUser,dto);
            return ResponseDtoFactory.toSuccess("更新成功",dto);
        }else{
            return ResponseDtoFactory.toError("找不到对应的数据");
        }
    }

    @Override
    public PageResultDto<List<AdmUserDto>> findByPage(final BasePageDto searchDto) {
        Pageable pageable=new PageRequest(searchDto.getPage()-1,searchDto.getLimit());

        //查询条件构造
        Specification<AdmUser> spec = new Specification<AdmUser>() {
        @Override
        public Predicate toPredicate(Root<AdmUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            Path<String> userCode = root.get("userCode");
            Path<String> userName = root.get("userName");
            Path<String> email = root.get("email");
            Path<String> mobilePhone = root.get("mobilePhone");
            cb.conjunction();
            Predicate p=null;
            if(!StringUtils.isEmpty(searchDto.getQueryParam())) {
                Predicate p1 = cb.like(userCode, "%" + searchDto.getQueryParam() + "%");
                Predicate p2 = cb.like(userName, "%" + searchDto.getQueryParam() + "%");
                Predicate p3 = cb.like(email, "%" + searchDto.getQueryParam() + "%");
                Predicate p4 = cb.like(mobilePhone, "%" + searchDto.getQueryParam() + "%");
                p = cb.or(p1, p2, p3,p4);
            }
            return p;
            }
         };
        Page<AdmUser> page=admUserRepository.findAll(spec,pageable);
        Iterable<AdmRole> roleIterable=admRoleRepository.findAll();

        List<AdmUserDto> list=new ArrayList<>();
        for(AdmUser entity:page.getContent()){
            AdmUserDto dto=new AdmUserDto();
            BeanUtils.copyProperties(entity,dto);
            for(AdmRole role:roleIterable){
                if(entity.getRoleId().equals(role.getId())){
                    dto.setRoleName(role.getName());
                    break;
                }
            }
            list.add(dto);
        }
        return PageResultDtoFactory.toSuccess("查询成功",list,page.getTotalElements(),page.getTotalPages());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDto<Boolean> deleteById(List<Long> ids) {
        for(Long id:ids) {
            admUserRepository.delete(id);
        }
        return ResponseDtoFactory.toSuccess("删除成功",Boolean.TRUE);
    }
}
