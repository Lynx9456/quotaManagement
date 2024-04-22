package com.test.quota.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.quota.bean.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
    @Select("select \n" +
            "\t* \n" +
            "from account ")
    List<Account> selectAll();

    @Select("select * from account where id = #{id}")
    Account findById(int id);

    @Select("select * from account where account_name = #{accountName}")
    Account findByName(String accountName);

    @Insert("insert into account (gmt_create,gmt_modified,account_name,quota,quota_type,`desc`) values (#{gmtCreate},#{gmtModified},#{accountName},#{quota},#{quotaType},#{desc});")
    int insert(Account account);

    @Delete("DELETE FROM account WHERE id=#{id}")
    int deleteById(int id);

    @Delete("DELETE FROM account WHERE account_name=#{accountName}")
    int deleteByName(String accountName);

    @Update("update account set gmt_modified=#{gmtModified}, account_name=#{accountName},quota=#{quota},`desc`=#{desc},version=#{version}+1 where id=#{id} and version=#{version}")
    int update(Account account);

}
