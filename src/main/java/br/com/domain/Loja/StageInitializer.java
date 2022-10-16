package br.com.domain.Loja;

import br.com.domain.Loja.Services.ChangeView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

@Component
public class StageInitializer implements ApplicationListener<ChartApplication.StageReadyEvent> {

    /* Atributes */
    @Value("classpath:/gui/Home.fxml")
    private Resource chartResource;
    private String applicationTitle;
    private ApplicationContext applicationContext;
    private static Scene mainScene;
    private final StringBuffer barcode = new StringBuffer();
    private long lastEventTimeStamp = 0L;
    private static final long THRESHOLD = 100;

    /* Services */

    @Autowired
    private ChangeView changeView;

    /* Methods */

    public StageInitializer(@Value("${spring.application.ui.title}") String applicationTitle,
                                ApplicationContext applicationContext){
        this.applicationTitle = applicationTitle;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ChartApplication.StageReadyEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(chartResource.getURL());
            fxmlLoader.setControllerFactory(aClass -> applicationContext.getBean(aClass));
            ScrollPane scrollPane = fxmlLoader.load();
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);
            Stage stage = event.getStage();
            mainScene = new Scene(scrollPane);


            mainScene.setOnKeyReleased((KeyEvent evento) -> {

                if(evento.getCode() != KeyCode.ENTER){
                    long now = Instant.now().toEpochMilli();

                    if (now - this.lastEventTimeStamp > this.THRESHOLD) {
                        barcode.delete(0, barcode.length());
                    }
                    this.lastEventTimeStamp = now;
                    this.barcode.append(evento.getText());
                }
                else{
                    Stage stagee = new Stage();
                    stagee.setTitle(barcode.toString());
                    stagee.show();
                    System.out.println(barcode.toString());
                    barcode.delete(0, barcode.length());
                }
            });

            stage.setScene(mainScene);
            stage.setTitle(applicationTitle);
            stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static Scene getScene(){
        return mainScene;
    }

}
