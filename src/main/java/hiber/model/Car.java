package hiber.model;

import javax.persistence.*;

@Entity @Table (name = "cars")
public class Car {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int series;
    private String model;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    private User user;

    @Override
    public String toString() {
        return "Car: " +
                "Series = " + series +
                ", Model = '" + model + '\'';
    }

    public Car() {

    }

    public Car(int series, String model) {
        this.series = series;
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
