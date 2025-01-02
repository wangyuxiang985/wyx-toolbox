package com.wyx.toolbox.common.tools.entity;

import java.util.List;
import java.util.Objects;

/**
 * 菜单demo类
 *
 * @author wangyu
 */
public class Menu {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 父级id
     */
    private Long parentId;
    /**
     * 菜单名
     */
    private String name;
    /**
     * 子集菜单
     */
    private List<Menu> subMenus;

    /**
     * 是否可以被选择
     */
    private Boolean choose;

    public Menu() {
    }

    public Menu(Long id, Long parentId, String name, List<Menu> subMenus) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.subMenus = subMenus;
    }

    public Menu(Long id, Long parentId, String name, List<Menu> subMenus, Boolean choose) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.subMenus = subMenus;
        this.choose = choose;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<Menu> subMenus) {
        this.subMenus = subMenus;
    }

    public Boolean getChoose() {
        return choose;
    }

    public void setChoose(Boolean choose) {
        this.choose = choose;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", subMenus=" + subMenus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return id.equals(menu.id) && parentId.equals(menu.parentId) && name.equals(menu.name) && subMenus.equals(menu.subMenus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentId, name, subMenus);
    }
}
