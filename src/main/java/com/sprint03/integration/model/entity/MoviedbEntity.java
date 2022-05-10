package com.sprint03.integration.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "moviedb_sprint03")
public class MoviedbEntity {

    @Id
    private String id;
    private String title;
    private String genre;
    private String year;
    private String description;
    private String type;

}
