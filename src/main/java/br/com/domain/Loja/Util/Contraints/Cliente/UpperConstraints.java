package br.com.domain.Loja.Util.Contraints.Cliente;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class UpperConstraints {


    public void upperFirstLetter(TextField txt) {
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.isBlank()) {




                String nomes[] = newValue.split(" ");

                String uppers = Arrays.stream(nomes).map(n -> n.substring(0, 1).toUpperCase() + n.substring(1))
                        .collect(Collectors.joining(" "));


                txt.setText(other);
            }
        });
    }
}
