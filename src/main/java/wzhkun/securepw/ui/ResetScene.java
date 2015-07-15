package wzhkun.securepw.ui;

import java.util.function.Consumer;

public interface ResetScene extends SceneContiner{
	void setResetHandler(Consumer<String> passwordConsumer);
	void setCancelHandler(Consumer<Object> nullConsumer);
}
