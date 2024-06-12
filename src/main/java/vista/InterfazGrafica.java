/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import modelo.Evento;
import modelo.Usuario;

/**
 *
 * @author davidvargas
 */
public class InterfazGrafica extends JFrame {
    private JTextArea areaTexto;
    private JLabel[] etiquetasUsuarios;
    private JLabel etiquetaEntradas;
    private JButton botonIniciar;
    private Evento evento;
    private Thread[] usuarios;

    public InterfazGrafica(Evento evento) {
        this.evento = evento;
        setTitle("Sistema de Reserva de Entradas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelUsuarios = new JPanel();
        panelUsuarios.setLayout(new GridLayout(5, 1));
        etiquetasUsuarios = new JLabel[5];
        for (int i = 0; i < 5; i++) {
            etiquetasUsuarios[i] = new JLabel("Usuario " + (i + 1) + ": 0 entradas reservadas");
            panelUsuarios.add(etiquetasUsuarios[i]);
        }
        add(panelUsuarios, BorderLayout.WEST);

        etiquetaEntradas = new JLabel("Entradas disponibles: 100");
        add(etiquetaEntradas, BorderLayout.NORTH);

        botonIniciar = new JButton("Iniciar");
        botonIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarProceso();
            }
        });
        add(botonIniciar, BorderLayout.SOUTH);
    }

    public synchronized void actualizarEstado(String nombre, boolean exito, int cantidad, int entradasDisponibles) {
        areaTexto.append(nombre + (exito ? " reservó " : " no pudo reservar ") + cantidad + " entradas.\n");
        etiquetaEntradas.setText("Entradas disponibles: " + entradasDisponibles);
        for (int i = 0; i < 5; i++) {
            if (etiquetasUsuarios[i].getText().startsWith(nombre)) {
                String textoActual = etiquetasUsuarios[i].getText();
                int entradasReservadas = Integer.parseInt(textoActual.split(": ")[1].split(" ")[0]);
                if (exito) {
                    entradasReservadas += cantidad;
                }
                etiquetasUsuarios[i].setText(nombre + ": " + entradasReservadas + " entradas reservadas");
                break;
            }
        }
    }

    private void iniciarProceso() {
        usuarios = new Thread[5];

        for (int i = 0; i < 5; i++) {
            usuarios[i] = new Thread(new Usuario(evento, "Usuario " + (i + 1), this));
        }

        for (Thread usuario : usuarios) {
            usuario.start();
        }

        new Thread(() -> {
            for (Thread usuario : usuarios) {
                try {
                    usuario.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            actualizarEstado("Final", true, 0, evento.getEntradasDisponibles());
        }).start();
    }
}
