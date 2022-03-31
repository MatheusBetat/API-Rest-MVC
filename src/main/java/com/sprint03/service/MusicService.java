package com.sprint03.service;

import com.sprint03.repository.MusicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MusicService {

    private final MusicRepository musicRepository;
}
