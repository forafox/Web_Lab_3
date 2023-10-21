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

@Getter
@Setter
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

    public void setX(float x) {
        System.out.println(x);
        this.x = x;
    }

    public void setY(float y) {
        System.out.println("Y value = " + y);
        this.y = y;
    }

    public void setR(float radius) {
        System.out.println(radius);
        this.r = radius;
    }

    public String getStatus() {
        if (status) return "Hit!";
        else return "Miss!";
    }
}
