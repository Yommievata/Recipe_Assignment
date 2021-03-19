package se.lexicon.yomi.recipe.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.yomi.recipe.model.Recipe;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class RecipeDaoRepository implements RecipeDao {

    private final EntityManager em;

    @Autowired
    public RecipeDaoRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Recipe create(Recipe recipe) {
        em.persist(recipe);
        return recipe;
    }

    @Override
    @Transactional(readOnly = true)
    public Recipe findById(Integer recipeId) {
        return em.find(Recipe.class, recipeId);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Recipe> findAll() {
        Collection<Recipe> result = new ArrayList<>();
        return em.createQuery("SELECT recipe from Recipe  recipe", Recipe.class).getResultList();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Recipe update(Recipe recipe) {
        return em.merge(recipe);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delete(Integer recipeId) {
        Recipe clear = findById(recipeId);
        if (clear != null){
            em.remove(clear);
        }else{
            throw new IllegalArgumentException("Recipe Could not be found");
        }
        clear = findById(recipeId);
    }
}
