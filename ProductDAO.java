package _Recycle;

import java.util.List;

public interface ProductDAO {
	public int insertproduct(ProductDTO pdto);									// 상품 입력
	public int updateproduct(ProductDTO pdto);									// 상품 수정
	public int deletproduct(String code);									// 상품 삭제
	public ProductDTO findBycodeProduct(String pcode);
	public List<ProductDTO> listProduct();
	public ProductDTO findBynameProduct(String pname);			// 상품 리스트
	public ProductDTO minusproduct(String name, int count);
	
}
