/**
* Implements the Comparable class. Creates the object Baby used in the Main class.
*
* @author ashultz
*/
public class Baby implements Comparable<Baby> {
    protected String name;
    protected int frequency;
    protected int uses;
 
    // Constructor
    public Baby(String nam, int freq, int use) {
        this.name = nam;
        this.frequency = freq;
        this.uses = use;
    }
 
    // Instance Methods
    public String getName() {
        return this.name;
    }
    public int getFrequency() {
        return this.frequency;
    }
    public int getUses() {
        return this.uses;
    }
    public int updateFreq(int newFreq) { // Update frequency method
        return frequency += newFreq;
    }
    public int updateUses() { // Update uses method
        return uses += 1;
    }
 
    // Override equals method of Object that determines if two items are equal by comparing their names
    public int compareTo(Baby baby) {
        String bby = this.name.toLowerCase();
        String bby2 = baby.name.toLowerCase();
        return bby.compareTo(bby2);
    }
     
    // Print Method
    public String toString() {
        return "Uses: " + uses +
               " Freq: " + frequency;
    } 
}