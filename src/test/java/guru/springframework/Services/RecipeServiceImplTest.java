package guru.springframework.Services;

import guru.springframework.Converters.RecipeCommandToRecipe;
import guru.springframework.Converters.RecipeToRecipeCommand;
import guru.springframework.Repositories.RecipeRepository;
import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {
    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
        recipeService=new RecipeServiceImpl(recipeRepository,recipeCommandToRecipe,recipeToRecipeCommand);
    }

    @Test
    public void findByIdTest() throws Exception{
        Recipe recipe=new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional= Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        Recipe recipeReturned= recipeService.findById(1L);
        assertNotNull("Null recipe returned",recipeReturned);
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();
    }
    @Test
    public void getRecipesTest() throws Exception{
        Recipe recipe= new Recipe();
        HashSet recipeData=new HashSet();
        recipeData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeData);
        Set<Recipe> recipes= recipeService.getRecipe();
        assertEquals(recipes.size(),1);
        verify(recipeRepository,times(1)).findAll();
    }

}