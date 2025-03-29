package io.skywalkerlabs.uMeet_service.Services.AmazonWebServices;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public class SimpleStorageService {



    private final String S3_ARN = "arn:aws:s3:::umeetpictures";
    private final String PROFILE_PICTURE_BUCKET = "umeetpictures";
    private final  AmazonS3 s3Client;


    public SimpleStorageService(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }


    @Async
    public String uploadProfilePicture(String userId, MultipartFile picture) throws IOException {

        String key = userId + "/profilePictures/" + picture.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(picture.getContentType());
        metadata.setContentLength(picture.getSize());
        s3Client.putObject(PROFILE_PICTURE_BUCKET, key, picture.getInputStream(), metadata);

        return s3Client.getUrl(PROFILE_PICTURE_BUCKET, key).toString();
    }

    @Async
    public String uploadPrivatePicture(String userId, MultipartFile picture) throws IOException {

        String filename = System.currentTimeMillis() + "_" + picture.getOriginalFilename();
        String key = userId + "/privatePictures/" + filename;
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(picture.getContentType());
        metadata.setContentLength(picture.getSize());
        s3Client.putObject(PROFILE_PICTURE_BUCKET, key, picture.getInputStream(), metadata);

        return s3Client.getUrl(PROFILE_PICTURE_BUCKET, key).toString();
    }

    @Async
    public ResponseEntity<String> deletePrivatePicture(String id, String filename) {
        String key = id + "/privatePictures/" + filename;


        s3Client.deleteObject(PROFILE_PICTURE_BUCKET, key);

        try {
            s3Client.deleteObject(PROFILE_PICTURE_BUCKET, key);
            return ResponseEntity.ok("✅ Successfully deleted picture: " + key);
        } catch (AmazonServiceException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body("❌ AWS Service Error: " + e.getErrorMessage());
        } catch (SdkClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ SDK Client Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Unexpected Error: " + e.getMessage());
        }

    }


    @Async
    public ResponseEntity<String> deleteProfilePicture(String id, String filename) {
        String key = id + "/profilePictures/" + filename;


        s3Client.deleteObject(PROFILE_PICTURE_BUCKET, key);

        try {
            s3Client.deleteObject(PROFILE_PICTURE_BUCKET, key);
            return ResponseEntity.ok("✅ Successfully deleted picture: " + key);
        } catch (AmazonServiceException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body("❌ AWS Service Error: " + e.getErrorMessage());
        } catch (SdkClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ SDK Client Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Unexpected Error: " + e.getMessage());
        }

    }

}
