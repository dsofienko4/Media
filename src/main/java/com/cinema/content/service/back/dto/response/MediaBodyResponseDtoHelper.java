package com.cinema.content.service.back.dto.response;

import java.time.LocalDate;

public record MediaBodyResponseDtoHelper(Long id,
                                         String title,
                                         String htmlBody,
                                         LocalDate date,
                                         Long accountId,
                                         Long previewId) {}
