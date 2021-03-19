package se.lexicon.yomi.recipe.repository;

import se.lexicon.yomi.recipe.model.RecipeInstruction;

import java.util.Collection;

public interface RecipeInstructionDao {

    RecipeInstruction create(RecipeInstruction instruction);
    RecipeInstruction findById(Integer instructionId);
    Collection<RecipeInstruction> findAll();
    RecipeInstruction update(RecipeInstruction instruction);
    void delete (Integer instructionId);
}
