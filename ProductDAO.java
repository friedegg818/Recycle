package _Recycle;

import java.util.List;

public interface ProductDAO {
	public int insertproduct(ProductDTO pdto);									// ��ǰ �Է�
	public int updateproduct(ProductDTO pdto);									// ��ǰ ����
	public int deletproduct(String code);									// ��ǰ ����
	public ProductDTO findBycodeProduct(String pcode);
	public List<ProductDTO> listProduct();
	public ProductDTO findBynameProduct(String pname);			// ��ǰ ����Ʈ
	public ProductDTO minusproduct(String name, int count);
	
}
