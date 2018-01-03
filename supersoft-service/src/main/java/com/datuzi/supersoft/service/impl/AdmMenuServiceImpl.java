package com.datuzi.supersoft.service.impl;

import com.datuzi.supersoft.dto.*;
import com.datuzi.supersoft.service.AdmMenuService;
import com.datuzi.supersoft.dao.AdmMenuRepository;
import com.datuzi.supersoft.entity.AdmMenu;
import com.datuzi.supersoft.utils.EntityUtil;
import com.google.common.collect.Lists;
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
 * Created by 27度 on 2017/12/9 0009.
 */
@Service
public class AdmMenuServiceImpl implements AdmMenuService{
    @Autowired
    AdmMenuRepository admMenuRepository;

    @Override
    public ResponseDto<List<TopMenuDto>> topMenu(Long roleId) {
        List<TopMenuDto> topMenuDtoList=new ArrayList<>();
        List<AdmMenu> menuList=admMenuRepository.findTopMenu(roleId);
        for(AdmMenu admMenu:menuList){
            TopMenuDto topMenuDto=new TopMenuDto();
            BeanUtils.copyProperties(admMenu,topMenuDto);
            topMenuDtoList.add(topMenuDto);
        }
        return ResponseDtoFactory.toSuccess(topMenuDtoList);
    }

    @Override
    public ResponseDto<List<LeftMenuDto>> leftMenu(Long pid) {
        List<LeftMenuDto> leftMenuDtoList=new ArrayList<>();
        List<AdmMenu> menuList=admMenuRepository.findLeftMenu(pid);
        for(AdmMenu admMenu:menuList){
            if(admMenu.getPid().equals(pid)) {
                LeftMenuDto current = new LeftMenuDto();
                BeanUtils.copyProperties(admMenu,current);
                leftMenuDtoList.add(getChildMenu(current,menuList));
            }
        }
        return ResponseDtoFactory.toSuccess(leftMenuDtoList);
    }

    @Override
    public PageResultDto<List<MenuListDto>> findByPage(final BasePageDto searchDto) {
        Pageable pageable=new PageRequest(searchDto.getPage()-1,searchDto.getLimit());

        //查询条件构造
        Specification<AdmMenu> spec = new Specification<AdmMenu>() {
            @Override
            public Predicate toPredicate(Root<AdmMenu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<String> name = root.get("title");
                cb.conjunction();
                Predicate p=null;
                if(!StringUtils.isEmpty(searchDto.getQueryParam())) {
                    p= cb.like(name, "%" + searchDto.getQueryParam() + "%");
                }
                return p;
            }
        };
        Page<AdmMenu> page=admMenuRepository.findAll(spec,pageable);
        List<MenuListDto> list=new ArrayList<>();
        for(AdmMenu role:page.getContent()){
            MenuListDto dto=new MenuListDto();
            BeanUtils.copyProperties(role,dto);
            list.add(dto);
        }
        return PageResultDtoFactory.toSuccess("查询成功",list,page.getTotalElements());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDto<Boolean> deleteById(List<Long> ids) {
        for(Long id:ids) {
            admMenuRepository.delete(id);
        }
        return ResponseDtoFactory.toSuccess("删除成功",Boolean.TRUE);
    }

    @Override
    public ResponseDto<List<MenuListDto>> findAll() {
        List<MenuListDto> list=new ArrayList<>();
        Iterable<AdmMenu> ite=admMenuRepository.findAll();
        for(AdmMenu menu:ite){
            MenuListDto dto=new MenuListDto();
            BeanUtils.copyProperties(menu,dto);
            list.add(dto);
        }
        return ResponseDtoFactory.toSuccess(list);
    }

    @Override
    public ResponseDto<Boolean> save(AdmMenuDto dto) {
        AdmMenu admMenu=EntityUtil.translate(dto,AdmMenu.class);
        admMenuRepository.save(admMenu);
        return ResponseDtoFactory.toSuccess("保存成功",Boolean.TRUE);
    }

    @Override
    public ResponseDto<Boolean> update(AdmMenuDto dto) {
        AdmMenu admMenu=admMenuRepository.findOne(dto.getId());
        if(admMenu!=null) {
            EntityUtil.copyPropertiesIgnoreNull(dto, admMenu);
            admMenuRepository.save(admMenu);
            return ResponseDtoFactory.toSuccess("更新成功",Boolean.TRUE);
        }else{
            return ResponseDtoFactory.toError("找不到对应的数据");
        }
    }

    private LeftMenuDto getChildMenu(LeftMenuDto current,List<AdmMenu> menuList){
        for(AdmMenu subMenu:menuList){
            if(subMenu.getPid().equals(current.getId())){
                LeftMenuDto subMenuDto=new LeftMenuDto();
                BeanUtils.copyProperties(subMenu,subMenuDto);
                current.getChildren().add(subMenuDto);
                getChildMenu(subMenuDto,menuList);
            }
        }
        return current;
    }
}
