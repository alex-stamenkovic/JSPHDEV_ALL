/* @Author: Wei-Lin Tsai weilints@andrew.cmu.edu */

package javasmartphone.cointoss;

public class Coin {
	private String sideUp;  // only can be "heads" or "tails"
	private int headCount; 
	private int tailCount;

	public Coin() {
		if ((int)(Math.random()*2)  == 0 ) { // 0 for heads 
			sideUp = "heads";
		} else {                     // 1 for tails
			sideUp = "tails";
		}
		this.headCount = 0;
		this.tailCount = 0;
	}
	
	public String getSideUp() {
		return sideUp;
	}
	public void setSideUp(String sideUp) {
		this.sideUp = sideUp;
	}
	public int getHeadCount() {
		return headCount;
	}
	public void setHeadCount(int headCount) {
		this.headCount = headCount;
	}
	public int getTailCount() {
		return tailCount;
	}
	public void setTailCount(int tailCount) {
		this.tailCount = tailCount;
	}
	public void toss() {
		if ((int)(Math.random()*2)== 0 ) { // 0 for heads 
			sideUp = "heads";
			headCount++;
		} else {                     // 1 for tails
			sideUp = "tails";
			tailCount++;
		}
	}
	@Override
	public String toString() {

		return "Coin [sideUp=" + sideUp + ", headCount=" + headCount
				+ ", tailCount=" + tailCount + "]";
	}
	
	public void print() {
		System.out.print(this.toString());
	} 
}
