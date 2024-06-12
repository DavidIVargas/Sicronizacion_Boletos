/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author davidvargas
 */
public class Hilo implements Runnable{
    private Evento evento;
    private Usuario usuario;

    public Hilo(Evento evento) {
        this.evento = evento;
    }

    @Override
    public void run() {
        for(int i=20; i<100;i--){
            evento.reservarEntradas(usuario);
        }
    }
    
    
}
