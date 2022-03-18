/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorios;

import Entidades.Autor;
import Entidades.AutorPremio;
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
public class AutorFacade extends AbstractFacade<Autor> {

    @PersistenceContext(unitName = "Biblioteca23PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AutorFacade() {
        super(Autor.class);
    }
    public List<Autor> autoresOrdenados(){
        EntityManager em = getEntityManager();
        Query q;
        q = em.createNamedQuery("Autor.findAllOrder");
        
        return q.getResultList();
    }  
    
    public List<AutorPremio> autoresPremios(Premio autoresPremios){
        EntityManager em = getEntityManager();
        Query q;
        if(autoresPremios!= null){
            q = em.createNamedQuery("AutorPremio.findByCodPremio").setParameter("codPremio", autoresPremios.getCodPremio());
            return q.getResultList();
        }
        return null;
    }

    public List<Autor> cargarAutoresPais(Pais pais){
        EntityManager em = getEntityManager();
        Query q;
        if(pais!= null){
            q = em.createNamedQuery("Autor.findByPais").setParameter("pais", pais);
            return q.getResultList();
        }
        return null;
    }
    
    public List<Autor> autoresPorPremio(Premio premio){
        EntityManager em = getEntityManager();
        Query q;
        if(premio != null){
            q = em.createNamedQuery("AutorPremio.findByCodPremioAutor").setParameter("premio", premio.getCodPremio());
            return q.getResultList();
        }
        return null;
    }
}
