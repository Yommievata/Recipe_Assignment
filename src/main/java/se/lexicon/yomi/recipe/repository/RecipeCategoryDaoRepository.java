package se.lexicon.yomi.recipe.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.yomi.recipe.model.RecipeCategory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class RecipeCategoryDaoRepository implements RecipeCategoryDao{
    private final EntityManager em;

    @Autowired
    public RecipeCategoryDaoRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    @Transactional (rollbackFor = RuntimeException.class)
    public RecipeCategory create(RecipeCategory category) {
        em.persist(category);
        return category;
    }

    @Override
    @Transactional(readOnly = true)
    public RecipeCategory findById(Integer categoryId) {
        return em.find(RecipeCategory.class, categoryId);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<RecipeCategory> findAll() {
        Collection<RecipeCategory> result = new ArrayList<>();
        return em.createQuery("SELECT category from RecipeCategory category", RecipeCategory.class).getResultList();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public RecipeCategory update(RecipeCategory category) {
        return em.merge(category);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delete(Integer categoryId) {
        RecipeCategory clear = findById(categoryId);
        if (clear != null){
            em.remove(clear);
        }else{
            throw new IllegalArgumentException("Recipe Category Could not be found");
        }
        clear = findById(categoryId);
    }
}
