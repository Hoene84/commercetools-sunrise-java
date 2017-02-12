package com.commercetools.sunrise.myaccount.myorders.myorderlist;

import com.commercetools.sunrise.hooks.RequestHookContext;
import com.commercetools.sunrise.myaccount.myorders.AbstractOrderQueryExecutor;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.customers.Customer;
import io.sphere.sdk.orders.Order;
import io.sphere.sdk.orders.queries.OrderQuery;
import io.sphere.sdk.queries.PagedQueryResult;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class DefaultMyOrderListQuery extends AbstractOrderQueryExecutor implements MyOrderListQuery {

    @Inject
    protected DefaultMyOrderListQuery(final SphereClient sphereClient, final RequestHookContext hookContext) {
        super(sphereClient, hookContext);
    }

    @Override
    public CompletionStage<PagedQueryResult<Order>> apply(final Customer customer) {
        return executeRequest(buildRequest(customer));
    }

    protected OrderQuery buildRequest(final Customer customer) {
        return OrderQuery.of()
                    .byCustomerId(customer.getId())
                    .withSort(order -> order.createdAt().sort().desc());
    }
}
