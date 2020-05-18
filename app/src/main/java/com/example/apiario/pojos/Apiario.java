package com.example.apiario.pojos;

import java.io.Serializable;

public class Apiario implements Serializable {
    private Integer idApiario;
    private String name;
    private Usuario usuario;

    public Apiario() {
    }

    public Apiario(String name, Usuario usuario) {
        this.name = name;
        this.usuario = usuario;
    }

    public Apiario(Integer idApiario) {
        this.idApiario = idApiario;
    }

    public Integer getIdApiario() {
        return idApiario;
    }

    public void setIdApiario(Integer idApiario) {
        this.idApiario = idApiario;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Apiario(Integer idApiario, String name, Usuario usuario) {
        this.idApiario = idApiario;
        this.name = name;
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Apiario{" +
                "idApiario=" + idApiario +
                ", name='" + name + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
