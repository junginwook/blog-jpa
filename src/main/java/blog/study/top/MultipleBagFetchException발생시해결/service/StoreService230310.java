package blog.study.top.MultipleBagFetchException발생시해결.service;

import blog.study.top.MultipleBagFetchException발생시해결.repository.StoreRepository230310;
import blog.study.top.entity.Employee;
import blog.study.top.entity.Product;
import blog.study.top.entity.Store;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StoreService230310 {

	private final StoreRepository230310 storeRepository230310;

	@Transactional
	public long find() {
		List<Store> stores = storeRepository230310.findAll();
		long productSum = stores.stream()
				.map(Store::getProducts)
				.flatMap(Collection::stream)
				.mapToLong(Product::getPrice)
				.sum();

		stores.stream()
				.map(Store::getEmployees)
				.flatMap(Collection::stream)
				.map(Employee::getName)
				.toList();

		return productSum;
	}

	@Transactional
	public long findByFetchJoin() {
		List<Store> stores = storeRepository230310.findAllByFetchJoin();

		long sum = stores.stream()
				.map(Store::getProducts)
				.flatMap(Collection::stream)
				.mapToLong(Product::getPrice)
				.sum();

		return sum;
	}
}
