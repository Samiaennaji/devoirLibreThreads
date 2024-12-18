package org.example.utils;

import org.example.models.OrderModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {

    private static final String INPUT_DIRECTORY ="data/Input";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static File[] listJsonFiles(){
        File inputDir = new File(INPUT_DIRECTORY);
        if(!inputDir.exists()|| !inputDir.isDirectory()){
            System.out.println("Le repertoire INPUT malo ? n existe pas ou pas valide .");
            return new File[0];
        }

        return inputDir.listFiles(file -> file.isFile() && file.getName().endsWith(".json"));
    }

    public static OrderModel readJsonFile(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(file, OrderModel.class);
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier JSON : " + file.getName());
            e.printStackTrace();
        }
        return null;
    }

    public static List<OrderModel> readOrders(String inputDir, String errorDir, String outputDir) {
        List<OrderModel> orders = new ArrayList<>();
        File directory = new File(inputDir);
        // Vérification si le répertoire existe et est valide
        if (!directory.exists() || !directory.isDirectory()) {
            System.err.println("Le répertoire INPUT n'existe pas ou est invalide.");
            return orders;
        }
// Vérification si le répertoire est vide
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".json"));
        if (files == null || files.length == 0) {
            System.out.println("Le répertoire INPUT est vide. Aucun fichier à traiter.");
            return orders; // Retourne une liste vide
        }
        for (File file : directory.listFiles((dir, name) -> name.endsWith(".json"))) {
            try {
                // Lire et convertir le fichier JSON en objet Order
                OrderModel order = objectMapper.readValue(file, OrderModel.class);
                orders.add(order);

                // Déplacer le fichier traité vers OUTPUT
                moveFile(file.toPath(), Path.of(outputDir, file.getName()));
                System.out.println("Fichier déplacé vers OUTPUT : " + file.getName());
            } catch (Exception e) {
                System.err.println("Erreur de lecture JSON pour le fichier : " + file.getName());
                e.printStackTrace();

                // Déplacer le fichier dans le répertoire ERROR
                moveFile(file.toPath(), Path.of(errorDir, file.getName()));
                System.out.println("Fichier déplacé vers ERROR : " + file.getName());
            }
        }
        return orders;
    }
    private static void moveFile(Path source, Path destination) {
        try {
            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Fichier déplacé vers : " + destination);
        } catch (IOException e) {
            System.err.println(" SOS ! Impossible de déplacer le fichier : " + source);
            e.printStackTrace();
        }
    }


}
