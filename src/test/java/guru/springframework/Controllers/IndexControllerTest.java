package guru.springframework.Controllers;

import guru.springframework.Services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class IndexControllerTest {
    IndexController indexController;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        indexController=new IndexController(recipeService);
    }
    @Test
    public void getIndexPage() {
        String result=indexController.getIndexPage(model);
        assertEquals("index",result);
        verify(model,times(1)).addAttribute(eq("recipes"),anySet());
        verify(recipeService,times(1)).getRecipe();
    }
}