package br.com.domain.Loja.Util.Contraints.Produto;

import br.com.domain.Loja.Models.Produto;
import br.com.domain.Loja.Repositories.ProdutoRepository;
import br.com.domain.Loja.Services.KillCaseSensitive;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PesquisaRealProduto {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private KillCaseSensitive caseSensitive;

    public void pesquisaByNome(TextField txt, TableView<Produto> tableView) {
        txt.textProperty().addListener((obs, oldValue, newValue) -> {

            System.out.println("vc digitou: " + newValue);

            if(!(newValue.isBlank())){
                List<Produto> produtos = produtoRepository.findProdutoByNomeContaining(newValue.toUpperCase());

                try {
                    caseSensitive.changeToLowerToView(produtos);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

                ObservableList<Produto> observableList = FXCollections.observableArrayList(produtos);
                tableView.setItems(observableList);
            }
            else
                tableView.setItems(null);

        });
    }
}
