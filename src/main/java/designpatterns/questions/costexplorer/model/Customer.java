package designpatterns.questions.costexplorer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor
public class Customer {
   String id;
   String name;
   Product product;
   LocalDate subscriptionDate;
}
