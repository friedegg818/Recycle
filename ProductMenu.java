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
			System.out.println("��ǰ�ڵ带 �����Ͽ��ּ���");
			pdto.setPcode(br.readLine());
			System.out.println("����Ͻ� ��ǰ���� �Է��Ͽ��ּ���");
			pdto.setPname(br.readLine());
			System.out.println("��ǰ������ �Է��Ͽ��ּ���");
			pdto.setPrice(Integer.parseInt(br.readLine()));
			System.out.println("��ǰ������ �Է��Ͽ��ּ���");
			pdto.setPcount(Integer.parseInt(br.readLine()));

			int result = pdao.insertproduct(pdto);
			if (result != 0) {
				System.out.println("��ϿϷ�");
			} else {
				System.out.println("��Ͻ���");
			}

		} catch (Exception e) {
		} 
	}

	public void updateproduct() { // ��ǰ �԰�
		try {
			ProductDTO pdto = new ProductDTO();
			System.out.println("��ǰ�ڵ带 �Է��Ͽ��ּ���");
			String code = br.readLine();

			pdto.setPcode(code);
			System.out.println("�԰�� ������ �Է��ϼ���");
			pdto.setPcount(Integer.parseInt(br.readLine()));

			int result = pdao.updateproduct(pdto);
			if (result != 0) {
				System.out.println("��ϿϷ�");
			} else {
				System.out.println("��Ͻ���");
			}
		} catch (Exception e) {
		}
	}

	public void deleteproduct() {
		try {
			System.out.println("������ ��ǰ�� �ڵ带 �Է��Ͽ��ּ���");
			String code = br.readLine();

			int result = pdao.deletproduct(code);

			if (result != 0) {
				System.out.println("�����Ϸ�");
			} else {
				System.out.println("��������");
			}

		} catch (Exception e) {
		}
	}

	public void listAll() {
		System.out.println("          [��ǰ����Ʈ]");
		System.out.println("===========================");
		List<ProductDTO> list = pdao.listProduct();
		System.out.println("��ǰ�ڵ�\t��ǰ��\t����\t����\t");
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
