/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorios;

import Entidades.Autor;
import Entidades.Libro;
import Entidades.Premio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author thefi
 */
@Stateless
public class LibroFacade extends AbstractFacade<Libro> {

    @PersistenceContext(unitName = "Biblioteca23PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LibroFacade() {
        super(Libro.class);
    }

    public List<Libro> librosAutor(Autor autor){
        EntityManager em = getEntityManager();
        Query q;
        q = em.createNamedQuery("Libro.findByAutor").setParameter("elAutor", autor);
        
        return q.getResultList();
    }
    
    public List<Libro> librosPremio(Premio premio){
        EntityManager em = getEntityManager();
        Query q;
        q = em.createNamedQuery("Libro.findByPremio").setParameter("elPremio", premio);
        
        return q.getResultList();
    }
    
    public List<Libro> librosPremiadosAutor(Autor autor){
        EntityManager em = getEntityManager();
        Query q;
        if(autor != null){
            q = em.createNamedQuery("Libro.findByPremiadosAutor").setParameter("elAutor", autor);
            return q.getResultList();
        }
        return null;
    }
}
