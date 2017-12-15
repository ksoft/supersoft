package com.datuzi.supersoft.service.impl;

import com.datuzi.supersoft.dto.*;
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


    @Override
    public  ResponseDto<AdmUserDto> findAdmUser(LoginUserDto loginUserDto) {
        AdmUserDto admUserDto=new AdmUserDto();
        AdmUser user=admUserRepository.findAdmUserByUserCodeAndPassword(loginUserDto.getUserCode(),loginUserDto.getPassword());
        if(user!=null){
            BeanUtils.copyProperties(user,admUserDto);
        }
        return ResponseDtoFactory.toSuccess(admUserDto);
    }

    @Override
    public ResponseDto<Boolean> saveAdmUser(AdmUserDto admUserDto) {
        AdmUser admUser=EntityUtil.translate(admUserDto,AdmUser.class);
        admUserRepository.save(admUser);
        return ResponseDtoFactory.toSuccess(Boolean.TRUE);
    }

    @Override
    public PageResultDto<List<UserListDto>> findUserPage(UserSearchDto searchDto) {
        Pageable pageable=new PageRequest(searchDto.getPage()-1,searchDto.getLimit());
        Specification<AdmUser> spec = new Specification<AdmUser>() {        //查询条件构造
        @Override
        public Predicate toPredicate(Root<AdmUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            Path<String> userName = root.get("userName");
            Path<String> email = root.get("email");
            cb.conjunction();
            Predicate p1 = cb.like(userName, "%"+searchDto.getUserName()+"%");
            Predicate p2 = cb.like(email, "%"+searchDto.getEmail()+"%");
            Predicate p = cb.and(p1, p2);
            return p;
            }
         };
        Page<AdmUser> userPage=admUserRepository.findAll(pageable);
        List<UserListDto> list=new ArrayList<>();
        for(AdmUser user:userPage.getContent()){
            UserListDto dto=new UserListDto();
            BeanUtils.copyProperties(user,dto);
            list.add(dto);
        }
        return PageResultDtoFactory.toSuccess("查询成功",list,userPage.getTotalElements());
    }
}
