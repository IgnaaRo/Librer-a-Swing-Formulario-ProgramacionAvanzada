package com.mycompany.formulario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Formulario extends JFrame {

    private JTextField txtNombre, txtApellido, txtDni, txtPasaporte, txtTelefono, txtCP, txtDomicilio;

    public Formulario() {
        setTitle("Carga de contacto");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(9, 2, 5, 5));

        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtDni = new JTextField();
        txtPasaporte = new JTextField();
        txtTelefono = new JTextField();
        txtCP = new JTextField();
        txtDomicilio = new JTextField();

        add(new JLabel("Nombre:"));
        add(txtNombre);

        add(new JLabel("Apellido:"));
        add(txtApellido);

        add(new JLabel("DNI:"));
        add(txtDni);

        add(new JLabel("Pasaporte:"));
        add(txtPasaporte);

        add(new JLabel("Teléfono:"));
        add(txtTelefono);

        add(new JLabel("Código Postal:"));
        add(txtCP);

        add(new JLabel("Domicilio:"));
        add(txtDomicilio);

        JButton btnValidar = new JButton("Validar");
        JButton btnLimpiar = new JButton("Limpiar");
        JButton btnCerrar = new JButton("Cerrar");

        add(btnValidar);
        add(btnLimpiar);
        add(btnCerrar);

        soloLetras(txtNombre, 20);
        soloLetras(txtApellido, 20);
        soloNumeros(txtDni, 8);
        soloNumeros(txtCP, 4);
        limitarCaracteres(txtDomicilio, 50);

        btnValidar.addActionListener(e -> validarFormulario());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnCerrar.addActionListener(e -> System.exit(0));
    }

    private void soloLetras(JTextField campo, int max) {
        campo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) || campo.getText().length() >= max) {
                    e.consume();
                }
            }
        });
    }

    private void soloNumeros(JTextField campo, int max) {
        campo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || campo.getText().length() >= max) {
                    e.consume();
                }
            }
        });
    }

    private void limitarCaracteres(JTextField campo, int max) {
        campo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (campo.getText().length() >= max) {
                    e.consume();
                }
            }
        });
    }

    private void validarFormulario() {

        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String dni = txtDni.getText();
        String pasaporte = txtPasaporte.getText();
        String telefono = txtTelefono.getText();
        String cp = txtCP.getText();
        String domicilio = txtDomicilio.getText();

      
        if (nombre.isEmpty() || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre y Apellido obligatorios");
            return;
        }

        
        if (!dni.isEmpty() && !pasaporte.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Solo puede ingresar DNI o Pasaporte, no ambos");
            return;
        }

        if (dni.isEmpty() && pasaporte.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar DNI o Pasaporte");
            return;
        }

        
        if (!dni.isEmpty()) {
            int valor = Integer.parseInt(dni);
            if (valor < 10000000 || valor > 60000000) {
                JOptionPane.showMessageDialog(this, "DNI fuera de rango");
                return;
            }
        }

      
        if (!pasaporte.isEmpty()) {
            if (!pasaporte.matches("^[A-Z][0-9]{8}$")) {
                JOptionPane.showMessageDialog(this, "Formato de pasaporte inválido");
                return;
            }
        }

        
        if (cp.length() != 4) {
            JOptionPane.showMessageDialog(this, "Código postal inválido");
            return;
        }

       
        if (telefono.length() < 7) {
            JOptionPane.showMessageDialog(this, "Teléfono inválido");
            return;
        }

        JOptionPane.showMessageDialog(this, "Formulario válido ✅");
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtDni.setText("");
        txtPasaporte.setText("");
        txtTelefono.setText("");
        txtCP.setText("");
        txtDomicilio.setText("");
    }

    public static void main(String[] args) {
        new Formulario().setVisible(true);
    }
}