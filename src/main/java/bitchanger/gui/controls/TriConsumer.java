package bitchanger.gui.controls;

public interface TriConsumer<T, U, V> {

	public abstract void accept(T t, U u, V v);
	
}
