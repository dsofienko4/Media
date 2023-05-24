package com.cinema.content.service.back.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record ProfileResponseDtoInternal(long accountId,
                                         String firstName,
                                         String lastName,
                                         @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
                                         LocalDate birthday
                                         ) {}
