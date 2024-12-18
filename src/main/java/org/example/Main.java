package org.example;

import org.example.models.OrderModel;
import org.example.utils.JsonReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Initialisation du projet...");

        String inputDir = "data/Input";
        String errorDir = "data/Error";
        String outputDir = "data/Output";


        List<OrderModel> orders = JsonReader.readOrders(inputDir, errorDir, outputDir);

        if (orders.isEmpty()) {
            System.out.println("Aucune commande valide n'a été trouvée. Verifier le file INPUT");
        } else {
            System.out.println("Commandes valides traitées :");
            for (OrderModel order : orders) {
                System.out.println("Commande ID : " + order.getId() + ", Montant : " + order.getAmount());
            }
        }

        System.out.println("Processus terminé !");
    }
}
