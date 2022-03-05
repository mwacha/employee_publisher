package tk.mwacha.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import tk.mwacha.entities.NotificationReceived;

public interface NotificationReceivedRepository extends JpaRepository<NotificationReceived, UUID> {}
