package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.config.AppConfig;
import org.example.model.FileInfo;

import java.io.File;

public class DownlodManager {
    @FXML
    private TableView<FileInfo> tableView;

    @FXML
    private TextField urltext_fild;

    public int indix = 0;
    @FXML
    void downlodButtonClicked(ActionEvent event) {
        String url= urltext_fild.getText().trim();
        String filename= url.substring(url.lastIndexOf("/")+1);
        String status="STRATING";
        String action="OPEN";
        String path= AppConfig.DOWNLOAD_PATH+ File.separator+filename;
        FileInfo file=new FileInfo((indix+1),filename,url,status,action,path);
        DownlodThread thread=new DownlodThread(file,this);
        thread.start();

    }

    public void updateUI(FileInfo metaFile) {
        System.out.println(metaFile);
        System.out.println("-------------");
    }

    @FXML
    public void initialize(){
        System.out.println("view initialize");
        TableColumn<FileInfo,String> sn=(TableColumn<FileInfo, String>) this.tableView.getColumns().get(0);
        sn.setCellValueFactory(p-> {
            return null;
        });
    }
}
