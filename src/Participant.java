public class Participant {
    private String ID;
    private String name;
    private String email;
    private String preferredGame;
    private int skillLevel;
    private String preferredRole;
    private int personalityScore;
    private String personalityType;

    //constructor
    public Participant(){
        this.ID=ID;
        this.name=name;
        this.email=email;
        this.preferredGame=preferredGame;
        this.skillLevel=skillLevel;
        this.preferredRole=preferredRole;
        this.personalityScore=personalityScore;
        this.personalityType=personalityType;
    }

    //getters and setters
    public String getID(){
        return ID;
    }
    public void setID(String ID){
        this.ID=ID;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getPreferredGame(){
        return preferredGame;
    }
    public void setPreferredGame(String preferredGame){
        this.preferredGame=preferredGame;
    }
    public int getSkillLevel(){
        return skillLevel;
    }
    public void setSkillLevel(int skillLevel){
        this.skillLevel=skillLevel;
    }
    public String getPreferredRole(){
        return preferredRole;
    }
    public void setPreferredRole(String preferredRole){
        this.preferredRole=preferredRole;
    }
    public int getPersonalityScore(){
        return personalityScore;
    }
    public void setPersonalityScore(int personalityScore){
        this.personalityScore=personalityScore;
    }
    public String getPersonalityType(){
        return personalityType;
    }
    public void setPersonalityType(String personalityType){
        this.personalityType=personalityType;
    }

    @Override
    public String toString(){
        return "ID: "+ID+"\nName: "+name+"\nEmail: "+email+"\nPreferred Game: "+preferredGame+"\nSkill Level: "+skillLevel+"\nPreferred Role: "+preferredRole+"\nPersonality Score: "+personalityScore+"\nPersonality Type: "+personalityType;
    }

}
