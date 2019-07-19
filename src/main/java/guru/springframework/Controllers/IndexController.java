package guru.springframework.Controllers;

import guru.springframework.Repositories.CategoryRepository;
import guru.springframework.Repositories.UnitOfMeasureRepository;
import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(){

        Optional<Category> categoryOptional =categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasure =unitOfMeasureRepository.findByDescription("Ounce");

        System.out.println(categoryOptional.get().getDescription());
        System.out.println(unitOfMeasure.get().getDescription());

        return "index";
    }
}
