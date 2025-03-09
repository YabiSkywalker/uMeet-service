package io.skywalkerlabs.uMeet_service.Services.AmazonWebServices;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import io.skywalkerlabs.uMeet_service.Configuration.S3Config;
import io.skywalkerlabs.uMeet_service.Controllers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;




@Service
public class UploadToS3 {



    private final String S3_ARN = "arn:aws:s3:::umeetpictures";
    private final String PROFILE_PICTURE_BUCKET = "umeetpictures";
    private final  AmazonS3 s3Client;


    public UploadToS3(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }


    public String uploadPictures(String userId, MultipartFile picture) throws IOException {


        String key = "profilePictures/" + userId;
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(picture.getContentType());
        metadata.setContentLength(picture.getSize());

        s3Client.putObject(PROFILE_PICTURE_BUCKET, key, picture.getInputStream(), metadata);

        return s3Client.getUrl(PROFILE_PICTURE_BUCKET, key).toString();
    }

}
