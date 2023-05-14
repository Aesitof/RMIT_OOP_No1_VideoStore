
import java.util.HashMap;


public class Stock {
    private  String id;
    private Integer numOfCopies;
    HashMap<String, Integer> stockList = new HashMap<>();

    public String getId() {
        return id;
    }

    public Integer getNumOfCopies() {
        return numOfCopies;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNumOfCopies(Integer numOfCopies) {
        this.numOfCopies = numOfCopies;
    }
    public void addStockList(String id, Integer numOfCopies) {
        stockList.put(id, numOfCopies);
    }
    public void displayStockList(){
        for(String i : stockList.keySet()) {
            System.out.println("ID: " + i +", stock: " + stockList.get(i));
        }
    }
    public void updateCopyNum(String id, Integer updatedCopyNum) {
        stockList.put(id, updatedCopyNum);
    }
    public void updateCopyNumPerLoan(String id) {
        stockList.put(id, stockList.get(id) + 1);
    }
}