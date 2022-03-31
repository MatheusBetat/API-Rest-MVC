package com.sprint03.controller;

import com.sprint03.integration.resttemplate.MusicClient;
import com.sprint03.model.entity.MusicEntity;
import com.sprint03.service.MusicService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1")
public class MusicController {

    private final MusicService musicService;

    private final MusicClient musicClient;

    @GetMapping("/musica/{music}")
    public List<MusicEntity> musicEntities(@PathVariable String music){
        return musicClient.call(music);
    }
}