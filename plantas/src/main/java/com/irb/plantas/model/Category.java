package com.irb.plantas.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    private UUID id;
    @NonNull
    private String name;
}
