package blog.study.top.querydsl정렬안하기.repository;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.NullExpression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;

public class OrderByNull extends OrderSpecifier {
	public static final OrderByNull DEFAULT = new OrderByNull();

	public OrderByNull() {
		super(Order.ASC, NullExpression.DEFAULT, NullHandling.Default);
	}
}
