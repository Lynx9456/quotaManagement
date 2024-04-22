package com.test.quota.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.quota.bean.QuotaRecords;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuotaRecordsMapper extends BaseMapper<QuotaRecords> {
    @Select("select \n" +
            "\t* \n" +
            "from quota_records ")
    List<QuotaRecords> selectAll();

    @Select("select * from quota_records where id = #{id}")
    List<QuotaRecords> queryById(int id);

    @Select("select * from quota_records where account_id = #{accountId}")
    List<QuotaRecords> queryByAccountId(int accountId);

    @Select("select * from quota_records where operator = #{operator}")
    List<QuotaRecords> queryByOperator(String operator);

    @Insert("insert into quota_records (gmt_create,gmt_modified,account_id,transaction_type,amount,quota_after,quota_type,operator,`desc`) values(#{gmtCreate},#{gmtModified},#{accountId},#{transactionType},#{amount},#{quotaAfter},#{quotaType},#{operator},#{desc});")
    int insert(QuotaRecords quotaRecords);

    @Delete("DELETE FROM quota_records WHERE id=#{id}")
    int deleteById(int id);

    @Delete("DELETE FROM quota_records WHERE account_id=#{accountId}")
    int deleteByName(int accountId);

    @Update("update quota_records set gmt_modified=#{gmtModified}, transaction_type=#{transactionType},amount=#{amount},quota_after=#{quotaAfter},quota_type=#{quotaType},operator=#{operator},`desc`=#{desc} where id=#{id}")
    boolean update(QuotaRecords quotaRecords);

}
