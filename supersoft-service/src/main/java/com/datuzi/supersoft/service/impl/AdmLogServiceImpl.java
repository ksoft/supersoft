package com.datuzi.supersoft.service.impl;

import com.datuzi.supersoft.dao.AdmLogRepository;
import com.datuzi.supersoft.dao.AdmUserRepository;
import com.datuzi.supersoft.dto.*;
import com.datuzi.supersoft.entity.AdmLog;
import com.datuzi.supersoft.entity.AdmUser;
import com.datuzi.supersoft.service.AdmLogService;
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
 * Created by 27度 on 2018/1/7 0007.
 */
@Service
public class AdmLogServiceImpl implements AdmLogService{
    @Autowired
    private AdmLogRepository admLogRepository;
    @Autowired
    private AdmUserRepository admUserRepository;

    @Override
    public PageResultDto<List<AdmLogDto>> findByPage(final BasePageDto searchDto) {
        Pageable pageable=new PageRequest(searchDto.getPage()-1,searchDto.getLimit());

        //查询条件构造
        Specification<AdmLog> spec = new Specification<AdmLog>() {
            @Override
            public Predicate toPredicate(Root<AdmLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<String> name = root.get("ip");
                cb.conjunction();
                Predicate p=null;
                if(!StringUtils.isEmpty(searchDto.getQueryParam())) {
                    p= cb.like(name, "%" + searchDto.getQueryParam() + "%");
                }
                return p;
            }
        };
        Page<AdmLog> page=admLogRepository.findAll(spec,pageable);
        Iterable<AdmUser> roleIterable=admUserRepository.findAll();

        List<AdmLogDto> list=new ArrayList<>();
        for(AdmLog log:page.getContent()){
            AdmLogDto dto=new AdmLogDto();
            BeanUtils.copyProperties(log,dto);
            for(AdmUser user:roleIterable){
                if(user.getId().equals(log.getUserId())){
                    dto.setUserName(user.getUserName());
                    break;
                }
            }
            list.add(dto);
        }
        return PageResultDtoFactory.toSuccess("查询成功",list,page.getTotalElements(),page.getTotalPages());
    }

    @Override
    public ResponseDto<Boolean> save(AdmLogDto dto) {
        AdmLog entity= EntityUtil.translate(dto,AdmLog.class);
        admLogRepository.save(entity);
        return ResponseDtoFactory.toSuccess("保存成功",Boolean.TRUE);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDto<Boolean> deleteById(List<Long> ids) {
        for(Long id:ids) {
            admLogRepository.delete(id);
        }
        return ResponseDtoFactory.toSuccess("删除成功",Boolean.TRUE);
    }
}
