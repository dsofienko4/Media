package com.cinema.content.service.back.feign;

import com.cinema.content.service.back.dto.ProfileResponseDtoInternal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "profile-service")
public interface MediaFeignClientProfile {

    @GetMapping("/api/internal/profile")
    ProfileResponseDtoInternal getProfileByAccountId(@RequestHeader("account_id") Long accountId);
}
