package com.datuzi.supersoft.service.impl;

import com.datuzi.supersoft.dao.AdmRoleMenuRepository;
import com.datuzi.supersoft.dto.*;
import com.datuzi.supersoft.entity.AdmRoleMenu;
import com.datuzi.supersoft.enums.MenuType;
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
    private AdmMenuRepository admMenuRepository;
    @Autowired
    private AdmRoleMenuRepository admRoleMenuRepository;


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
    public ResponseDto<List<LeftMenuDto>> leftMenu(LeftMenuSearchDto searchDto) {
        List<LeftMenuDto> leftMenuDtoList=new ArrayList<>();
        List<AdmMenu> menuList=admMenuRepository.findLeftMenu(searchDto.getRoleId(),searchDto.getPid());
        for(AdmMenu admMenu:menuList){
            if(admMenu.getPid().equals(searchDto.getPid())) {
                LeftMenuDto current = new LeftMenuDto();
                BeanUtils.copyProperties(admMenu,current);
                leftMenuDtoList.add(getChildMenu(current,menuList));
            }
        }
        return ResponseDtoFactory.toSuccess(leftMenuDtoList);
    }

    @Override
    public ResponseDto<List<MenuTreeDto>> findMenuTree(Long roleId) {
        List<MenuTreeDto> list=new ArrayList<>();
        List<MenuTreeDto> list2=new ArrayList<>();
        List<AdmMenu> menuList= Lists.newArrayList(admMenuRepository.findAll());
        List<AdmRoleMenu> roleMenuList=admRoleMenuRepository.findMenuByRole(roleId);
        List<Long> menuIdList=new ArrayList<>();
        for(AdmRoleMenu roleMenu:roleMenuList){
            menuIdList.add(roleMenu.getMenuId());
        }
        List<AdmMenu> topMenuList=new ArrayList<>();
        List<AdmMenu> leftMenuList=new ArrayList<>();
        for(AdmMenu menu:menuList){
            if(MenuType.TOP.equals(menu.getType())){
                topMenuList.add(menu);
            }else{
                leftMenuList.add(menu);
            }
        }

        for(AdmMenu topMenu:topMenuList){
            MenuTreeDto current = new MenuTreeDto();
            current.setId(topMenu.getId());
            current.setPid(topMenu.getPid());
            current.setTitle(topMenu.getTitle());
            current.setValue(topMenu.getId());
            if(menuIdList.contains(topMenu.getId())) {
                current.setChecked(true);
            }
            list.add(current);
        }
        for(AdmMenu leftMenu:leftMenuList){
            MenuTreeDto current = new MenuTreeDto();
            current.setId(leftMenu.getId());
            current.setPid(leftMenu.getPid());
            current.setTitle(leftMenu.getTitle());
            current.setValue(leftMenu.getId());
            if(menuIdList.contains(leftMenu.getId())) {
                current.setChecked(true);
            }
            list2.add(getChildMenu(current,leftMenuList,menuIdList));
        }
        for(MenuTreeDto top:list){
            for(MenuTreeDto left:list2){
                if(left.getPid().equals(top.getId())){
                    top.setChecked(true);
                    top.getData().add(left);
                }
            }
        }

        return ResponseDtoFactory.toSuccess(list);
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
    public ResponseDto<MenuListDto> findById(Long id) {
        MenuListDto listDto=new MenuListDto();
        AdmMenu role=admMenuRepository.findOne(id);
        if(role!=null){
            BeanUtils.copyProperties(role,listDto);
            return ResponseDtoFactory.toSuccess(listDto);
        }else{
            return ResponseDtoFactory.toError("未找到对应的信息");
        }
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
            if(MenuType.LEFT.equals(subMenu.getType()) && subMenu.getPid().equals(current.getId())){
                LeftMenuDto subMenuDto=new LeftMenuDto();
                BeanUtils.copyProperties(subMenu,subMenuDto);
                current.getChildren().add(subMenuDto);
                getChildMenu(subMenuDto,menuList);
            }
        }
        return current;
    }

    private MenuTreeDto getChildMenu(MenuTreeDto current, List<AdmMenu> leftMenuList,List<Long> menuIdList){
        for(AdmMenu subMenu:leftMenuList){
            if(subMenu.getPid().equals(current.getId())){
                MenuTreeDto subMenuDto=new MenuTreeDto();
                subMenuDto.setId(subMenu.getId());
                subMenuDto.setPid(subMenu.getPid());
                subMenuDto.setTitle(subMenu.getTitle());
                subMenuDto.setValue(subMenu.getId());
                if(menuIdList.contains(subMenuDto.getId())) {
                    subMenuDto.setChecked(true);
                }
                current.getData().add(subMenuDto);
                getChildMenu(subMenuDto,leftMenuList,menuIdList);
            }
        }
        return current;
    }
}
