package org.forafox.web_lab_3;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.forafox.web_lab_3.utils.AreaChecker;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Setter
@Getter
@Named("dotBean")
@ApplicationScoped
public class DotBean implements Serializable {

    private Dot dot;
    @Inject
    private DotDao dotDao;
    private List<Dot> dotsList;
    private int timezone;
    private Dot lastDot;
    private String dotsJson;

    @PostConstruct
    public void postConstruct() {
        try {
            dot = new Dot();
            lastDot = dot;
            dotDao.createEntityManager();
            dotsList = dotDao.getDotsFromDB();
        } catch (Exception e) {
            System.err.print("Error in postConstruct()" + e);
        }
    }

    public void add() {
        long timer = System.nanoTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String currentTime = formatter.format(LocalDateTime.now().minusMinutes(getTimezone()));

        dot.setStatus(AreaChecker.isHit(dot));
        dot.setTime(currentTime);
        dot.setScriptTime((long) ((System.nanoTime() - timer) * 0.001));
        dotsList.add(dot);
        dotDao.addDotToDB(dot);

        lastDot = dot;
        dot = new Dot();
        dot.setX(lastDot.getX());
        dot.setY(lastDot.getY());
        dot.setR(lastDot.getR());

    }

    public void clear() {
        dotDao.clearDotsInBD();
        dotsList = dotDao.getDotsFromDB();
    }

    public String getDotsJson() {
        return dotsList.stream()
                .map(Dot::toJSON)
                .toList()
                .toString();
    }

}