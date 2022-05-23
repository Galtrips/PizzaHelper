/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tppizza;

import com.sun.xml.internal.ws.api.pipe.Fiber;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tppizza.Pizza;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.plaf.ComboBoxUI;

/**
 *
 * @author Brice Effantin
 */
public class MaFenetre extends JFrame implements ActionListener {

    private ArrayList<Pizza> listePizzas = new ArrayList();
    private JPanel panel;
    private String[] priceList = new String[]{"0","1","2","3","4","5","6","7","8","9"}; 
   
    private ArrayList<JComboBox<String>> price = new ArrayList<>();
    Double prixTotal = new Double(0);
    
    private JButton RAZ;
    private JButton Calc;
    
    private JLabel printPrice;
    
    private JMenuBar menu;
    private JMenu onglet;
    private JMenuItem add;
    private JMenuItem del;
    private JMenuItem edit;
    
    private JButton accept;
    private JButton refuse;
    private JComboBox box;
    
    private JButton accept2;
    private JTextField nomPizza;
    private JTextField ingrediantPizza;
    private JTextField tarifPizza;
    
    public MaFenetre() {
        this.setTitle("Pizza helper");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RAZ = new JButton("RAZ");
        Calc = new JButton("Calculer");
        menu = new JMenuBar();
        onglet = new JMenu("Pizza");
        
        add = new JMenuItem("Ajouter");
        del = new JMenuItem("Supprimer");
        edit = new JMenuItem("Modifier");
        
        accept = new JButton("Valider");
        accept.setBackground(Color.green);
            
        refuse = new JButton("Annuler");
        refuse.setBackground(Color.red);
        
        accept2 = new JButton("Valider");
        accept2.setBackground(Color.green);
        
        onglet.add(add);
        onglet.add(del);
        onglet.add(edit);
        
        menu.add(onglet);
        
        this.setJMenuBar(menu);
        initialisation();
        
        Calc.addActionListener(this);
        RAZ.addActionListener(this);
        
        add.addActionListener(this);
        del.addActionListener(this);
        edit.addActionListener(this);
        accept.addActionListener(this);
        refuse.addActionListener(this);
        accept2.addActionListener(this);
    }
    
    private void initialisation() {
        
        this.panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.darkGray);
        
        printPrice = new JLabel("0.0 €");
        printPrice.setForeground(Color.white);
        
        GridBagConstraints cont = new GridBagConstraints();
        cont.fill=GridBagConstraints.BOTH;
        
        cont.gridx=2;
        cont.gridy=0;
        cont.weightx = 1;
        RAZ.setBackground(Color.red);

        panel.add(RAZ, cont);
        
        cont.gridx=2;
        cont.gridy=2;
        cont.gridheight = 2;
        Calc.setBackground(Color.green);

        panel.add(Calc, cont);
        
        price.removeAll(price);
        cont.gridheight = 1;
        cont.weightx = 1;
        int i = 0;
        for (Pizza p : this.listePizzas) {
            JLabel label = new JLabel(p.getNom().toUpperCase() + " " + p.getIngredients() + " " + p.getTarif());
            label.setForeground(Color.white);
            cont.gridx = 0;
            cont.gridy = i;
            cont.insets = new Insets(0,15,0,0);
            panel.add(label, cont);
            price.add(new JComboBox<>(priceList));
            cont.gridx = 1;
            price.get(i).setBackground(Color.darkGray);
            price.get(i).setForeground(Color.white);
            panel.add(price.get(i),cont);
            i++;
        }
        
        cont.gridx = 2;
        cont.gridy = 4;
        cont.insets = new Insets(0,30,0,0);
        panel.add(printPrice, cont);
        
        this.setContentPane(panel);
    }
    /**
     * Ajoute une pizza à la liste
     *
     * @param nomPizza
     * @param ingredients
     * @param tarif
     */
    public void ajoutPizza(String nomPizza, String ingredients, double tarif) {
        listePizzas.add(new Pizza(nomPizza, ingredients, tarif));
        initialisation();
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Calc) {
            int j = 0;
            for (JComboBox<String> i : this.price) {   
                this.prixTotal += Integer.parseInt((String) i.getSelectedItem()) * listePizzas.get(j).getTarif();
                j++;
            }       
            this.printPrice.setText(prixTotal.toString() + " €");
            this.prixTotal = 0.0;
        }
        if (e.getSource() == RAZ) {
            int j = 0;
            for (JComboBox<String> i : this.price) {   
                i.setSelectedIndex(0);
            }
            this.printPrice.setText("0.0 €");
        }
        if (e.getSource() == add) {
            
            JPanel addWindows = new JPanel();
            addWindows.setBackground(Color.darkGray);
            GridBagConstraints constr = new GridBagConstraints();
            addWindows.setLayout(new GridBagLayout());
                    
            constr.fill=GridBagConstraints.BOTH;
            
            
            JLabel label = new JLabel("Nom de la pizza :");
            label.setForeground(Color.white);
            
            constr.gridx=0;
            constr.gridy=0;
            constr.gridwidth=2;
            addWindows.add(label,constr);
            
            nomPizza = new JTextField();
            
            constr.gridx=0;
            constr.gridy=1;
            
            addWindows.add(nomPizza,constr);
            
            JLabel label2 = new JLabel("Ingrédiants de la pizza :");
            label2.setForeground(Color.white);
            
            constr.gridx=0;
            constr.gridy=2;
            constr.gridwidth=2;
            addWindows.add(label2,constr);
            
            ingrediantPizza = new JTextField();
            
            constr.gridx=0;
            constr.gridy=3;
            addWindows.add(ingrediantPizza,constr);
            
            JLabel label3 = new JLabel("Tarif de la pizza :");
            label3.setForeground(Color.white);
            
            constr.gridx=0;
            constr.gridy=4;
            constr.gridwidth=2;
            addWindows.add(label3,constr);
            
            tarifPizza = new JTextField();
            
            constr.gridx=0;
            constr.gridy=5;
            
            addWindows.add(tarifPizza,constr);
            
            constr.gridx=1;
            constr.gridy=6;
            constr.gridwidth=1;
            addWindows.add(accept2,constr);
            
            constr.gridx=0;
            constr.gridy=6;
            addWindows.add(refuse,constr);
            
            this.setContentPane(addWindows);
            this.pack();
            
        }
        if (e.getSource() == del) {
            JPanel delWindows = new JPanel();
            delWindows.setBackground(Color.darkGray);
            GridBagConstraints constr = new GridBagConstraints();
            delWindows.setLayout(new GridBagLayout());
                    
            constr.fill=GridBagConstraints.BOTH;
            
            ArrayList<String> nomPizza = new ArrayList<>();
            for (Pizza i : this.listePizzas) {
                nomPizza.add(i.getNom());
            }
            
            JLabel label = new JLabel("Choix de la pizza :");
            label.setForeground(Color.white);
            
            constr.gridx=0;
            constr.gridy=0;
            constr.gridwidth=2;
            delWindows.add(label,constr);
            
            box = new JComboBox(nomPizza.toArray());
            
            constr.gridx=0;
            constr.gridy=1;
            
            delWindows.add(box,constr);
            
            constr.gridx=1;
            constr.gridy=2;
            constr.gridwidth=1;
            delWindows.add(accept,constr);
            
            constr.gridx=0;
            constr.gridy=2;
            delWindows.add(refuse,constr);
            
            this.setContentPane(delWindows);
            this.pack();
        }
        if (e.getSource() == edit) {    
            
        }
        if (e.getSource() == refuse) {    
            this.setContentPane(panel);
            this.pack();
        }
        if (e.getSource() == accept) {
            String selectedPizza = (String) box.getSelectedItem();
  
            for (int i = 0; i < this.listePizzas.size(); i++) {
                if (listePizzas.get(i).getNom() == selectedPizza) {
                    listePizzas.remove(i);
                }
            }
            initialisation();
            this.setContentPane(panel);
            this.pack();
        }
        if (e.getSource() == accept2) {
            
            this.ajoutPizza(nomPizza.getText(), ingrediantPizza.getText(), Integer.parseInt(tarifPizza.getText()));
            
            initialisation();
            this.setContentPane(panel);
            this.pack();
        }
    }   
}
