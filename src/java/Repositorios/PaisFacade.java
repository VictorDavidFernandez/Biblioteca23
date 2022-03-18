/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorios;

import Entidades.Autor;
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
public class PaisFacade extends AbstractFacade<Pais> {

    @PersistenceContext(unitName = "Biblioteca23PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaisFacade() {
        super(Pais.class);
    }
    
    public List<Pais> paisesOrdenados(){
        EntityManager em = getEntityManager();
        Query q;
        q = em.createNamedQuery("Pais.findAllOrder");
        
        return q.getResultList();
    }
    public List<Autor> autoresPorPais(Pais pais){
        EntityManager em = getEntityManager();
        Query q;
        if(pais != null){
            q = em.createNamedQuery("Autor.findByPais").setParameter("pais", pais);
            return q.getResultList();
        }
        return null;
    }
    
    public List<Premio> premiosPorPais(Pais pais){
        EntityManager em = getEntityManager();
        Query q;
        if(pais != null){
            q = em.createNamedQuery("Premio.findByPais").setParameter("codPais", pais);
            return q.getResultList();
        }
        return null;
    }    
}
