package $package;

public interface Quest<T> {
    T embark() throws QuestFailedException;
}