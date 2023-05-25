package $package;

public class HolyGrailQuest implements Quest<HolyGrail> {
    public HolyGrailQuest() { /*...*/ }
    
    public HolyGrail embark() throws QuestFailedException {
        return new HolyGrail();
    }
}