package tk.mwacha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.mwacha.entities.Notifications;

import java.util.UUID;

public interface NotificationsRepository extends JpaRepository<Notifications, UUID> {
}
