package guru.springframework.Services;

import guru.springframework.commands.IngredientCommand;

public interface IngredientService {
    public IngredientCommand findByRIdAndId(Long id,Long lId);
    public void deleteById(Long id,Long lId);
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
}
