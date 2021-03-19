package se.lexicon.yomi.recipe.repository;

import se.lexicon.yomi.recipe.model.RecipeIngredient;

import java.util.Collection;

public interface RecipeIngredientDao {

    RecipeIngredient create(RecipeIngredient recipeIngredient);
    RecipeIngredient findById(Integer recipeIngredientId);
    Collection<RecipeIngredient> findAll();
    RecipeIngredient update(RecipeIngredient recipeIngredient);
    void delete (Integer recipeIngredientId);
}
