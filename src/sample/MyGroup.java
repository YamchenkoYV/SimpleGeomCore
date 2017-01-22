package sample;

import javafx.scene.Group;
import javafx.scene.shape.Line;

/**
 * Created by vladimir on 20.01.17.
 */
public class MyGroup extends Group {

    public void addLine(MyLine line){
        getChildren().addAll(line, line.startPoint, line.endPoint);
    }

}
