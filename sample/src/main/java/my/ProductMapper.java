package my;

import java.util.List;

public interface ProductMapper {
	
	public int selectMaxId();
	
	public List<Product> selectProducts();

	public Product selectProduct(int id);

	public void insertProduct(Product product);

	public void deleteProduct(int id);
}
