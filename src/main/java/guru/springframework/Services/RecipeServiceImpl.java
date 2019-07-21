package guru.springframework.Services;

import guru.springframework.Repositories.RecipeRepository;
import guru.springframework.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {
    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipe() {
        Set<Recipe> recipeSet=new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long l){
       Optional<Recipe> recipe=recipeRepository.findById(l);
       if(!recipe.isPresent()){
           throw new RuntimeException("Recipe Not Found!");

       }
       return recipe.get();
    }
}
