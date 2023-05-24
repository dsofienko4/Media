package com.cinema.content.service.client.rest.out;

import com.cinema.content.service.back.dto.request.MediaRequestDto;
import com.cinema.content.service.back.dto.response.MediaBodyResponseDto;
import com.cinema.content.service.back.dto.response.MediaTitleResponseDto;
import com.cinema.content.service.client.facade.MediaServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MediaRestController {

    private final MediaServiceFacade mediaServiceFacade;

    @GetMapping("/medias/page/{pageNumber}")
    public Page<MediaTitleResponseDto> listMedia(@PathVariable Integer pageNumber,
                                                 @RequestParam(required = false, defaultValue = "10") Integer countItem,
                                                 @RequestParam(required = false) Long categoryId) {
        return mediaServiceFacade.pageMediaDtoToCategory(PageRequest.of(pageNumber, countItem,
                Sort.by("date").descending()), categoryId);
    }

    @PostMapping("/publicist/medias")
    public ResponseEntity<Void> saveMedia(@RequestBody MediaRequestDto mediaRequestDto,
                                                     @RequestHeader Long accountId) {
        mediaServiceFacade.saveMedia(mediaRequestDto, accountId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/publicist/medias/{id}")
    public ResponseEntity<Void> updateMedia(@RequestBody MediaRequestDto mediaRequestDto,
                                                       @RequestHeader Long accountId,
                                                       @PathVariable Long id) {
        mediaServiceFacade.updateMedia(mediaRequestDto, accountId, id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/publicist/medias/{id}/publish")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id) {
        mediaServiceFacade.updateStatus(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/publicist/medias/{id}")
    public ResponseEntity<Void> deleteMedia(@PathVariable Long id, @RequestHeader Long accountId) {
        mediaServiceFacade.deleteMedia(id, accountId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/medias/{id}/exist")
    public boolean existMedia(@PathVariable Long id) {
        return mediaServiceFacade.existMedia(id);
    }

    @GetMapping("/medias/{id}")
    public ResponseEntity<MediaBodyResponseDto> getMedia(@PathVariable Long id) {
        return ResponseEntity.ok(mediaServiceFacade.getMediaById(id));
    }
}
