package com.smoms.mapper;

import com.smoms.pojo.Provider;
import org.apache.ibatis.annotations.*;

import java.util.List;
//供应商SQL
public interface ProviderMapper {
    @Select("select * from smbms_provider")
    List<Provider> selAllProvider();
//根据供应商id查找账单信息 主要用来查看详细信息展示
    @Select("select * from smbms_provider where id=#{providerId}")
    Provider selProviderById(@Param("providerId") int providerId);

    @Insert("insert into smbms_provider (proCode,proName,proDesc,proContact,proPhone,proAddress,proFax,createdBy,creationDate) values" +
            " (#{provider.proCode},#{provider.proName},#{provider.proDesc},#{provider.proContact},#{provider.proPhone},#{provider.proAddress},#{provider.proFax},#{provider.createdBy},#{provider.creationDate})")
    int insProvider(@Param("provider") Provider provider);
//条件查询  根据供应商编号或供应商名称模糊查询
    @Select("<script>" +
            "select * from smbms_provider" +
            "<where>" +
            "<if test='proCode!=null'>" +
            "proCode like concat('%',#{proCode},'%') and " +
            "</if>" +
            "<if test='proName!=null'>" +
            "proName like concat('%',#{proName},'%')" +
            "</if>" +
            "</where>" +
            "</script>")
    List<Provider> selProviderByTerm(@Param("proCode") String proCode, @Param("proName") String proName);

    @Delete("delete from smbms_provider where id=#{id}")
    boolean delProviderById(@Param("id") Integer id);
    //根据id修改供应商信息
    @Update("update smbms_provider set proCode=#{pro.proCode},proName=#{pro.proName},proDesc=#{pro.proDesc},proContact=#{pro.proContact},proPhone=#{pro.proPhone},proAddress=#{pro.proAddress},proFax=#{pro.proFax},modifyBy=#{pro.modifyBy},modifyDate=#{pro.modifyDate} where id=#{id}")
    int updProviderById(@Param("pro") Provider pro, @Param("id") int proId);
}
