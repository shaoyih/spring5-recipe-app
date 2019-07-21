package guru.springframework.Services;

import guru.springframework.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    public Set<Recipe> getRecipe();
    public Recipe findById(Long l);
}
