
package com.dmj.cli.mapper.gen;

import com.dmj.cli.domain.query.BaseQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GeneratorDao {
    List<Map<String, Object>> queryList(BaseQuery query);

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);
}
