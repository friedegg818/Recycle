package _Recycle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class ProductMenu {
	ProductDAO pdao = new ProductDAOImpl();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public void insertproduct() {

		try {
			ProductDTO pdto = new ProductDTO();
			System.out.println("상품코드를 설정하여주세요");
			pdto.setPcode(br.readLine());
			System.out.println("등록하실 상품명을 입력하여주세요");
			pdto.setPname(br.readLine());
			System.out.println("상품가격을 입력하여주세요");
			pdto.setPrice(Integer.parseInt(br.readLine()));
			System.out.println("상품개수를 입력하여주세요");
			pdto.setPcount(Integer.parseInt(br.readLine()));

			int result = pdao.insertproduct(pdto);
			if (result != 0) {
				System.out.println("등록완료");
			} else {
				System.out.println("등록실패");
			}

		} catch (Exception e) {
		} 
	}

	public void updateproduct() { // 상품 입고
		try {
			ProductDTO pdto = new ProductDTO();
			System.out.println("상품코드를 입력하여주세요");
			String code = br.readLine();

			pdto.setPcode(code);
			System.out.println("입고된 개수를 입력하세요");
			pdto.setPcount(Integer.parseInt(br.readLine()));

			int result = pdao.updateproduct(pdto);
			if (result != 0) {
				System.out.println("등록완료");
			} else {
				System.out.println("등록실패");
			}
		} catch (Exception e) {
		}
	}

	public void deleteproduct() {
		try {
			System.out.println("제거할 상품의 코드를 입력하여주세요");
			String code = br.readLine();

			int result = pdao.deletproduct(code);

			if (result != 0) {
				System.out.println("삭제완료");
			} else {
				System.out.println("삭제실패");
			}

		} catch (Exception e) {
		}
	}

	public void listAll() {
		System.out.println("          [상품리스트]");
		System.out.println("===========================");
		List<ProductDTO> list = pdao.listProduct();
		System.out.println("상품코드\t상품명\t가격\t개수\t");
		System.out.println("===========================");
		for (ProductDTO pdto : list) {
			System.out.print(pdto.getPcode() + "\t");
			System.out.print(pdto.getPname() + "\t");
			System.out.print(pdto.getPrice() + "\t");
			System.out.print(pdto.getPcount() + "\n");
		}
		System.out.println("===========================");
	}
	

}
