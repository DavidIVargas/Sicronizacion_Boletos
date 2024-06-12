/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author davidvargas
 */
public class Evento {
    private int entradasDisponibles;

    public Evento(int entradasIniciales) {
        this.entradasDisponibles = entradasIniciales;
    }

    public synchronized boolean reservarEntradas(int cantidad) {
        if (entradasDisponibles >= cantidad) {
            entradasDisponibles -= cantidad;
            return true;
        }
        return false;
    }

    public synchronized int getEntradasDisponibles() {
        return entradasDisponibles;
    }
}
