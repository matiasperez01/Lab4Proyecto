package com.app.libreria2.model;


import jakarta.persistence.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@Entity
@Table(name="usuarios")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id_usuario;

    @Column(name="nombre", length=50, nullable=false, unique=false)
    private String nombre;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public User() {
    }

    public User(int id_usuario, String nombre){
        this.id_usuario = id_usuario;
        this.nombre = nombre;
    }

}