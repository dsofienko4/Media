package com.cinema.content.service.back.dto.response;


import java.time.LocalDate;

public record MediaTitleResponseDto(Long id, String title , CategoriesResponseDto category, LocalDate date, Integer countComment) {

}
// TODO        String mediaUrl - достать по обращению к fileStorage service по previewId пока не реализовывать, поставить TODO

