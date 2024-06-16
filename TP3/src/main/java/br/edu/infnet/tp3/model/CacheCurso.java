package br.edu.infnet.tp3.model;

public class CacheCurso {
    private String id;
    private Curso curso;

    public CacheCurso(String id, Curso curso) {
        this.id = id;
        this.curso = curso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
