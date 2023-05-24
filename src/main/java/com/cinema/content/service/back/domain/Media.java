package com.cinema.content.service.back.domain;

import com.cinema.content.service.back.domain.enums.MediaStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;

import java.time.LocalDate;
import java.util.Objects;

import static java.sql.Types.LONGVARCHAR;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "medias")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull
    @CreationTimestamp
    private LocalDate date;

    @NotNull
    private String title;

    @Lob
    @JdbcTypeCode(LONGVARCHAR)
    @NotNull
    @Column(name = "html_body")
    private String htmlBody;

    @NotNull
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "preview_id")
    private Long previewId;

    @Column(name = "enable")
    private Boolean enable;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MediaStatus status;

    public Media(Category category, LocalDate date, String title, String htmlBody, Long accountId, Long previewId, Boolean enable, MediaStatus status) {
        this.category = category;
        this.date = date;
        this.title = title;
        this.htmlBody = htmlBody;
        this.accountId = accountId;
        this.previewId = previewId;
        this.enable = enable;
        this.status = status;
    }

    public Media(Category category, LocalDate date, String title, String htmlBody, Long accountId, Boolean enable, MediaStatus status) {
        this.category = category;
        this.date = date;
        this.title = title;
        this.htmlBody = htmlBody;
        this.accountId = accountId;
        this.enable = enable;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Media media = (Media) o;
        return id != null && Objects.equals(id, media.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
