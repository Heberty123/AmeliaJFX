package br.com.domain.Loja.Util.Contraints;

import br.com.domain.Loja.Repositories.ClienteRepository;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteConstraints {

    @Autowired
    private ClienteRepository clienteRepository;

    public void cpfExist(TextField txt, Label labelErrorExistCpf) {
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.isBlank() && newValue.matches("^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$")) {
                System.out.println("eu fui chamado o primeiro if");
                if(clienteRepository.existsByCpf(newValue)) {
                    System.out.println("eu fui chamado o segundo if");
                    labelErrorExistCpf.setText("O cpf já existe");
                }

            }
            else {
                labelErrorExistCpf.setText(null);
            }

        });
    }

/*
    public static void setTextFieldDouble(TextField txt) {
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null && !newValue.matches("\\d*([\\.]\\d*)?")) {
                txt.setText(oldValue);
            }
        });
    }
 */
}
