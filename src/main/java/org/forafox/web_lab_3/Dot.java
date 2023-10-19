package org.forafox.web_lab_3;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.component.html.HtmlSelectBooleanCheckbox;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "dots")
@ApplicationScoped
public class Dot implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private float x = -2.0f;
    @Column
    private float y = 0.0f;
    @Column
    private float r = 2.0f;
    @Column
    private boolean status;
    @Column
    private String time;
    @Column
    private long scriptTime;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getR() {
        return r;
    }

    public void setR(float radius) {
        this.r = radius;
    }

    public String getStatus() {
        if (status) return "попадание!";
        else return "промах!";
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getScriptTime() {
        return scriptTime;
    }

    public void setScriptTime(long scriptTime) {
        this.scriptTime = scriptTime;
    }

    public void updateX(ValueChangeEvent e){
        String id = ((HtmlSelectBooleanCheckbox) e.getSource()).getId();
        boolean isChecked = (boolean) e.getNewValue();
        if (isChecked){
            setX(Float.parseFloat(id.substring(5,id.length()).replace("x", ".")));
        }
    }
}
