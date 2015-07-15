package wzhkun.securepw.ui;

import java.util.function.Consumer;

public interface LoginScene extends ErrorShower,SceneContiner{
	void setLoginHandler(Consumer<String> passwordConsumer);
	void toResetSceneHandler(java.util.function.Consumer<Object> nullConsumer);
}
