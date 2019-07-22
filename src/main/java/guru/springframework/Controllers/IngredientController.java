package guru.springframework.Controllers;

import guru.springframework.Services.IngredientService;
import guru.springframework.Services.RecipeService;
import guru.springframework.Services.unitOfMeasureService;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
public class IngredientController {
    private RecipeService recipeService;
    private IngredientService ingredientService;
    private unitOfMeasureService uomService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, unitOfMeasureService uomService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.uomService = uomService;
    }

    @GetMapping
    @RequestMapping("recipe/ingredients/{id}")
    public String listIngredients(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/ingredients/list";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/ingredient/{iId}/show")
    public String viewIngredient(@PathVariable String id, @PathVariable String iId, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRIdAndId(Long.valueOf(id), Long.valueOf(iId)));
        return "recipe/ingredients/show";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/ingredient/{iId}/update")
    public String updateIngredient(@PathVariable String id, @PathVariable String iId, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRIdAndId(Long.valueOf(id), Long.valueOf(iId)));
        model.addAttribute("uomList", uomService.listAllUoms());
        return "recipe/ingredients/ingredientform";
    }

    @PostMapping
    @RequestMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand) {
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);
        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }


    @GetMapping
    @RequestMapping("recipe/{id}/ingredient/{iId}/delete")
    public String deleteIngredient(@PathVariable String id, @PathVariable String iId) {
        ingredientService.deleteById(Long.valueOf(id), Long.valueOf(iId));
        return "redirect:/recipe/ingredients/" + id;
    }


    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable String recipeId, Model model) {

        //make sure we have a good id value
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
        //todo raise exception if null

        //need to return back parent id for hidden form property
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientCommand);

        //init uom
        ingredientCommand.setUom(new UnitOfMeasureCommand());

        model.addAttribute("uomList", uomService.listAllUoms());

        return "recipe/ingredients/ingredientform";


    }
}
