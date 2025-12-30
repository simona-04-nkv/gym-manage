
package app;


public class Plan {
    private int planId;
    private String name;
    private int duration;
    private double price;

    public Plan(int planId, String name, int duration, double price) {
        this.planId = planId;
        this.name = name;
        this.duration = duration;
        this.price = price;
    }

    public int getPlanId() { return planId; }
    public void setPlanId(int planId) { this.planId = planId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name;}
    
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Object[] toArray() {
        return new Object[]{ planId, name, duration, price };
    }
}
