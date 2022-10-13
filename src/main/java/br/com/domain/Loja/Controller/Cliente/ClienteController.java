package br.com.domain.Loja.Controller.Cliente;

import br.com.domain.Loja.Models.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.stereotype.Controller;

@Controller
public class ClienteController {

    /* Atributes */

    private Cliente cliente;

    /* FXML */

    @FXML
    private Label labelName;


    public void updateView(){
        labelName.setText(cliente.getNome());
    }


    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
