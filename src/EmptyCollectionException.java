
public class EmptyCollectionException extends RuntimeException {

	public EmptyCollectionException(String c) {
		super("The "+ c + " is empty");
	}
}
