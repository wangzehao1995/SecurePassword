package wzhkun.securepw.ui.javafx;

import java.io.IOException;
import java.net.URL;
import java.util.function.Function;

import javafx.fxml.FXMLLoader;

public class ObjectAndController <O,C>{
	O object;
	C controller;
	
	private ObjectAndController(){
	}
	
	public ObjectAndController(URL fxmlUrl) {
		FXMLLoader loader=new FXMLLoader(fxmlUrl);
		try {
			object=loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		controller=loader.getController();
	}
	
	public O getObejct() {
		return object;
	}
	
	public C getController() {
		return controller;
	}
	
	public <T> ObjectAndController<T,C> castObjectType(Function<O,T> caster){
		ObjectAndController<T, C> afterCast=new ObjectAndController<T, C>();
		afterCast.object=caster.apply(this.object);
		afterCast.controller=this.controller;
		return afterCast;
	}
}
