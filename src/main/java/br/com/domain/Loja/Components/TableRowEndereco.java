package br.com.domain.Loja.Components;

import br.com.domain.Loja.Models.Endereco;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TableRowEndereco {

    public void getFormattedRowEndereco(Endereco endereco, ListView<AnchorPane> enderecos) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Cliente/Components/RowEndereco.fxml"));
        AnchorPane newAnchor = loader.load();

        ObservableList<Node> observableListNode = newAnchor.getChildren();
        /*
        Button button1 = (Button) observableListNode.get(0);
        button1.setText("Deu certo !!!!!");

        newAnchor.getChildren().set(0, button1);


        ObservableList<AnchorPane> observableList = enderecos.getItems();
        observableList.add(newAnchor);

        enderecos.setItems(observableList);

         */

        GridPane grid = new GridPane();

    }
}
