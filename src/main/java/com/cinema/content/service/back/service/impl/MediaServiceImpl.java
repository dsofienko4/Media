package com.cinema.content.service.back.service.impl;

import com.cinema.content.service.back.domain.Category;
import com.cinema.content.service.back.domain.Media;
import com.cinema.content.service.back.domain.enums.MediaStatus;
import com.cinema.content.service.back.dto.ProfileResponseDtoInternal;
import com.cinema.content.service.back.dto.request.MediaRequestDto;
import com.cinema.content.service.back.dto.response.MediaBodyResponseDto;
import com.cinema.content.service.back.dto.response.MediaBodyResponseDtoHelper;
import com.cinema.content.service.back.dto.response.MediaTitleResponseDto;
import com.cinema.content.service.back.exception.NotFoundEntityExceptional;
import com.cinema.content.service.back.exception.UnavailableFunctionException;
import com.cinema.content.service.back.feign.MediaFeignClientComment;
import com.cinema.content.service.back.mapping.MediaMapper;
import com.cinema.content.service.back.repository.MediaRepository;
import com.cinema.content.service.back.service.CategoryService;
import com.cinema.content.service.back.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Transactional
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;
    private final CategoryService categoryService;

    private final MediaFeignClientComment mediaFeignClientComment;

    @Override
    public Page<MediaTitleResponseDto> pageMediaDtoToCategory(PageRequest pageRequest, Long categoryId) {
        Page<Media> pageMedia;
        if (categoryId == null) {
            pageMedia = mediaRepository.findAllByStatusAndEnable(MediaStatus.VERIFIED, true, pageRequest);
        } else {
            pageMedia = mediaRepository.findAllByStatusAndEnableAndCategoryId(MediaStatus.VERIFIED, true, pageRequest,
                    categoryService.getCategoryById(categoryId));
        }
        Page<MediaTitleResponseDto> pageDto = pageMedia.map(new Function<Media, MediaTitleResponseDto>() {
            @Override
            public MediaTitleResponseDto apply(Media media) {
                return new MediaTitleResponseDto(media.getId(), media.getTitle(),
                        mediaMapper.convertToDto(media.getCategory()), media.getDate(),
                        Math.toIntExact(mediaFeignClientComment.countComments(media.getId())));
            }
        });
        return pageDto;
    }

    @Override
    public void saveMedia(MediaRequestDto mediaRequestDto, Long accountId) {
        Category category = categoryService.getCategoryById(mediaRequestDto.categoryId());
        Media media = new Media(category, LocalDate.now(), mediaRequestDto.title(), mediaRequestDto.htmlBody(), accountId, false, MediaStatus.EDITED);
        mediaRepository.save(media);
    }

    @Override
    public void updateMedia(MediaRequestDto mediaRequestDto, Long accountId, Long mediaId) {
        Optional<Media> mediaOptional = mediaRepository.findById(mediaId);
        if (mediaOptional.isEmpty()) {
            throw new NotFoundEntityExceptional("There is no such media");
        } else {
            Media media = mediaOptional.get();
            if (media.getAccountId().equals(accountId) && media.getStatus().equals(MediaStatus.EDITED)) {
                media.setCategory(categoryService.getCategoryById(mediaRequestDto.categoryId()));
                media.setTitle(mediaRequestDto.title());
                media.setHtmlBody(mediaRequestDto.htmlBody());
                mediaRepository.save(media);
            } else {
                throw new UnavailableFunctionException("This function is not available");
            }
        }

    }

    @Override
    public void updateStatus(Long id) {
        Optional<Media> mediaOptional = mediaRepository.findById(id);
        if (mediaOptional.isEmpty()) {
            throw new NotFoundEntityExceptional("There is no such media");
        } else {
            Media media = mediaOptional.get();
            media.setStatus(MediaStatus.WAIT_VERIFIED);
            mediaRepository.save(media);
        }
    }

    @Override
    public void deleteMedia(Long id, Long accountId) {
        Optional<Media> mediaOptional = mediaRepository.findById(id);
        if (mediaOptional.isEmpty()) {
            throw new NotFoundEntityExceptional("There is no such media");
        } else {
            Media media = mediaOptional.get();
            if (media.getAccountId().equals(accountId)) {
                media.setEnable(false);
                mediaRepository.save(media);
            } else {
                throw new UnavailableFunctionException("This function is not available");
            }
        }
    }

    @Override
    public boolean existMedia(Long id) {
        return mediaRepository.existsById(id);
    }

    @Override
    public MediaBodyResponseDtoHelper getMediaById(Long id) {
        return mediaRepository.getMediaDtoById(id);
    }
}

