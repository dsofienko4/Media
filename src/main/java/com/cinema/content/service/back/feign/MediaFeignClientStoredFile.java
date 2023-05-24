package com.cinema.content.service.back.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "file-service")
public interface MediaFeignClientStoredFile {

    @GetMapping("/api/internal/url")
    String getPreviewUrl(@RequestHeader("preview_id") Long previewId);
}
