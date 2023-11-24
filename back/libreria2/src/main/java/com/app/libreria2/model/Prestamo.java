package com.app.libreria2.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="prestamos")
public class Prestamo {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id_prestamo;
    @Column(name="id_book")
    private int id_book;

    @Column(name="fecha_prestamo")
    private LocalDate fecha_prestamo;
    @Column(name="fecha_devolucion")
    private LocalDate fecha_devolucion;
    @Column(name="nombre_prestatario")
    private String nombre_prestatario;
    public Prestamo() {
    }

    public Prestamo(int id_prestamo, int id_book, LocalDate fechaprestamo, LocalDate fechadevolucion, String nombre_prestatario) {
        this.id_prestamo = id_prestamo;
        this.id_book = id_book;
        this.fecha_prestamo = fechaprestamo;
        this.fecha_devolucion = fechadevolucion;
        this.nombre_prestatario = nombre_prestatario;
    }

    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public LocalDate getFechaprestamo() {
        return fecha_prestamo;
    }

    public void setFechaprestamo(LocalDate fechaprestamo) {
        this.fecha_prestamo = fechaprestamo;
    }

    public LocalDate getFechadevolucion() {
        return fecha_devolucion;
    }

    public void setFechadevolucion(LocalDate fechadevolucion) {
        this.fecha_devolucion = fechadevolucion;
    }

    public String getNombre_prestatario() {
        return nombre_prestatario;
    }

    public void setNombre_prestatario(String nombre_prestatario) {
        this.nombre_prestatario = nombre_prestatario;
    }
}
