package se.lexicon.yomi.recipe.repository;

import se.lexicon.yomi.recipe.model.Ingredient;

import java.util.Collection;

public interface IngredientDao {

    Ingredient create(Ingredient ingredient);
    Ingredient findById(Integer ingredientId);
    Collection<Ingredient> findAll();
    Ingredient update(Ingredient ingredient);
    void delete (Integer ingredientId);
}
