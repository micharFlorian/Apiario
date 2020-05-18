package com.example.apiario.pojos;


import java.io.Serializable;

public class Colmena implements Serializable {

    private Integer idColmena;
    private Apiario apiario;
    private String incidencia;

    public Colmena(Integer idColmena, Apiario apiario, String incidencia) {
        this.idColmena = idColmena;
        this.apiario = apiario;
        this.incidencia = incidencia;
    }

    public Colmena(Apiario apiario, String incidencia) {
        this.apiario = apiario;
        this.incidencia = incidencia;
    }

    public Integer getIdColmena() {
        return idColmena;
    }

    public void setIdColmena(Integer idColmena) {
        this.idColmena = idColmena;
    }

    public Apiario getApiario() {
        return apiario;
    }

    public void setApiario(Apiario apiario) {
        this.apiario = apiario;
    }

    public String getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(String incidencia) {
        this.incidencia = incidencia;
    }

    public Colmena() {
    }

    @Override
    public String toString() {
        return "Colmena{" +
                "idColmena=" + idColmena +
                ", apiario=" + apiario +
                ", incidencia='" + incidencia + '\'' +
                '}';
    }
}
