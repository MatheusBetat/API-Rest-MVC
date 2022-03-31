package com.sprint03.integration.resttemplate;

import com.sprint03.integration.model.Hits;
import com.sprint03.integration.model.MusicTrack;
import com.sprint03.integration.model.Track;
import com.sprint03.model.entity.MusicEntity;
import lombok.Builder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Component
@Builder
public class MusicClient {

    private final RestTemplate restTemplate;


    public List<MusicEntity> call(String music){

        List<MusicEntity> musicEntity = new ArrayList<>();

        List<Hits> musics =
                (List<Hits>) Objects.requireNonNull(restTemplate.getForObject("/search?term=" + music, Hits.class))
                .getHit();

        /*MusicEntity.builder()
                        .id(musics.)
                        .music(musics.getTrack().getTitle())
                        .artist(musics.getTrack().getSubtitle())
                .build();*/

        return musicEntity;
    }

}
