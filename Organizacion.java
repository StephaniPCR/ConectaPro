package com.conectapro.crm.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Organizacion {
    private String idOrganizacion;
    private String nombre;
    private String razonSocial;
    private String cif;
    private String numTelf;
    private String direccion;
    private String email;
    private LocalDateTime fechaActivacion;
    private LocalDateTime fechaBaja;
    private boolean activo;

    //CONSTRUCTOR vacío necesario para Spring Boot (archivo JSON)
    public Organizacion() {
        this.idOrganizacion = java.util.UUID.randomUUID().toString(); //Universal Unique Identifier
        this.activo = true;
        this.fechaActivacion = LocalDateTime.now();
    }

    //CONSTRUCTOR con parámetros
    public Organizacion(String idOrganizacion, String nombre, String razonSocial, String cif, String numTelf, String direccion, String email, LocalDateTime fechaActivacion, boolean activo){
        if(razonSocial == null || cif == null) {
            throw new IllegalArgumentException("El CIF y la Razón Social son campos obligatorios.");
        };
        this.idOrganizacion = idOrganizacion;
        this.nombre = nombre;
        this.razonSocial = razonSocial;
        this.cif = cif;
        this.numTelf = numTelf;
        this.direccion = direccion;
        this.email = email;
        this.fechaActivacion = fechaActivacion;
        this.activo = activo;
    }

    //GETTERS Y SETTERS
    public String getIdOrganizacion() { return idOrganizacion; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre;}

    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }

    public String getCif() { return cif; }
    public void setCif(String cif) { this.cif = cif; }

    public String getNumTelf() { return numTelf; }
    public void setNumTelf(String numTelf) { this.numTelf = numTelf; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDateTime getFechaActivacion() { return fechaActivacion; }
    public void setFechaActivacion(LocalDateTime fechaActivacion) { this.fechaActivacion = fechaActivacion; }

    public LocalDateTime getFechaBaja() { return fechaBaja; }
    public void setFechaBaja(LocalDateTime fechaBaja) { this.fechaBaja = fechaBaja; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    //FIN GETTERS Y SETTERS

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
}
