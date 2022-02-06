package tk.mwacha.interactions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mwacha.entities.Employee;
import tk.mwacha.entities.NotificationReceived;
import tk.mwacha.entities.Notifications;
import tk.mwacha.events.sender.EmployeeCreatedEvent;
import tk.mwacha.http.notification.EventSender;
import tk.mwacha.mapper.MessageMapper;
import tk.mwacha.repositories.EmployeeRepository;
import tk.mwacha.repositories.NotificationReceivedRepository;
import tk.mwacha.repositories.NotificationsRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeCreation {

    private final EventSender<EmployeeCreatedEvent> eventSender;
    private final EmployeeRepository repository;
    private final NotificationsRepository notificationsRepository;
    private final MessageMapper mapper;

    @Transactional
    public void create(Employee employee) {
        buildNotifications(mapper.toJson(employee));
        log.info("CREATING EMPLOYEE TENANT-ID");

        repository.save(employee);

        eventSender.send(employee.toCreatedEvent());

        log.info("EMPLOYEE HAS BEEN CREATED ID {} ", employee.getId());
    }

    private void buildNotifications(String json) {
        var notifications = Notifications.builder().body(json).status("DONE").build();
        notificationsRepository.saveAndFlush(notifications);
    }
}
