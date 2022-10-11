package br.com.domain.Loja.Services;

import br.com.domain.Loja.Models.Cliente;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class KillCaseSensitive {

    public void everthingToUpperCase(Object object) throws IllegalAccessException {
        Class<?> classe = object.getClass();
        Field[] campos = classe.getDeclaredFields();


        for(Field campo : campos){

            if(campo.getType().equals(String.class)){
                campo.setAccessible(true);
                campo.set(object, campo.get(object).toString().toUpperCase());
            }

        }
    }


    public void changeToLowerToView(Object object) throws IllegalAccessException {


        if(object instanceof Collection){

            for(Object obj : ((Collection<?>) object).toArray()){

                Class<?> classe = obj.getClass();
                Field[] campos = classe.getDeclaredFields();


                for(Field campo : campos){

                    if(campo.getType().equals(String.class)){
                        campo.setAccessible(true);


                        String nomes[] = campo.get(obj).toString().toLowerCase().split(" ");

                        String uppers = Arrays.stream(nomes).map(n -> n.substring(0, 1).toUpperCase() + n.substring(1))
                                .collect(Collectors.joining(" "));

                        campo.set(obj, uppers);

                    }

                }
            }
        }

    }


}
