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
        AdmUser user=admUserRepository.findAdmUserByUserCodeAndPassword(loginUserDto.getUserCode(),loginUserDto.getPassword());
        if(user!=null){
            AdmUserDto admUserDto=new AdmUserDto();
            BeanUtils.copyProperties(user,admUserDto);
            return ResponseDtoFactory.toSuccess(admUserDto);
        }else{
            return ResponseDtoFactory.toError("未找到对应的用户信息");
        }
    }

    @Override
    public ResponseDto<UserListDto> findById(Long id) {
        UserListDto userListDto=new UserListDto();
        AdmUser user=admUserRepository.findOne(id);
        if(user!=null){
            BeanUtils.copyProperties(user,userListDto);
            return ResponseDtoFactory.toSuccess(userListDto);
        }else{
            return ResponseDtoFactory.toError("未找到对应的用户信息");
        }
    }

    @Override
    public ResponseDto<Boolean> save(AdmUserDto admUserDto) {
        AdmUser admUser=EntityUtil.translate(admUserDto,AdmUser.class);
        admUserRepository.save(admUser);
        return ResponseDtoFactory.toSuccess("保存成功",Boolean.TRUE);
    }

    @Override
    public ResponseDto<AdmUserDto> update(AdmUserDto admUserDto) {
        AdmUser admUser=admUserRepository.findOne(admUserDto.getId());
        if(admUser!=null) {
            EntityUtil.copyPropertiesIgnoreNull(admUserDto, admUser);
            admUserRepository.save(admUser);
            BeanUtils.copyProperties(admUser,admUserDto);
            return ResponseDtoFactory.toSuccess("更新成功",admUserDto);
        }else{
            return ResponseDtoFactory.toError("找不到对应的数据");
        }
    }

    @Override
    public PageResultDto<List<UserListDto>> findByPage(final BasePageDto searchDto) {
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
        Page<AdmUser> userPage=admUserRepository.findAll(spec,pageable);
        Iterable<AdmRole> roleIterable=admRoleRepository.findAll();

        List<UserListDto> list=new ArrayList<>();
        for(AdmUser user:userPage.getContent()){
            UserListDto dto=new UserListDto();
            BeanUtils.copyProperties(user,dto);
            for(AdmRole role:roleIterable){
                if(user.getRoleId().equals(role.getId())){
                    dto.setRoleName(role.getName());
                    break;
                }
            }
            list.add(dto);
        }
        return PageResultDtoFactory.toSuccess("查询成功",list,userPage.getTotalElements());
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
