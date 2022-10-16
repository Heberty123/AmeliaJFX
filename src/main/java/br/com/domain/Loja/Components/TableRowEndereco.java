package br.com.domain.Loja.Components;

import br.com.domain.Loja.Models.Endereco;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class TableRowEndereco {

    public void getFormattedRowEndereco(List<Endereco> listEnderecos, ListView<AnchorPane> enderecos) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Cliente/Components/RowEndereco.fxml"));
        AnchorPane newAnchor = loader.load();

        for(Endereco end : listEnderecos){

            GridPane gridPane = (GridPane) newAnchor.getChildren().get(0);

            Label labelId = new Label(String.valueOf(end.getId()));
            Label labelCep = new Label(end.getCep());
            Label labelTipoEntrega = new Label(end.getTipoEntrega().toString());
            Label labelLograAndUF = new Label(String.format("%s/%s", end.getLogradouro(), end.getUf()));
            Label labelLocalidade = new Label(end.getLocalidade());
            Label labelBairro = new Label(end.getBairro());
            Label labelCelular = new Label(end.getCelular());
            Label labelTelefone = new Label(end.getTelefone());

            gridPane.add(labelId, 0,0);
            gridPane.add(labelCep, 1,0);
            gridPane.add(labelTipoEntrega, 2,0);
            gridPane.add(labelLograAndUF, 3,0);
            gridPane.add(labelLocalidade, 2,1);
            gridPane.add(labelBairro, 3,1);
            gridPane.add(labelCelular, 2,2);
            gridPane.add(labelTelefone, 3,2);

            newAnchor.getChildren().set(0, gridPane);

            ObservableList<AnchorPane> observableList = enderecos.getItems();
            observableList.add(newAnchor);

            enderecos.setItems(observableList);
        }



    }
}
