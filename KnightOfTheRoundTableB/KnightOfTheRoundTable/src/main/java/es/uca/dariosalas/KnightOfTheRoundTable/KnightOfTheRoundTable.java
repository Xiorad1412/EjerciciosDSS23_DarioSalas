import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KnightOfTheRoundTable implements Knight {
    private String name;
    private Quest quest;

    @Autowired
    public KnightOfTheRoundTable(String name, QuestFactoryBean<Quest> questFactoryBean) throws Exception {
        this.name = name;
        this.quest = questFactoryBean.getObject();
    }

    @Override
    public Object embarkOnQuest() {
        return quest.embark();
    }
}