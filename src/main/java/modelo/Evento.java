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

    private int entradas = 100;
    private final Object look = new Object();
    private Map<Usuario, Integer> reservacionesPorUsuario;

    Usuario usuario = new Usuario(1, "Juan");

    public Evento(int entradas) {
        this.entradas = entradas;
        this.reservacionesPorUsuario = new HashMap<>();
    }

    public boolean reservarEntradas(Usuario usuario) {
        synchronized (look) {
            int reservacionesActuales = reservacionesPorUsuario.getOrDefault(usuario, 0);
            
            if(reservacionesActuales >=0){
                System.out.println("EL USUARIO " + usuario.getNombre()+ "(ID: " + usuario.getId() + ")" + "No puede hacer mas reservaciones");
                return false;
            }
            
            if (entradas > 0) {
                entradas--;
                reservacionesPorUsuario.put(usuario,reservacionesActuales + 1);
                System.out.println("Entrada reservada para: " + usuario.getNombre() + " (ID: " + usuario.getId() + "). Entradas restantes: " + numeroEntradas()/*+reservaMax */);
                return true;
            } else {
                System.out.println("Error al reservar la entrada porque ya no existen entradas disponibles" + usuario.getNombre() + " (ID: " + usuario.getId());
                return false;
            }
        }
    }

    public int numeroEntradas() {
        synchronized (look) {
            System.out.println("El numero de entradas restantes es: " + entradas);
            return entradas;
        }
    }

}
