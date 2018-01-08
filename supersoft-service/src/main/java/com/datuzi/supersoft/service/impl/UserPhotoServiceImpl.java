package com.datuzi.supersoft.service.impl;

import com.datuzi.supersoft.dao.UserPhotoRepository;
import com.datuzi.supersoft.dto.*;
import com.datuzi.supersoft.entity.UserPhoto;
import com.datuzi.supersoft.service.UserPhotoService;
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
 * @date 2018/1/8
 */
@Service
public class UserPhotoServiceImpl implements UserPhotoService{
    @Autowired
    private UserPhotoRepository userPhotoRepository;

    @Override
    public ResponseDto<UserPhotoDto> findById(Long id) {
        UserPhotoDto dto=new UserPhotoDto();
        UserPhoto entity=userPhotoRepository.findOne(id);
        if(entity!=null){
            BeanUtils.copyProperties(entity,dto);
            return ResponseDtoFactory.toSuccess(dto);
        }else{
            return ResponseDtoFactory.toError("未找到对应的信息");
        }
    }

    @Override
    public ResponseDto<List<UserPhotoDto>> findAll() {
        List<UserPhotoDto> list=new ArrayList<>();
        Iterable<UserPhoto> ite=userPhotoRepository.findAll();
        for(UserPhoto entity:ite){
            UserPhotoDto dto=new UserPhotoDto();
            BeanUtils.copyProperties(entity,dto);
            list.add(dto);
        }
        return ResponseDtoFactory.toSuccess(list);
    }

    @Override
    public ResponseDto<Boolean> save(UserPhotoDto dto) {
        UserPhoto entity= EntityUtil.translate(dto,UserPhoto.class);
        userPhotoRepository.save(entity);
        return ResponseDtoFactory.toSuccess("保存成功",Boolean.TRUE);
    }

    @Override
    public ResponseDto<Boolean> update(UserPhotoDto dto) {
        UserPhoto entity=userPhotoRepository.findOne(dto.getId());
        if(entity!=null) {
            EntityUtil.copyPropertiesIgnoreNull(dto, entity);
            userPhotoRepository.save(entity);
            return ResponseDtoFactory.toSuccess("更新成功",Boolean.TRUE);
        }else{
            return ResponseDtoFactory.toError("找不到对应的数据");
        }
    }

    @Override
    public PageResultDto<List<UserPhotoDto>> findByPage(final BasePageDto searchDto) {
        Pageable pageable=new PageRequest(searchDto.getPage()-1,searchDto.getLimit());

        //查询条件构造
        Specification<UserPhoto> spec = new Specification<UserPhoto>() {
            @Override
            public Predicate toPredicate(Root<UserPhoto> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<String> name = root.get("imgSrc");
                cb.conjunction();
                Predicate p=null;
                if(!StringUtils.isEmpty(searchDto.getQueryParam())) {
                    p= cb.like(name, "%" + searchDto.getQueryParam() + "%");
                }
                return p;
            }
        };
        Page<UserPhoto> page=userPhotoRepository.findAll(spec,pageable);
        List<UserPhotoDto> list=new ArrayList<>();
        for(UserPhoto role:page.getContent()){
            UserPhotoDto dto=new UserPhotoDto();
            BeanUtils.copyProperties(role,dto);
            list.add(dto);
        }
        return PageResultDtoFactory.toSuccess("查询成功",list,page.getTotalElements(),page.getTotalPages());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDto<Boolean> deleteById(List<Long> ids) {
        for(Long id:ids) {
            userPhotoRepository.delete(id);
        }
        return ResponseDtoFactory.toSuccess("删除成功",Boolean.TRUE);
    }
}
