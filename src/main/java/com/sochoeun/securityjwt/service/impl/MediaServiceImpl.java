package com.sochoeun.securityjwt.service.impl;

import com.sochoeun.securityjwt.model.Media;
import com.sochoeun.securityjwt.repository.MediaRepository;
import com.sochoeun.securityjwt.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.sochoeun.securityjwt.constant.constant.PHOTO_MEDIA_DIRECTORY;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {
    private final MediaRepository mediaRepository;

    @Override
    public Media createMedia(Media request) {
        return mediaRepository.save(request);
    }

    @Override
    public List<Media> getAllByContentId(Integer contentId) {
        return mediaRepository.findAllByContentId(contentId);
    }

    @Override
    public Media getMedia(Integer id) {
        return mediaRepository.findById(id).orElseThrow(()->new RuntimeException("Not Found"));
    }

    @Override
    public List<Media> getAllByMediaType(String type) {
        return mediaRepository.findAllByMediaType(type);
    }

    @Override
    public void deleteMedia(Integer mediaId) {
        getMedia(mediaId);
        mediaRepository.deleteById(mediaId);
    }

    @Override
    public String uploadMedia(Integer mediaId, MultipartFile file) {
        Media media = getMedia(mediaId);
        String mediaName = media.getName().toLowerCase();
        String mediaUrl = photoFunction.apply(mediaName,file);
        media.setMediaType(mediaUrl);
        mediaRepository.save(media);
        return mediaUrl;
    }

    private final Function<String,String> fileExtension =
            filename -> Optional.of(filename)
                    .filter(name -> name.contains("."))
                    .map(name
                            -> "." + name.substring(filename.lastIndexOf(".")+1)).orElse(".png");

    private final BiFunction<String,MultipartFile,String> photoFunction = (id, image) ->{
        try{
            Path fileStorageLocation = Paths.get(PHOTO_MEDIA_DIRECTORY).toAbsolutePath().normalize();
            if (!Files.exists(fileStorageLocation)){
                Files.createDirectories(fileStorageLocation);
            }

            Files.copy(
                    image.getInputStream(),
                    fileStorageLocation.resolve(id + fileExtension.apply(image.getOriginalFilename())), // filename
                    REPLACE_EXISTING);

            return ServletUriComponentsBuilder
                    .fromCurrentContextPath() // localhost:8080
                    .path("/api/medias/photo/" + id + fileExtension.apply(image.getOriginalFilename())).toUriString();
        }catch (Exception e){
            throw new RuntimeException("Unable to save image");
        }
    };
}
