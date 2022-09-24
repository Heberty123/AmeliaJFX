package br.com.domain.Loja.Util.Contraints.Cliente;

import br.com.domain.Loja.Repositories.ClienteRepository;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CPFConstraints {

    @Autowired
    private ClienteRepository clienteRepository;

    public void cpfExist(TextField txt, Label labelErrorExistCpf) {
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.isBlank() && newValue.matches("^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$")) {
                if(clienteRepository.existsByCpf(newValue.replace(".", "").replace("-", ""))) {
                    labelErrorExistCpf.setText("O cpf já existe");
                }
            }
            else {
                labelErrorExistCpf.setText(null);
            }
        });
    }


    public void setTextCpf(TextField txt) {
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            StringBuilder sb = new StringBuilder();

            if (!newValue.isBlank()) {

                    if(newValue.length() == 4){
                        if(!(newValue.charAt(3) == '.')){
                            sb.append(newValue).insert(3, '.');
                            txt.setText(sb.toString());
                        }
                        sb = new StringBuilder();
                    }

                    if(newValue.length() == 8){
                        if(!(newValue.charAt(7) == '.')){
                            sb.append(newValue).insert(7, '.');
                            txt.setText(sb.toString());
                        }
                        sb = new StringBuilder();
                    }

                    if(newValue.length() == 12){
                        if(!(newValue.charAt(11) == '-')){
                            sb.append(newValue).insert(11, '-');
                            txt.setText(sb.toString());
                        }
                        sb = new StringBuilder();
                    }


                    if(newValue.length() > 14){
                        txt.setText(oldValue);
                    }
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
