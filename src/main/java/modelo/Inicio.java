/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package modelo;

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
        Thread thread = new Thread();
        Hilo hilo = new Hilo(evento);
        Usuario usuario1 = new Usuario(0, "Juan");
        Usuario usuario2 = new Usuario(01, "Pedro");
        Usuario usuario3 = new Usuario(02, "Daniel");
        Usuario usuario4 = new Usuario(03, "Juaquin");
        
//        for (int i=0; i>6; i++){
//            evento.reservarEntradas(usuario1);
//        }
        
        for (int i = 0; i<4; i++){
            evento.reservarEntradas(usuario2);
        }
        thread.start();
        
        System.out.println(evento.numeroEntradas());
    }
}
