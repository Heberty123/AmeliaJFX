package br.com.domain.Loja.Costumize;

import br.com.domain.Loja.Models.Marca;
import javafx.util.StringConverter;
import org.springframework.stereotype.Service;

@Service
public class StringConverterMarca extends StringConverter<Marca> {


    @Override
    public String toString(Marca object) {
        if(object == null)
            return null;

        return object.getNome();
    }

    @Override
    public Marca fromString(String string) {
        return null;
    }
}
