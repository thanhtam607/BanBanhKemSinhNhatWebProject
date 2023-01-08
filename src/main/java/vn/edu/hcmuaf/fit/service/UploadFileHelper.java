package vn.edu.hcmuaf.fit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class UploadFileHelper {
    public static List<String> uploadFile(String UPLOAD_DIR, HttpServletRequest request) {
        List<String> fileNames = new ArrayList<String>();
        try {
            List<Part> parts = (List<Part>) request.getParts();
            for (Part part : parts) {
                if (part.getName().equalsIgnoreCase("upload")) {
                    String fileName = getFileName(part);
                    fileNames.add(fileName);

                    InputStream inputStream = null;
                    OutputStream outputStream = null;
                    System.out.println(Path.of(UPLOAD_DIR));
                    try {

                        File outputFilePath = new File(Path.of(UPLOAD_DIR)+ fileName);

                        inputStream = part.getInputStream();
                        outputStream = new FileOutputStream(outputFilePath);
                        System.out.println(outputFilePath.getAbsolutePath());
                        int read = 0;
                        final byte[] bytes = new byte[1024];
                        while ((read = inputStream.read(bytes)) != -1) {
                            outputStream.write(bytes, 0, read);
                        }
                    } catch (Exception ex) {
                        fileName = null;
                    } finally {
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    }
                }
            }
        } catch (Exception e) {
            fileNames = null;
        }
        return fileNames;
    }

    private static String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
