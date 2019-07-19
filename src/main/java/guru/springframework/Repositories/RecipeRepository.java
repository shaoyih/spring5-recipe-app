package guru.springframework.Repositories;

import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;


public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
