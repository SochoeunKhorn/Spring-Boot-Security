package com.sochoeun.securityjwt.controller.user;

import com.sochoeun.securityjwt.model.BaseResponse;
import com.sochoeun.securityjwt.model.request.UserRequest;
import com.sochoeun.securityjwt.model.response.UserResponse;
import com.sochoeun.securityjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.sochoeun.securityjwt.constant.constant.PHOTO_DIRECTORY;
import static org.springframework.util.MimeTypeUtils.IMAGE_JPEG_VALUE;
import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private BaseResponse baseResponse;
    @GetMapping()
    public ResponseEntity<?> getUsers(@RequestParam(required = false) String firstname){
        List<UserResponse> users = userService.getUsers(firstname);
        baseResponse = new BaseResponse();
        baseResponse.success(users);
        return ResponseEntity.ok(baseResponse);
    }

    @PostMapping("/disable/{userId}")
    public ResponseEntity<?> disableUser(@PathVariable Integer userId){
        userService.disableUser(userId);
        return ResponseEntity.ok("User ID: %s disabled".formatted(userId));
    }

    @PostMapping("/enable/{userId}")
    public ResponseEntity<?> enableUser(@PathVariable Integer userId){
        userService.enableUser(userId);
        return ResponseEntity.ok("User ID: %s enabled".formatted(userId));
    }

    @PostMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Integer userId,@RequestBody UserRequest request){
        userService.updateUser(userId,request);
        return ResponseEntity.ok("User ID: %s updated".formatted(userId));
    }

    @PutMapping("/update/profile")
    public ResponseEntity<String> updateUserProfile(@RequestParam Integer userId, @RequestParam MultipartFile file){
        String profile = userService.uploadProfile(userId, file);
        return ResponseEntity.ok().body(profile);
    }

    @GetMapping(path = "/profile/{filename}",produces = {IMAGE_PNG_VALUE,IMAGE_JPEG_VALUE})
    public byte[] getProfile(@PathVariable("filename") String filename) throws Exception{
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }

}
