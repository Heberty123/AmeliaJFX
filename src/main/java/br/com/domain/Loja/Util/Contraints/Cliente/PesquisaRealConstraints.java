package br.com.domain.Loja.Util.Contraints.Cliente;

import br.com.domain.Loja.Models.Cliente;
import br.com.domain.Loja.Models.Endereco;
import br.com.domain.Loja.Repositories.ClienteRepository;
import br.com.domain.Loja.Services.KillCaseSensitive;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PesquisaRealConstraints {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private KillCaseSensitive caseSensitive;

    public void pesquisaByNome(TextField txt, TableView<Cliente> table) {
        txt.textProperty().addListener((obs, oldValue, newValue) -> {


            if(!(newValue.isBlank())){
                List<Cliente> clientes = clienteRepository.findClienteByNomeContaining(newValue.toUpperCase());



                try {
                    caseSensitive.changeToLowerToView(clientes);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }



                ObservableList<Cliente> observableList = FXCollections.observableArrayList(clientes);
                table.setItems(observableList);
            }
            else
                table.setItems(null);

        });
    }
}
