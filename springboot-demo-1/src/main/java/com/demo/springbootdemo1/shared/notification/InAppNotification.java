package com.demo.springbootdemo1.shared.notification;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
    name = "in_app_notification",
    indexes = {
        @Index(name = "idx_inapp_receiver", columnList = "receiver_id"),
        @Index(name = "idx_inapp_received_at", columnList = "received_at"),
        @Index(
            name = "idx_inapp_receiver_status",
            columnList = "receiver_id, is_read, is_archived, is_starred"
        )
    }
)
public class InAppNotification {

    public static final String ID = "id";
    public static final String RECEIVER_ID = "receiverId";
    public static final String READ = "read";
    public static final String ARCHIVED = "archived";
    public static final String STARRED = "starred";
    public static final String TITLE = "title";
    public static final String MESSAGE = "message";
    public static final String RECEIVED_AT = "receivedAt";
    public static final String LAST_ARCHIVED_AT = "lastArchivedAt";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "receiver_id", updatable = false, nullable = false)
    private Long receiverId;

    @Column(name = "is_read", nullable = false)
    private boolean read;

    @Column(name = "is_archived", nullable = false)
    private boolean archived;

    @Column(name = "is_starred", nullable = false)
    private boolean starred;

    @Column(name = "title", columnDefinition = "TEXT", nullable = false)
    private String title;

    @Column(name = "message", columnDefinition = "TEXT", nullable = false)
    private String message;

    @CreationTimestamp
    @Column(name = "received_at", updatable = false, nullable = false)
    private LocalDateTime receivedAt;

    @Column(name = "last_archived_at")
    private LocalDateTime lastArchivedAt;

    public static InAppNotification newInstance(Long receiverId, String title, String message) {
        return InAppNotification.builder()
            .receiverId(receiverId)
            .title(title)
            .message(message)
            .receivedAt(LocalDateTime.now())
            .build();
    }

    @PrePersist
    @PreUpdate
    private void updateLastArchivedTimestamp() {
        if (this.archived) {
            if (this.lastArchivedAt == null) {
                this.lastArchivedAt = LocalDateTime.now();
            }
        } else {
            this.lastArchivedAt = null;
        }
    }

    @Override
    public final boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        Class<?> oEffectiveClass = object instanceof HibernateProxy
                                   ? ((HibernateProxy) object).getHibernateLazyInitializer()
                                       .getPersistentClass()
                                   : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy
                                      ? ((HibernateProxy) this).getHibernateLazyInitializer()
                                          .getPersistentClass()
                                      : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) {
            return false;
        }
        InAppNotification that = (InAppNotification) object;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
               ? ((HibernateProxy) this).getHibernateLazyInitializer()
                   .getPersistentClass().hashCode()
               : getClass().hashCode();
    }
}
