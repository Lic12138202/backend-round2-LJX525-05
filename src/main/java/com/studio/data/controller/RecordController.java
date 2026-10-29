package com.studio.data.controller;

import com.studio.data.dto.ApiResponse;
import com.studio.data.dto.PageResult;
import com.studio.data.model.Record;
import com.studio.data.model.RecordRequest;
import com.studio.data.service.RecordService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 数据存取接口。
 * POST /api/records        保存（请求体 JSON → MySQL）
 * GET  /api/records        分页读取（page/size）
 * GET  /api/records/{id}   按 id 读取
 * GET  /api/records/title  按标题查（走索引）
 */
@RestController
@RequestMapping("/api/records")
public class RecordController {

    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping
    public ApiResponse<Record> create(@Valid @RequestBody RecordRequest request) {
        Record record = recordService.create(request);
        return ApiResponse.ok("存储成功", record);
    }

    @GetMapping
    public ApiResponse<PageResult<Record>> page(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.ok("查询成功", recordService.page(page, size));
    }

    @GetMapping("/{id}")
    public ApiResponse<Record> get(@PathVariable Long id) {
        return ApiResponse.ok("查询成功", recordService.getById(id));
    }

    @GetMapping("/title")
    public ApiResponse<List<Record>> byTitle(@RequestParam String title) {
        return ApiResponse.ok("查询成功", recordService.listByTitle(title));
    }
}
