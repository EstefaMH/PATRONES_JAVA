package com.losAtuendos.models;


public class Cliente extends Persona {

    private String mail;

    public Cliente(String id, String nombre, String direccion, String telefono, String mail) {
        super(id, nombre, direccion, telefono);
        this.mail = mail;

    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean validarDatos() {
        boolean isValid = validarDatosComunes();
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        if (mail == null || mail.isEmpty()) {
            System.out.println("El mail no puede estar vacío.");
            isValid = false;
        }

        if (!mail.matches(emailRegex)) {
            System.out.println("El mail no tiene la estrcutura adecuada ej: example@gmail.com");
            isValid = false;
        }

        return isValid;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("\nCreado internamente");
        System.out.println("Nombre: " + nombre);
        System.out.println("Dirección: " + direccion);
        System.out.println("Teléfono: " + telefono);
        System.out.println("mail " + mail);
    }
}
