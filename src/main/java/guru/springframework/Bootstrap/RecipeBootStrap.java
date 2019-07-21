package guru.springframework.Bootstrap;

import guru.springframework.Repositories.CategoryRepository;
import guru.springframework.Repositories.RecipeRepository;
import guru.springframework.Repositories.UnitOfMeasureRepository;
import guru.springframework.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootStrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading Bootstrap data");
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if (!tableSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if (!teaSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }



        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if (!cupsUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teapoonUom = tableSpoonUomOptional.get();

        UnitOfMeasure cupsUom = cupsUomOptional.get();

        //get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }





        //get Categories
        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        Recipe xiHongShi_ChaoJiDan=new Recipe();
        xiHongShi_ChaoJiDan.setDescription("完美的家常菜");
        xiHongShi_ChaoJiDan.setPrepTime(10);
        xiHongShi_ChaoJiDan.setCookeTime(10);
        xiHongShi_ChaoJiDan.setDifficulty(Difficulty.EASY);

        xiHongShi_ChaoJiDan.setDirections("1.有个厨房\n 2.有点食材\n 3.会用百度\n 4.自行百度");
        Notes xiNotes= new Notes();
        xiNotes.setRecipeNotes("别废话！");
        xiNotes.setRecipe(xiHongShi_ChaoJiDan);
        xiHongShi_ChaoJiDan.setNotes(xiNotes);
        xiHongShi_ChaoJiDan.getIngredients().add(new Ingredient("西红柿",new BigDecimal(2),xiHongShi_ChaoJiDan,teapoonUom));
        xiHongShi_ChaoJiDan.getIngredients().add(new Ingredient("鸡蛋",new BigDecimal(2),xiHongShi_ChaoJiDan,eachUom));
        xiHongShi_ChaoJiDan.getCategories().add(americanCategory);
        xiHongShi_ChaoJiDan.getCategories().add(mexicanCategory);

        xiHongShi_ChaoJiDan.setUrl("www.baidu.com");
        xiHongShi_ChaoJiDan.setServings(4);

        Recipe xiao_chao_rou=new Recipe();
        xiao_chao_rou.setUrl("www.baidu.com");
        xiao_chao_rou.setServings(5);
        xiao_chao_rou.setDescription("我好饿");
        xiao_chao_rou.setPrepTime(11);
        xiao_chao_rou.setCookeTime(0);
        xiao_chao_rou.setDifficulty(Difficulty.HARD);

        xiao_chao_rou.setDirections("1.有个厨房ba\n 2.有点食材\n 3.会用百度\n 4.自行百度");
        Notes xi_caho_Notes= new Notes();
        xi_caho_Notes.setRecipeNotes("别废话a！");
        xi_caho_Notes.setRecipe(xiao_chao_rou);
        xiao_chao_rou.setNotes(xi_caho_Notes);
        xiao_chao_rou.getIngredients().add(new Ingredient("肉",new BigDecimal(2),xiao_chao_rou,teapoonUom));
        xiao_chao_rou.getIngredients().add(new Ingredient("青椒",new BigDecimal(2),xiao_chao_rou,eachUom));
        xiao_chao_rou.getCategories().add(americanCategory);
        xiao_chao_rou.getCategories().add(mexicanCategory);

        recipes.add(xiao_chao_rou);
        recipes.add(xiHongShi_ChaoJiDan);

        return recipes;


    }
}
