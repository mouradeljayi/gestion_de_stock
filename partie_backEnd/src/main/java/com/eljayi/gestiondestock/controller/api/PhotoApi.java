package com.eljayi.gestiondestock.controller.api;

import com.flickr4java.flickr.FlickrException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.eljayi.gestiondestock.utils.Constants.APP_ROOT;

@Tag(name = "photos")
public interface PhotoApi {

    @PostMapping( APP_ROOT + "/photos/{id}/{title}/{context}")
    Object savePhoto(String context, Integer id, @RequestPart("file") MultipartFile photo, String title) throws IOException, FlickrException;
}
