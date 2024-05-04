package App;

import java.io.File;
import java.util.Objects;

public interface EventListener {
    void update(String eventType, Object target, Object data);
}
