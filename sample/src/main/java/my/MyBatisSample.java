package my;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisSample {
	public static void main(String[] args) throws IOException {
		String resource = "META-INF/mybatis/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);

		Properties propse = new Properties();
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream, propse);

		for (int j = 0; j < 10; j++) {
			try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
				ProductMapper mapper = sqlSession
						.getMapper(ProductMapper.class);
				// 更新前
				{
					List<Product> products = mapper.selectProducts();
					for (Product product : products) {
						System.out.println(product.getId() + ", "
								+ product.getName());
						mapper.deleteProduct(product.getId());
					}
				}
				System.out.println();

				{
					for (int i = 0; i < 10; i++) {
						int nextId = mapper.selectMaxId() + 1;
						Product product = new Product(nextId, "foo" + nextId);
						mapper.insertProduct(product);
					}
				}

				// 更新後
				{
					List<Product> products = mapper.selectProducts();
					for (Product product : products) {
						// System.out.println(product.getId() + ", " +
						// product.getName());
					}
				}
				sqlSession.commit();
			}
		}
	}
}
