package com.example.apiario.pojos;

import java.io.Serializable;

public class Usuario implements Serializable {

    private Integer idUsuario;
    private String name;
    private String password;
    private String dni;
    private String rol;
    private String telefono;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(String name, String password, String dni, String rol, String telefono) {
        this.name = name;
        this.password = password;
        this.dni = dni;
        this.rol = rol;
        this.telefono = telefono;
    }

    public Usuario(Integer idUsuario, String name, String password, String dni, String rol, String telefono) {
        this.idUsuario = idUsuario;
        this.name = name;
        this.password = password;
        this.dni = dni;
        this.rol = rol;
        this.telefono = telefono;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", dni='" + dni + '\'' +
                ", rol='" + rol + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
