package org.example;

import org.example.model.FileInfo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DownlodThread extends Thread{

    private FileInfo file;
    DownlodManager manager;
    public DownlodThread(FileInfo file,DownlodManager manager){
        this.file=file;
        this.manager=manager;
    }

    @Override
    public void run() {
        this.file.setStatus("DOWNLOADINF");
        this.manager.updateUI(this.file);
        //dowload logic
            try {
                Files.copy(new URL(this.file.getUrl()).openStream(), Paths.get(this.file.getPath()));
                this.file.setStatus("DONE");
            }catch (IOException e){
                this.file.setStatus("Failed");
                System.out.println("Downloding error");
                e.printStackTrace();
            }
        this.manager.updateUI(this.file);

    }
}
