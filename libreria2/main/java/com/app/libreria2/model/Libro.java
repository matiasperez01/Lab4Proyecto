package com.app.libreria2.model;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@Entity
@Table(name="books")
public class Libro {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id_book;
    @Column(name="titulo", length=50, unique=false, nullable=false)
    private String titulo;
    @Column(name="autor", length=50, unique=false, nullable=false)
    private String autor;
    @Column(name="genero", length=50, unique=false, nullable=false)
    private String genero;
    @Column(name="num_paginas", length=50, unique=false, nullable=false)
    private int num_paginas;
    @Column(name="sinopsis", length=50, unique=false, nullable=false)
    private String sinopsis;

    public Libro() {
    }

    public Libro(int id_book, String titulo, String autor, String genero, int num_paginas, String sinopsis) {
        this.id_book = id_book;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.num_paginas = num_paginas;
        this.sinopsis = sinopsis;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNum_paginas() {
        return num_paginas;
    }

    public void setNum_paginas(int num_paginas) {
        this.num_paginas = num_paginas;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
}
