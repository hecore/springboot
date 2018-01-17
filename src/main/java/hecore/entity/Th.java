//
/**
 * @author Administrator
 *
 */
package hecore.entity;
//Model

public class Th {
	private final long  id;
	private final String name;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Th(long l, String name) {
		this.id = l;
		this.name = name;
	}
}