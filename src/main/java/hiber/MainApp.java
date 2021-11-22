package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car oldCar = new Car(1908, "model T");
      Car electro = new Car(2012, "model S");
      Car commonCar = new Car(2000, "Celica");
      userService.add(new User("User1", "Lastname1", "user1@mail.ru", oldCar));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", electro));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", commonCar));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      //Ошибка, нельзя использовать один объект Car для разных объектов User при OneToOne
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru", commonCar));

      userService.listUsers().forEach(System.out::println);

      System.out.println(userService.getUserWithTheCar(2000, "Celica"));
      System.out.println(userService.getUserWithTheCar(2021, "model 3"));
      context.close();
   }
}
