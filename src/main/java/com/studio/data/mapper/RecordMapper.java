package com.studio.data.mapper;

import com.studio.data.model.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * DAO（数据访问层）：只定义「要做什么」，SQL 写在 mapper/RecordMapper.xml。
 * MyBatis 在启动时为本接口生成实现类。
 */
@Mapper
public interface RecordMapper {

    /** 插入一条记录，返回受影响行数 */
    int insert(Record record);

    /** 按 id 查询 */
    Record selectById(@Param("id") Long id);

    /** 分页查询（page/size 由 Service 计算 offset） */
    List<Record> selectPage(@Param("offset") int offset, @Param("size") int size);

    /** 总条数（分页需要） */
    long countAll();

    /** 按标题精确查询（走 idx_records_title 索引） */
    List<Record> selectByTitle(@Param("title") String title);
}
