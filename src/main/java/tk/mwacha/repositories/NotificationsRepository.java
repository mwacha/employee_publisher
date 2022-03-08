package tk.mwacha.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import tk.mwacha.entities.Notifications;

public interface NotificationsRepository extends JpaRepository<Notifications, UUID> {}
