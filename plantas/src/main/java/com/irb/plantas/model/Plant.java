package com.irb.plantas.model;


import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Plant {
    @Id
    private UUID id;
    @NonNull
    private String name;
    @NonNull
    private String color;
    @NonNull
    private String price;
    @NonNull
    private Category category;
    @NonNull
    private String photo;
    @NonNull
    private String family;
    private Integer stock;
    private Integer count;
}
