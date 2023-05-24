package com.cinema.content.service.back.dto.response;

import com.cinema.content.service.back.dto.ProfileResponseDtoInternal;

import java.time.LocalDate;

public record MediaBodyResponseDto(Long id,
                                   String title,
                                   String previewUrl,
                                   String htmlBody,
                                   LocalDate date,
                                   Integer countComment,
                                   ProfileResponseDtoInternal author) {}
