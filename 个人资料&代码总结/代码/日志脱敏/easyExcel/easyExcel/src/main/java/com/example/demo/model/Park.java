package com.example.demo.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;

/**
 * park
 * @author 
 */
public class Park implements Serializable {

    @ExcelProperty(index = 0,value = "公园ID")
    private String id;

    @ExcelProperty(index = 1,value = "公园名字")
    private String name;

    @ExcelProperty(index = 2,value = "城市名字")
    private String city;

    @ExcelProperty(index = 3,value = "面积")
    private String area;

    @ExcelIgnore
    private String miaoshu;

    /**
     * 公园的树
     */
    @ExcelProperty(index = 4,value = "树名")
    private String trees;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMiaoshu() {
        return miaoshu;
    }

    public void setMiaoshu(String miaoshu) {
        this.miaoshu = miaoshu;
    }

    public String getTrees() {
        return trees;
    }

    public void setTrees(String trees) {
        this.trees = trees;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Park other = (Park) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
            && (this.getMiaoshu() == null ? other.getMiaoshu() == null : this.getMiaoshu().equals(other.getMiaoshu()))
            && (this.getTrees() == null ? other.getTrees() == null : this.getTrees().equals(other.getTrees()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getMiaoshu() == null) ? 0 : getMiaoshu().hashCode());
        result = prime * result + ((getTrees() == null) ? 0 : getTrees().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", city=").append(city);
        sb.append(", area=").append(area);
        sb.append(", miaoshu=").append(miaoshu);
        sb.append(", trees=").append(trees);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Park(String id, String name, String city, String area, String miaoshu, String trees) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.area = area;
        this.miaoshu = miaoshu;
        this.trees = trees;
    }
}