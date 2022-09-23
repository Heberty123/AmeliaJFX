package br.com.domain.Loja;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<ChartApplication.StageReadyEvent> {
    @Value("classpath:/gui/Home.fxml")
    private Resource chartResource;
    private String applicationTitle;
    private ApplicationContext applicationContext;

    private static Scene mainScene;

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
