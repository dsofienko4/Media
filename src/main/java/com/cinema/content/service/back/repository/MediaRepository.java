package com.cinema.content.service.back.repository;

import com.cinema.content.service.back.domain.Category;
import com.cinema.content.service.back.domain.Media;
import com.cinema.content.service.back.domain.enums.MediaStatus;
import com.cinema.content.service.back.dto.response.MediaBodyResponseDtoHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    Page<Media> findAllByStatusAndEnable(MediaStatus status, Boolean enable, Pageable pageable);

    @Query("SELECT u FROM Media u WHERE u.status = :status AND u.enable = :enable AND u.category = :category")
    Page<Media> findAllByStatusAndEnableAndCategoryId(MediaStatus status, Boolean enable, Pageable pageable, Category category);

    @Query("SELECT new com.cinema.content.service.back.dto.response.MediaBodyResponseDtoHelper(" +
            "m.id, m.title, m.htmlBody, m.date, m.accountId, m.previewId) FROM Media m WHERE m.id = :id")
    MediaBodyResponseDtoHelper getMediaDtoById(Long id);
}
