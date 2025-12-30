
package app;


public class Membership {
     private int id;
    private int memberId;
    private int planId;
    private String startDate;
    private String endDate;
    private String status;
    private double paidAmount;
    
    public Membership(int id, int memberId, int planId, String startDate, String endDate, String status, double paidAmount) {
        this.id=id;
        this.memberId=memberId;
        this.planId=planId;
        this.startDate=startDate;
        this.endDate=endDate;
        this.status=status;
        this.paidAmount=paidAmount;
    }
    
    public Membership(int memberId, int planId, String startDate, String endDate, String status, double paidAmount) {
        this(0, memberId,planId, startDate,endDate,status,paidAmount);
    }
    
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getMemberId(){
        return memberId;
    }
    public void setMemberId(int memberId){
        this.memberId=memberId;
    }
    public int getPlanId(){
        return planId;
    }
    public void setPlanId(int planId){
        this.planId=planId;
    }
    public String getStartDate(){
        return startDate;
    }
    public void setStartDate(String startDate){
        this.startDate=startDate;
    }
    public String getEndDate(){
        return endDate;
    }
    public void setEndDate(String endDate){
        this.endDate=endDate;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status=status;
    }
    public double getPaidAmount(){
        return paidAmount;
    }
    public void setPaidAmount(double paidAmount){
        this.paidAmount=paidAmount;
    }
    
    public Object[] toArray() {
        return new Object[] { id, memberId, planId, startDate,endDate, status, paidAmount };
    }
}
