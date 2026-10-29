package com.studio.data.model;

import jakarta.validation.constraints.NotBlank;

/** POST /api/records 请求体 */
public class RecordRequest {

    @NotBlank(message = "title 不能为空")
    private String title;

    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
