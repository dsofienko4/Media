package com.cinema.content.service.back.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "comment-service")
public interface MediaFeignClientComment {

    @GetMapping("/api/internal/media/{id}/comments/count")
    Long countComments(@PathVariable long id);

}