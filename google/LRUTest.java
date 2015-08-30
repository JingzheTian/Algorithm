package google;

public class LRUTest {
	public static void main(String[] args) {
		LRUCache cache = new LRUCache(1);
		cache.set(2,1);
		
		System.out.println(cache.get(2));
	}
}
