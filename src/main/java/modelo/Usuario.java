/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import vista.InterfazGrafica;

/**
 *
 * @author davidvargas
 */
public class Usuario implements Runnable {
    private Evento evento;
    private String nombre;
    private Random random = new Random();
    private InterfazGrafica interfaz;

    public Usuario(Evento evento, String nombre, InterfazGrafica interfaz) {
        this.evento = evento;
        this.nombre = nombre;
        this.interfaz = interfaz;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            int entradasAReservar = random.nextInt(5) + 1;
            boolean exito;
            synchronized (evento) {
                exito = evento.reservarEntradas(entradasAReservar);
                interfaz.actualizarEstado(nombre, exito, entradasAReservar, evento.getEntradasDisponibles());
            }
            try {
                Thread.sleep(100);  // Simula el tiempo de espera entre intentos de reserva
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
