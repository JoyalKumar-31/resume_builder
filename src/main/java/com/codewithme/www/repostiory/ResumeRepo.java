package com.codewithme.www.repostiory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithme.www.model.Resume;

public interface ResumeRepo extends JpaRepository<Resume,String> {
       List<Resume> findByUserIdOrderByUpdatedAtDesc(int userId);
       Optional<Resume> findByUserIdAndId(int userId, String id);
}
