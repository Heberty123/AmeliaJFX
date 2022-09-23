package br.com.domain.Loja.Controller.Cliente;

import br.com.domain.Loja.Models.Cliente;
import br.com.domain.Loja.Repositories.ClienteRepository;
import br.com.domain.Loja.Services.ClienteService;
import br.com.domain.Loja.Util.Contraints.ClienteConstraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class CadastroController implements Initializable {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteConstraints clienteConstraints;

    @FXML
    private Label labelIdCliente;

    @FXML
    private TextField fieldName;

    @FXML
    private TextField fieldCPF;

    @FXML
    private Label labelErrorCPF;

    @FXML
    private Button buttonSave;

    private Cliente cliente = new Cliente();

    @FXML
    private AnchorPane anchorPaneEndereco;


    public void buttonSaveAction(){
        cliente.setNome(fieldName.getText());
        cliente.setCpf(fieldCPF.getText());


        Cliente clienteRegistered = clienteService.Register(cliente);
        labelIdCliente.setText(String.valueOf(clienteRegistered.getId()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clienteConstraints.cpfExist(fieldCPF, labelErrorCPF);
    }
}
