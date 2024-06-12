/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package modelo;

import vista.InterfazGrafica;


/**
 *
 * @author davidvargas
 */
public class Inicio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Evento evento = new Evento(100);
        InterfazGrafica interfaz = new InterfazGrafica(evento);
        interfaz.setVisible(true);
    }
}
