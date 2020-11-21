package poc.stomp.http.notification;

public interface EventSender<E> {

    void send(E event);
}