package se.lexicon.yomi.recipe.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.yomi.recipe.model.RecipeInstruction;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class RecipeInstructionDaoRepository implements RecipeInstructionDao{
    private final EntityManager em;

    @Autowired
    public RecipeInstructionDaoRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public RecipeInstruction create(RecipeInstruction instruction) {
        em.persist(instruction);
        return instruction;
    }

    @Override
    @Transactional(readOnly = true)
    public RecipeInstruction findById(Integer instructionId) {
        return em.find(RecipeInstruction.class, instructionId);
    }


    @Override
    @Transactional(readOnly = true)
    public Collection<RecipeInstruction> findAll() {
        Collection<RecipeInstruction> result = new ArrayList<>();
        return em.createQuery("SELECT instruction from RecipeInstruction  instruction", RecipeInstruction.class).getResultList();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public RecipeInstruction update(RecipeInstruction instruction) {
        return em.merge(instruction);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delete(Integer instructionId) {
        RecipeInstruction clear = findById(instructionId);
        if (clear != null){
            em.remove(clear);
        }else{
            throw new IllegalArgumentException("Recipe Instruction Could not be found");
        }
        clear = findById(instructionId);
    }
}
