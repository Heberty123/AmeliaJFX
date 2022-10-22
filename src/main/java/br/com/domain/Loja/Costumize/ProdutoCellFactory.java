package br.com.domain.Loja.Costumize;

import br.com.domain.Loja.Models.Produto;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import org.springframework.stereotype.Service;

@Service
public class ProdutoCellFactory implements Callback<ListView<Produto>, ListCell<Produto>> {

    @Override
    public ListCell<Produto> call(ListView<Produto> param) {
        return new ListCell<>(){
            @Override
            public void updateItem(Produto produto, boolean empty){
                super.updateItem(produto, empty);
                if(empty || produto == null){
                    setText(null);
                } else {
                    setText(produto.getNome());
                }
            }
        };
    }
}
