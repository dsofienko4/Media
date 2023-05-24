package com.cinema.content.service.client.facade.impl;

import com.cinema.content.service.back.dto.request.MediaRequestDto;
import com.cinema.content.service.back.dto.response.MediaBodyResponseDto;
import com.cinema.content.service.back.dto.response.MediaBodyResponseDtoHelper;
import com.cinema.content.service.back.dto.response.MediaTitleResponseDto;
import com.cinema.content.service.back.feign.MediaFeignClientComment;
import com.cinema.content.service.back.feign.MediaFeignClientProfile;
import com.cinema.content.service.back.feign.MediaFeignClientStoredFile;
import com.cinema.content.service.back.service.MediaService;
import com.cinema.content.service.client.facade.MediaServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MediaServiceFacadeImpl implements MediaServiceFacade {

    private final MediaService mediaService;

    private final MediaFeignClientComment mediaFeignClientComment;
    private final MediaFeignClientProfile mediaFeignClientProfile;
    private final MediaFeignClientStoredFile mediaFeignClientStoredFile;

    @Override
    public Page<MediaTitleResponseDto> pageMediaDtoToCategory(PageRequest pageRequest, Long categoryId) {
        return mediaService.pageMediaDtoToCategory(pageRequest, categoryId);
    }

    @Override
    public void saveMedia(MediaRequestDto mediaRequestDto, Long accountId) {
        mediaService.saveMedia(mediaRequestDto, accountId);
    }

    @Override
    public void updateMedia(MediaRequestDto mediaRequestDto, Long accountId, Long mediaId) {
        mediaService.updateMedia(mediaRequestDto, accountId, mediaId);
    }

    @Override
    public void updateStatus(Long id) {
        mediaService.updateStatus(id);
    }

    @Override
    public void deleteMedia(Long id, Long accountId) {
        mediaService.deleteMedia(id, accountId);
    }

    @Override
    public boolean existMedia(Long id) {
        return mediaService.existMedia(id);
    }

    @Override
    public MediaBodyResponseDto getMediaById(Long id) {
        MediaBodyResponseDtoHelper dto = mediaService.getMediaById(id);

        return new MediaBodyResponseDto(dto.id(), dto.title(), mediaFeignClientStoredFile.getPreviewUrl(dto.previewId()),
                dto.htmlBody(), dto.date(), Math.toIntExact(mediaFeignClientComment.countComments(id)),
                mediaFeignClientProfile.getProfileByAccountId(dto.accountId()));
    }
}
