package com.saccess.eventAndDonation.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
@Service
public class CloudinaryService_Event {

    Cloudinary cloudinary;
    // Constructeur pour initialiser Cloudinary avec les clés d'API
    public CloudinaryService_Event(){
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("cloud_name", "docmlwlw2");
        valuesMap.put("api_key", "967611415747748");
        valuesMap.put("api_secret", "zjECP1HfQItu16E_8J0l1dU7F7c");
        cloudinary = new Cloudinary(valuesMap);
    }
    // Méthode pour convertir un objet MultipartFile en fichier
    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
    // Méthode pour télécharger un fichier sur Cloudinary
    public Map upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        // Spécifiez le type de ressource que vous souhaitez uploader : un IMG
        Map params = ObjectUtils.asMap(
                "resource_type", "auto" // Vous pouvez spécifier "auto" pour détecter automatiquement le type de ressource
        );
        // Upload du fichier sur Cloudinary avec les paramètres spécifiés
        Map result = cloudinary.uploader().upload(file, params);
        // Supprimer le fichier temporaire après le téléchargement
        if (!Files.deleteIfExists(file.toPath())) {
            throw new IOException("Failed to delete temporary file: " + file.getAbsolutePath());
        }
        return result;// Retourne le résultat de l'upload sur Cloudinary
    }

    // Méthode pour supprimer un fichier sur Cloudinary en utilisant son ID
    public Map delete(String id) throws IOException {
        return cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
    }
}
