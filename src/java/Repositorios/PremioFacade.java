/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorios;

import Entidades.Autor;
import Entidades.AutorPremio;
import Entidades.Libro;
import Entidades.Pais;
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
public class PremioFacade extends AbstractFacade<Premio> {

    @PersistenceContext(unitName = "Biblioteca23PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PremioFacade() {
        super(Premio.class);
    }
    public List<Premio> premiosOrdenados(){
        EntityManager em = getEntityManager();
        Query q;
        q = em.createNamedQuery("Premio.findAllOrder");
        
        return q.getResultList();
    }
    
    public List<Premio> premios_autor(){
        EntityManager em = getEntityManager();
        Query q;
        q = em.createNamedQuery("Premio.findByTipo").setParameter("tipo", 'A');
        
        return q.getResultList();
    }
    
    public List<Premio> premios_libro(){
        EntityManager em = getEntityManager();
        Query q;
        q = em.createNamedQuery("Premio.findByTipo").setParameter("tipo", 'L');
        
        return q.getResultList();
    }
    
    public List<AutorPremio> premiosDeAutor(Autor autor){
        EntityManager em = getEntityManager();
        Query q;
        if(autor != null){
            q = em.createNamedQuery("AutorPremio.findByCodAutor").setParameter("codAutor", autor.getCodAutor());
            return q.getResultList();
        }
        return null;
    }
    
    public List<Libro> librosPremiadosAutor(Autor autor){
        EntityManager em = getEntityManager();
        Query q;
        q = em.createNamedQuery("Libro.findByPremiadosAutor").setParameter("elAutor", autor);
        return q.getResultList();

    }
}
