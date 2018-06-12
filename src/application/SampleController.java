package application;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class SampleController implements Initializable
{
    private IntegerProperty counter;

    public IntegerProperty counterProperty() {
        return counter;
    }
    
    public int getCounter()
    {
        return counter.get();
    }

    public void setCounter(int value)
    {
        counter.set(value);
    }

    public SampleController()
    {
        counter = new SimpleIntegerProperty(15);
    }

    @Override
    public void initialize(URL url, ResourceBundle resources)
    {
    }

    @FXML
    private void handleStartButtonAction(ActionEvent event)
    {
        setCounter(getCounter() - 1);
        System.out.println(getCounter());
    }
}