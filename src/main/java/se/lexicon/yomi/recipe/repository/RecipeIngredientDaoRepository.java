package se.lexicon.yomi.recipe.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.yomi.recipe.model.RecipeIngredient;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class RecipeIngredientDaoRepository implements RecipeIngredientDao{

    private final EntityManager em;

    @Autowired
    public RecipeIngredientDaoRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public RecipeIngredient create(RecipeIngredient recipeIngredient) {
        em.persist(recipeIngredient);
        return recipeIngredient;
    }

    @Override
    @Transactional(readOnly = true)
    public RecipeIngredient findById(Integer recipeIngredientId) {
        return em.find(RecipeIngredient.class, recipeIngredientId);
    }


    @Override
    @Transactional (readOnly = true)
    public Collection<RecipeIngredient> findAll() {
        Collection<RecipeIngredient> result = new ArrayList<>();
        return em.createQuery("SELECT recipeIngredient from RecipeIngredient recipeIngredient", RecipeIngredient.class).getResultList();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public RecipeIngredient update(RecipeIngredient recipeIngredient) {
        return em.merge(recipeIngredient);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delete(Integer recipeIngredientId) {
        RecipeIngredient clear = findById(recipeIngredientId);
        if (recipeIngredientId != null){
            em.remove(clear);
        }else{
            throw new IllegalArgumentException("Recipe Ingredient Could not be found");
        }
        clear = findById(recipeIngredientId);
    }
}
