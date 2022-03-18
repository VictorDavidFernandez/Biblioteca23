/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author thefi
 */
@Entity
@Table(name = "libros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Libro.findAll", query = "SELECT l FROM Libro l")
    , @NamedQuery(name = "Libro.findByCodLibro", query = "SELECT l FROM Libro l WHERE l.codLibro = :codLibro")
    , @NamedQuery(name = "Libro.findByNomLibro", query = "SELECT l FROM Libro l WHERE l.nomLibro = :nomLibro")
    , @NamedQuery(name = "Libro.findByAutor", query = "SELECT l FROM Libro l WHERE l.autorCollection = :elAutor ORDER BY l.ano")
    , @NamedQuery(name = "Libro.findByPremiadosAutor", query = "SELECT DISTINCT l FROM Libro l WHERE l.autorCollection = :elAutor AND l.premioCollection != NULL")
    , @NamedQuery(name = "Libro.findByPremio", query = "SELECT l FROM Libro l WHERE l.premioCollection = :elPremio")
    , @NamedQuery(name = "Libro.findByAno", query = "SELECT l FROM Libro l WHERE l.ano = :ano")
    , @NamedQuery(name = "Libro.findByPortada", query = "SELECT l FROM Libro l WHERE l.portada = :portada")
    , @NamedQuery(name = "Libro.findByNomArchivo", query = "SELECT l FROM Libro l WHERE l.nomArchivo = :nomArchivo")
    , @NamedQuery(name = "Libro.findByPelicula", query = "SELECT l FROM Libro l WHERE l.pelicula = :pelicula")})
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_libro")
    private Integer codLibro;
    @Size(max = 100)
    @Column(name = "nom_libro")
    private String nomLibro;
    @Column(name = "ano")
    private Integer ano;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "portada")
    private String portada;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nom_archivo")
    private String nomArchivo;
    @Size(max = 30)
    @Column(name = "pelicula")
    private String pelicula;
    @Lob
    @Size(max = 16777215)
    @Column(name = "comentario")
    private String comentario;
    @JoinTable(name = "libro_premios", joinColumns = {
        @JoinColumn(name = "cod_libro", referencedColumnName = "cod_libro")}, inverseJoinColumns = {
        @JoinColumn(name = "cod_premio", referencedColumnName = "cod_premio")})
    @ManyToMany
    private Collection<Premio> premioCollection;
    @JoinTable(name = "autor_libro", joinColumns = {
        @JoinColumn(name = "cod_libro", referencedColumnName = "cod_libro")}, inverseJoinColumns = {
        @JoinColumn(name = "cod_autor", referencedColumnName = "cod_autor")})
    @ManyToMany
    private Collection<Autor> autorCollection;

    public Libro() {
    }

    public Libro(Integer codLibro) {
        this.codLibro = codLibro;
    }

    public Libro(Integer codLibro, String portada, String nomArchivo) {
        this.codLibro = codLibro;
        this.portada = portada;
        this.nomArchivo = nomArchivo;
    }

    public Integer getCodLibro() {
        return codLibro;
    }

    public void setCodLibro(Integer codLibro) {
        this.codLibro = codLibro;
    }

    public String getNomLibro() {
        return nomLibro;
    }

    public void setNomLibro(String nomLibro) {
        this.nomLibro = nomLibro;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public String getNomArchivo() {
        return nomArchivo;
    }

    public void setNomArchivo(String nomArchivo) {
        this.nomArchivo = nomArchivo;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @XmlTransient
    public Collection<Premio> getPremioCollection() {
        return premioCollection;
    }

    public void setPremioCollection(Collection<Premio> premioCollection) {
        this.premioCollection = premioCollection;
    }

    @XmlTransient
    public Collection<Autor> getAutorCollection() {
        return autorCollection;
    }

    public void setAutorCollection(Collection<Autor> autorCollection) {
        this.autorCollection = autorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codLibro != null ? codLibro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libro)) {
            return false;
        }
        Libro other = (Libro) object;
        if ((this.codLibro == null && other.codLibro != null) || (this.codLibro != null && !this.codLibro.equals(other.codLibro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Libro[ codLibro=" + codLibro + " ]";
    }
    
}
