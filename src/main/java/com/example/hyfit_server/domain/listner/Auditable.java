package com.example.hyfit_server.domain.listner;

import java.time.LocalDateTime;

// createdAt과 updatedAt을 설정해줄 때 사용
public interface Auditable {
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();

    void setCreatedAt(LocalDateTime createdAt);
    void setUpdatedAt(LocalDateTime updatedAt);
}