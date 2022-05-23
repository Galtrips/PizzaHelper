/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tppizza;

/**
 *
 * @author Brice Effantin
 */
public class ExoPizzas_etu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MaFenetre fen=new MaFenetre();
        fen.ajoutPizza("4 Frommages", "Tomate, Parmesan, Mozzarella, Cheddar, Gorgonzola", 8);
        fen.ajoutPizza("Diabolico", "Mozzarella, Tomate, Merguez, Poivrons", 7.5);
        fen.ajoutPizza("HAWAIENNE", "Mozzarella, Tomate, Jambon, Ananas", 9);
        fen.ajoutPizza("Reine", "Mozzarella, Tomate, Jambon, Champignons", 7);
        fen.ajoutPizza("Marguerite", "Tomate, Mozzarella", 6.5);
        
        fen.setVisible(true);
    }
    
}
