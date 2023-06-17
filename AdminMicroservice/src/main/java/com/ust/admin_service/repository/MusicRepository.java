package com.ust.admin_service.repository;

import com.ust.admin_service.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music,Long> {
}
