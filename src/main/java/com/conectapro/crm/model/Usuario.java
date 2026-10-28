package com.conectapro.crm.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public class Usuario {
    private static final String REGEX_EMAIL = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
    private static final int MAX_PASSWORD_LENGTH = 12;
    private static final int MIN_PASSWORD_LENGTH = 8;

    private LocalDateTime fechaActivacion;
    private String idUsuario;
    private String idOrganizacion;
    private String nombre;
    private String apellido;
    private String email;
    private String passwordHash; //Guarda el hash de la contraseña
    private String telf;
    private String rol; //FALTA DEFINIR ROLES
    private int permisos; //Crear ENUM PERMISOS
    private LocalDateTime fechaBaja;
    private boolean activo;

    //CONSTRUCTOR vacío
    public Usuario(){
        this.idUsuario = java.util.UUID.randomUUID().toString();
        this.activo = true;
        this.fechaActivacion = LocalDateTime.now();
        //FALTA ROL
    }

    //CONSTRUCTOR reconstruir BD (recibe el passwordHash sin volver a encriptar)
    public Usuario(LocalDateTime fechaActivacion, String idUsuario, String idOrganizacion, String nombre, String apellido, String email, String passwordHash, String telf, String rol, int permisos, LocalDateTime fechaBaja, boolean activo) {
        if(idOrganizacion == null  || idOrganizacion.isBlank()) { //Regex email
            throw new IllegalArgumentException("Indique organización a la que pertenece");
        }

        validarEmail(email);

        this.fechaActivacion = LocalDateTime.now();
        this.idUsuario = idUsuario;
        this.idOrganizacion =  idOrganizacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.passwordHash = passwordHash;
        this.telf = telf;
        this.rol = rol;
        this.permisos = permisos;
        this.fechaBaja = fechaBaja;
        this.activo = activo;
    }

    //CONSTRUCTOR registro de nuevos usuarios
    public Usuario(LocalDateTime fechaActivacion, String idUsuario, String idOrganizacion, String nombre, String apellido, String email, String plainTextPassword, String telf, String rol, int permisos, boolean activo) {
        if(idOrganizacion == null  || idOrganizacion.isBlank()) { //Regex email
            throw new IllegalArgumentException("Indique organización a la que pertenece");
        }

        validarEmail(email);

        this.fechaActivacion = LocalDateTime.now();
        this.idUsuario = java.util.UUID.randomUUID().toString();
        this.idOrganizacion =  idOrganizacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.passwordHash = PasswordUtils.generarHash(plainTextPassword); //Sacamo el hash
        this.telf = telf;
        //this.rol = (rol != null) ? rol : "Usuario";
        this.permisos = permisos; //FALTA NIVEL DE PERMISOS
        this.activo = activo;
    }

    //Validación de email
    private void validarEmail(String email) {
        if(email == null || email.isBlank() || !email.matches(REGEX_EMAIL)) {
            throw new IllegalArgumentException("El formato del correo es inválido. Revise nuevamente sus datos ingresados.");
        }
    }

    //GETTERS Y SETTERS
    public LocalDateTime getFechaActivacion() { return fechaActivacion; }
    public void setFechaActivacion(LocalDateTime fechaActivacion) { this.fechaActivacion = fechaActivacion; }

    public String getIdUsuario() { return idUsuario; }

    public String getIdOrganizacion() { return idOrganizacion; }
    //En caso de transferencia de usuario
    public void setOrganiacion(String idOrganizacion) {
        if(idOrganizacion == null || idOrganizacion.isBlank()){
            throw new IllegalArgumentException("La ID de la organización no puede ser nula.");
        }
        this.idOrganizacion = idOrganizacion;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        validarEmail(email);
        this.email = email;
    }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setPassword(String plainTextPassword) { this.passwordHash = PasswordUtils.generarHash(plainTextPassword); }

    public String getTelf() {
        return telf;
    }

    public void setTelf(String telf) {
        this.telf = telf;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getPermisos() {
        return permisos;
    }

    public void setPermisos(int permisos) {
        this.permisos = permisos;
    }

    public LocalDateTime getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDateTime fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /*
    //EQUALS
    @Override
    public boolean equals(Object o){
        if(o == null || o.getClass() != getClass()) {return false;}
        if(this == o) {return true;}
        Organizacion oZ = (Organizacion) o;
        return Objects.equals(idOrganizacion, oZ.idOrganizacion);
    }

    //HASHCODE
    @Override
    public int hashCode() {
        return Objects.hash(idOrganizacion);
    }

    //TOSTRING
    @Override
    public String toString() {
        return "Organización{" +
                "idOrganizacion=" + idOrganizacion +
                ", nombre=" + nombre +
                ", cif=" + cif + "}";
    }
    */
}
