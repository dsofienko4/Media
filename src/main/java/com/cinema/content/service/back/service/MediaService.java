package com.cinema.content.service.back.service;

import com.cinema.content.service.back.dto.request.MediaRequestDto;
import com.cinema.content.service.back.dto.response.MediaBodyResponseDtoHelper;
import com.cinema.content.service.back.dto.response.MediaTitleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface MediaService {

    Page<MediaTitleResponseDto> pageMediaDtoToCategory(PageRequest pageRequest, Long categoryId);

    void saveMedia(MediaRequestDto mediaRequestDto, Long accountId);

    void updateMedia(MediaRequestDto mediaRequestDto, Long accountId, Long mediaId);

    void updateStatus(Long id);

    void deleteMedia(Long id, Long accountId);

    boolean existMedia(Long id);

    MediaBodyResponseDtoHelper getMediaById(Long id);

}
