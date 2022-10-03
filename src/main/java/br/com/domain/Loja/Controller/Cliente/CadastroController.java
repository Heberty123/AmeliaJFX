package br.com.domain.Loja.Controller.Cliente;

import br.com.domain.Loja.Models.Cliente;
import br.com.domain.Loja.Models.Endereco;
import br.com.domain.Loja.Models.Estados;
import br.com.domain.Loja.Models.TipoEntrega;
import br.com.domain.Loja.Services.API;
import br.com.domain.Loja.Services.ClienteService;
import br.com.domain.Loja.Util.Contraints.Cliente.CPFConstraints;
import br.com.domain.Loja.Util.Contraints.Cliente.UpperConstraints;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class CadastroController implements Initializable {

    /* Services */

    @Autowired
    private API api;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private CPFConstraints clienteConstraints;
    @Autowired
    private UpperConstraints upperConstraints;


    /* Initial */

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

    /* Endereços */

    @FXML
    private AnchorPane anchorPaneEndereco;
    @FXML
    private TextField txtCEP;
    @FXML
    private Button buscaCEP;
    @FXML
    private TextField txtLogradouro;
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtBairro;
    @FXML
    private TextField txtCidade;
    @FXML
    private TextField txtCelular;
    @FXML
    private TextField txtTelefone;
    @FXML
    private ComboBox<Estados> comboBoxEstado;
    @FXML
    private ComboBox<TipoEntrega> comboBoxTipoEntrega;

    /* Table endereços */
    @FXML
    private TableView<Endereco> enderecos;
    @FXML
    private TableColumn<Endereco, Long> tableColumnId;
    @FXML
    private TableColumn<Endereco, String> tableColumnLogradouro;
    @FXML
    private TableColumn<Endereco, Integer> tableColumnNumero;
    @FXML
    private TableColumn<Endereco, String> tableColumnBairro;
    @FXML
    private TableColumn<Endereco, String> tableColumnLocalidade;
    @FXML
    private TableColumn<Endereco, String> tableColumnUF;
    @FXML
    private TableColumn<Endereco, String> tableColumnCEP;
    @FXML
    private TableColumn<Endereco, TipoEntrega> tableColumnTipoEntrega;
    @FXML
    private TableColumn<Endereco, String> tableColumnTelefone;
    @FXML
    private TableColumn<Endereco, String> tableColumnCelular;









    public void buttonSaveAction(){
        Cliente cliente = new Cliente();
        cliente.setNome(fieldName.getText());
        cliente.setCpf(fieldCPF.getText().replace(".", "").replace("-", ""));

        Cliente clienteRegistered = clienteService.Register(cliente);
        labelIdCliente.setText(String.valueOf(clienteRegistered.getId()));
        anchorPaneEndereco.setDisable(false);
    }

    public void buscaCEP() throws IOException, InterruptedException {
        String uri = String.format("https://viacep.com.br/ws/%s/json/", txtCEP.getText());
        Endereco end = api.getAPI(URI.create(uri), Endereco.class);

        txtLogradouro.setText(end.getLogradouro());
        txtBairro.setText(end.getBairro());
        txtCidade.setText(end.getLocalidade());
        comboBoxEstado.setValue(Estados.valueOf(end.getUf()));
    }


    public void salvarEndereco(){

        Endereco endereco = new Endereco();
        endereco.setLogradouro(txtLogradouro.getText());
        endereco.setNumero(Integer.parseInt(txtNumero.getText()));
        endereco.setBairro(txtBairro.getText());
        endereco.setLocalidade(txtCidade.getText());
        endereco.setUf(String.valueOf(comboBoxEstado.getValue()));
        endereco.setCep(txtCEP.getText());
        endereco.setTipoEntrega(comboBoxTipoEntrega.getValue());
        endereco.setTelefone(txtTelefone.getText());
        endereco.setCelular(txtCelular.getText());



        clienteService.addEnderecoParaCliente(Long.valueOf(labelIdCliente.getText()), endereco);


        initializeTableEnderecos();



    }

    private void initializeNodes(){
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnLogradouro.setCellValueFactory(new PropertyValueFactory<>("logradouro"));
        tableColumnNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tableColumnBairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        tableColumnLocalidade.setCellValueFactory(new PropertyValueFactory<>("localidade"));
        tableColumnUF.setCellValueFactory(new PropertyValueFactory<>("uf"));
        tableColumnCEP.setCellValueFactory(new PropertyValueFactory<>("cep"));
        tableColumnTipoEntrega.setCellValueFactory(new PropertyValueFactory<>("tipoEntrega"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tableColumnCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));
    }

    private void initializeComboBoxTipoEntrega() {

        ObservableList<TipoEntrega> entregas = comboBoxTipoEntrega.getItems();
        entregas.addAll(TipoEntrega.values());
        comboBoxTipoEntrega.setItems(entregas);
        comboBoxTipoEntrega.setValue(TipoEntrega.RESIDENCIA);
    }

    private void initializeComboBoxEstados() {

        ObservableList<Estados> estados = comboBoxEstado.getItems();
        estados.addAll(Estados.values());
        comboBoxEstado.setItems(estados);
        comboBoxEstado.setValue(Estados.AC);
    }

    private void initializeTableEnderecos() {

        List<Endereco> listaEnderecos = clienteService.buscarEnderecosPorCliente(Long.valueOf(labelIdCliente.getText()));
        ObservableList<Endereco> observableList = FXCollections.observableArrayList(listaEnderecos);

        enderecos.setItems(observableList);

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
        upperConstraints.upperFirstLetter(fieldName);
        clienteConstraints.setTextCpf(fieldCPF);
        clienteConstraints.cpfExist(fieldCPF, labelErrorCPF);
        initializeComboBoxTipoEntrega();
        initializeComboBoxEstados();


    }


}
