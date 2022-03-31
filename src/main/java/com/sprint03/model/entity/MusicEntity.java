package com.sprint03.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "Music")
public class MusicEntity {

    @Id
    private String id;
    private String music;
    private String artist;

}
