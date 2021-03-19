package se.lexicon.yomi.recipe.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.lexicon.yomi.recipe.model.Ingredient;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class IngredientDaoRepository implements IngredientDao{

    private final EntityManager em;

    @Autowired
    public IngredientDaoRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Ingredient create(Ingredient ingredient) {
        em.persist(ingredient);
        return ingredient;
    }

    @Override
    @Transactional(readOnly = true)
    public Ingredient findById(Integer ingredientId) {
        return em.find(Ingredient.class, ingredientId);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Ingredient> findAll() {
        Collection<Ingredient> result = new ArrayList<>();
        return em.createQuery("SELECT ingredient from Ingredient ingredient", Ingredient.class).getResultList();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Ingredient update(Ingredient ingredient) {
        return em.merge(ingredient);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delete(Integer ingredientId) {
        Ingredient clear = findById(ingredientId);
        if (clear != null){
            em.remove(clear);
        }else{
            throw new IllegalArgumentException("Ingredient Could not be found");
        }
        clear = findById(ingredientId);
    }
}

