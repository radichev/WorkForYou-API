package com.radichev.workforyou.repository;

import com.radichev.workforyou.domain.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, String> {
}
