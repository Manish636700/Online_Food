package com.OnlineFoodOrdering.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private Long price;

    @ManyToOne
    private Category foodCategory;

    @ElementCollection
    @Column(length = 1000)
    private List<String>images;

    private boolean available;

    @ManyToOne
    private Restaurant restaurant;

    private boolean isVegetarian;

    private boolean isSeasonal;

    private boolean isNonveg;

    @ManyToMany
    private List<IngredientsItems> ingredientsItems = new ArrayList<>();

    private Date creationDate;
}
