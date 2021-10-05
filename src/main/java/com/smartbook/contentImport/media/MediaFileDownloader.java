package com.smartbook.contentImport.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class MediaFileDownloader {
    private String filePathInt;
    private String filePathExt;


    public byte[] makeBinaryFile(String urlFromDb) throws IOException {
        String fileName = urlFromDb.substring((urlFromDb.lastIndexOf("/") + 1));
        String toStorageFile = filePathInt + filePathExt + fileName;
        FileUtils.copyURLToFile(new URL(urlFromDb), new File(toStorageFile));
        byte[] fileBinaryDb = Files.readAllBytes(Paths.get(toStorageFile));
        System.out.println("fileBinaryDb = " + fileBinaryDb);
        return fileBinaryDb;
    }

    public String makeStoragePath(String fromFile) {
        String fileName = fromFile.substring((fromFile.lastIndexOf("/") + 1));
        String filePathDb = filePathExt + fileName;
        System.out.println("filePathDb = " + filePathDb);
        return filePathDb;
    }
}
