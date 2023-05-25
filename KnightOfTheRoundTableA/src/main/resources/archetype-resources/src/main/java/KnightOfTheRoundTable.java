package $package;

public class KnightOfTheRoundTable implements Knight<HolyGrail> {
    private String name;
    private Quest<HolyGrail> quest;
    
    public KnightOfTheRoundTable(String name) {
        this.name = name;
        quest = new HolyGrailQuest();
    }
    
    public HolyGrail embarkOnQuest() throws QuestFailedException {
        return quest.embark();
    }

    public static void main(String[] args) {
        // Esto es una función main vacía
    }
}