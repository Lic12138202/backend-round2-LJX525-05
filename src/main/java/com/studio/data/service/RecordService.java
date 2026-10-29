package com.studio.data.service;

import com.studio.data.dto.PageResult;
import com.studio.data.exception.BizException;
import com.studio.data.mapper.RecordMapper;
import com.studio.data.model.Record;
import com.studio.data.model.RecordRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层：参数校验、编排 DAO、组装分页结果。
 * 不写 HTTP、不写 SQL。
 */
@Service
public class RecordService {

    private static final Logger log = LoggerFactory.getLogger(RecordService.class);

    private final RecordMapper recordMapper;

    public RecordService(RecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }

    /** 存储一条记录，返回自增 id */
    public Record create(RecordRequest request) {
        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw BizException.badRequest("title 不能为空");
        }
        if (request.getTitle().length() > 100) {
            throw BizException.badRequest("title 长度不能超过 100");
        }

        Record record = new Record();
        record.setTitle(request.getTitle().trim());
        record.setContent(request.getContent());

        int rows = recordMapper.insert(record);
        if (rows != 1) {
            throw new IllegalStateException("插入失败，受影响行数=" + rows);
        }
        log.info("已入库: id={}, title={}", record.getId(), record.getTitle());
        return record;
    }

    /** 按 id 查询 */
    public Record getById(Long id) {
        Record record = recordMapper.selectById(id);
        if (record == null) {
            throw BizException.notFound("记录不存在: id=" + id);
        }
        return record;
    }

    /** 分页查询（page 从 1 开始） */
    public PageResult<Record> page(int page, int size) {
        if (page < 1) {
            throw BizException.badRequest("page 必须 >= 1");
        }
        if (size < 1 || size > 100) {
            throw BizException.badRequest("size 必须在 1~100 之间");
        }
        int offset = (page - 1) * size;
        List<Record> list = recordMapper.selectPage(offset, size);
        long total = recordMapper.countAll();
        return new PageResult<>(list, total, page, size);
    }

    /** 按标题查（索引场景） */
    public List<Record> listByTitle(String title) {
        return recordMapper.selectByTitle(title);
    }
}
