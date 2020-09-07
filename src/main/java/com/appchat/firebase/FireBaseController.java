package com.appchat.firebase;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class FireBaseController {
    private final FirebaseManager firebaseManager;
    @PostMapping(value = "/postImage")
    public Object postImage(@RequestParam(value = "image") MultipartFile file) throws IOException {
        return firebaseManager.uploadFile(file);
    }

    @GetMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@RequestParam(value = "fileName") String fileName){
        return firebaseManager.getImage(fileName);
    }

//    @GetMapping(value = "/getImageMyComputer", produces = MediaType.IMAGE_JPEG_VALUE)
//    public byte[] getImageMyComputer(
//            @RequestParam(value = "fileName") String fileName
//    )throws IOException{
//        String path = "/Users/ducnd/Desktop/bicycle/image_bicycle/"+fileName;
//        File file = new File(path);
//        byte[] bytesArray = new byte[(int) file.length()];
//
//        FileInputStream fis = new FileInputStream(file);
//        fis.read(bytesArray); //read file into bytes[]
//        fis.close();
//        return bytesArray;
//    }
}
