package guru.springframework.Services;

import guru.springframework.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface unitOfMeasureService {
    public Set<UnitOfMeasureCommand> listAllUoms();

}
