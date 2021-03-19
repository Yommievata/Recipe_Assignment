package se.lexicon.yomi.recipe.repository;

import se.lexicon.yomi.recipe.model.Recipe;

import java.util.Collection;

public interface RecipeDao {
    Recipe create(Recipe recipe);
    Recipe findById(Integer recipeId);
    Collection<Recipe> findAll();
    Recipe update(Recipe recipe);
    void delete (Integer recipeId);
}
