package com.dcits.govsbu.sd.taxbankportal.excel.mapper;


import com.dcits.govsbu.sd.taxbankportal.excel.model.TbEntFinanceNews;
import org.springframework.stereotype.Repository;

@Repository
public interface TbEntFinanceNewMappers {
    int deleteByPrimaryKey(Integer id);

    int insert(TbEntFinanceNews record);

    int insertSelective(TbEntFinanceNews record);

    TbEntFinanceNews selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbEntFinanceNews record);

    int updateByPrimaryKey(TbEntFinanceNews record);
}