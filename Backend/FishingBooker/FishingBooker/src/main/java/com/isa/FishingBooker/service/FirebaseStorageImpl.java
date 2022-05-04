package com.isa.FishingBooker.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.isa.FishingBooker.model.Photo;
import com.isa.FishingBooker.service.interfaces.FirebaseStorage;

@Service
public class FirebaseStorageImpl implements FirebaseStorage {
	private FirebaseOptions options;
	private StorageOptions storageOptoins;
	public FirebaseStorageImpl() {

		FileInputStream serviceAccount;
		try {
			String currentDirectory = System.getProperty("user.dir");
			serviceAccount = new FileInputStream(currentDirectory+"/src/main/resources/firebaseToken.json");
			GoogleCredentials googleCredentials = GoogleCredentials.fromStream(serviceAccount);
			this.options = new FirebaseOptions.Builder()
					.setCredentials(googleCredentials).setStorageBucket("fishingbooker-c95ea.appspot.com").build();

			this.storageOptoins= StorageOptions.newBuilder().setCredentials(googleCredentials).build();
			FirebaseApp.initializeApp(options);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String save(MultipartFile file){
		Bucket bucket=StorageClient.getInstance().bucket();
        String imageName = generateFileName(file.getOriginalFilename());
        
        BlobId blobId = BlobId.of(bucket.getName(), imageName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
        
        try {
			storageOptoins.getService().create(blobInfo, file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
        return imageName;
    }
    
    private String generateFileName(String originalFileName) {
        return UUID.randomUUID().toString() + "." + getExtension(originalFileName);
    }

    private String getExtension(String originalFileName) {
        return StringUtils.getFilenameExtension(originalFileName);
    }

}
