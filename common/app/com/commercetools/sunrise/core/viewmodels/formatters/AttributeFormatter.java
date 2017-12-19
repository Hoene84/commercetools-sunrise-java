package com.commercetools.sunrise.core.viewmodels.formatters;

import com.commercetools.sunrise.models.products.AttributeWithProductType;
import com.google.inject.ImplementedBy;
import io.sphere.sdk.models.LocalizedString;
import io.sphere.sdk.models.Reference;
import io.sphere.sdk.products.attributes.Attribute;
import io.sphere.sdk.producttypes.ProductType;

import javax.annotation.Nullable;

@ImplementedBy(AttributeFormatterImpl.class)
public interface AttributeFormatter {

    /**
     * Obtains the label corresponding to the given attribute from this product type.
     * @param attributeWithProductType attribute with its corresponding product type
     * @return the label of this attribute in different locales
     */
    LocalizedString label(@Nullable final AttributeWithProductType attributeWithProductType);

    default LocalizedString label(final Attribute attribute, final Reference<ProductType> productTypeRef) {
        return label(AttributeWithProductType.of(attribute, productTypeRef));
    }

    /**
     * Obtains the value (i.e. {@code name} in CTP terminology) corresponding to the given attribute from this product type.
     * @param attributeWithProductType attribute with its corresponding product type
     * @return the value of this attribute
     */
    String value(@Nullable final AttributeWithProductType attributeWithProductType);

    default String value(final Attribute attribute, final Reference<ProductType> productTypeRef) {
        return value(AttributeWithProductType.of(attribute, productTypeRef));
    }

    /**
     * Obtains a white-listed version of the {@link #value(AttributeWithProductType)}, ready to be used for HTTP requests.
     * @param attributeWithProductType attribute with its corresponding product type
     * @return the encoded value of this attribute
     */
    String encodedValue(@Nullable final AttributeWithProductType attributeWithProductType);
}
