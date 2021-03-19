package se.lexicon.yomi.recipe.repository;

import se.lexicon.yomi.recipe.model.RecipeCategory;

import java.util.Collection;

public interface RecipeCategoryDao {

    RecipeCategory create(RecipeCategory category);
    RecipeCategory findById(Integer categoryId);
    Collection<RecipeCategory> findAll();
    RecipeCategory update(RecipeCategory categoryId);
    void delete (Integer categoryId);
}
