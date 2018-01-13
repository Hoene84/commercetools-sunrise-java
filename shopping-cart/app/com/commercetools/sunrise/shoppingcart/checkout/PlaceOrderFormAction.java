package com.commercetools.sunrise.shoppingcart.checkout;

import com.commercetools.sunrise.core.controllers.FormAction;
import com.google.inject.ImplementedBy;

@ImplementedBy(DefaultPlaceOrderFormAction.class)
@FunctionalInterface
public interface PlaceOrderFormAction extends FormAction<PlaceOrderFormData> {

}
