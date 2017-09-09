package com.ak.controler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowControler implements Initializable {

    private final static String surl = "http://api.apixu.com/v1/current.json?key=d578c483ed8b494bad8170534161511&q=Warsaw";
    private final static String coldimage = "http://www.personal.psu.edu/afr3/blogs/siowfa12/cold.jpg";
    private final static String hotImage = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQSmmmLN1JfEkCvdvbhIDsQw_xAJQ5QKjVxmQ0lXlq3pbJ-vts4";


    //referencje na kontrolki
    @FXML
    private ImageView image;

    @FXML
    private Label temperature;

    @FXML
    private Label temperatureF;

    public void initialize(URL location, ResourceBundle resources) {
        temperature.setText("Pobieranie danych pogodowych");
        try {
            URL url = new URL(surl);
            //jsonowy root
            JSONObject json = new JSONObject(IOUtils.toString(url));
            //chcemy pobrac zawartosc taga "current"
            JSONObject json2 = (JSONObject) json.get("current");
            //pobieramy temp w postaci tekstowej w *c
            String sstemp = json2.get("temp_c").toString();
            String sstempF = json2.get("temp_f").toString();
            Double temp = Double.parseDouble(sstemp);
            Double tempF = Double.parseDouble(sstempF);
            Image im = null;
            if (temp < 15) {
                im = new Image(coldimage);

            } else {
                im = new Image(hotImage);

            }
            if (im != null) {
                image.setImage(im);
            }

            temperature.setText("Srednia temperatura: " + temp);
            temperatureF.setText("Srednia temperatura w F " + tempF);

            System.out.println(json.toString());

        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
