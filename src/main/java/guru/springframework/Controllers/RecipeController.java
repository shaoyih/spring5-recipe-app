package guru.springframework.Controllers;


import guru.springframework.Services.RecipeService;
import guru.springframework.commands.RecipeCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {
    private  final RecipeService recipeService;
    public RecipeController(RecipeService recipeService){
        this.recipeService=recipeService;
    }

    @GetMapping
    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.findById(new Long(id)));
        return "recipe/show";
    }

    @GetMapping
    @RequestMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe",new RecipeCommand());
        return "recipe/recipeform";
    }

    @GetMapping
    @RequestMapping("recipe/update/{id}")
    public String updateRecipe(@PathVariable String id,Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        System.out.println(id);
        return "recipe/recipeform";

    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand savedRecipeCommand= recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/show/"+savedRecipeCommand.getId();
    }

    @GetMapping
    @RequestMapping("recipe/delete/{id}")
    public String deleteById(@PathVariable String id){
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
}
