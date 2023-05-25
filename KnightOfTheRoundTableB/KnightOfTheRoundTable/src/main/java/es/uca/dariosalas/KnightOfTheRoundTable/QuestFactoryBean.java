import org.springframework.beans.factory.FactoryBean;

public class QuestFactoryBean<T> implements FactoryBean<T> {
    private Class<T> questClass;

    public QuestFactoryBean(Class<T> questClass) {
        this.questClass = questClass;
    }

    @Override
    public T getObject() throws Exception {
        // Here, you can perform any custom logic to create the quest instance
        // For simplicity, let's assume there's a no-arg constructor for each quest class
        return questClass.getDeclaredConstructor().newInstance();
    }

    @Override
    public Class<?> getObjectType() {
        return questClass;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}