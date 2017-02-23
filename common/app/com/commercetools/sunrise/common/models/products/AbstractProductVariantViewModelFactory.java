package com.commercetools.sunrise.common.models.products;

import com.commercetools.sunrise.common.models.ViewModelFactory;
import io.sphere.sdk.products.Image;
import io.sphere.sdk.products.ProductVariant;

import java.util.Optional;

public abstract class AbstractProductVariantViewModelFactory<I> extends ViewModelFactory<ProductVariantViewModel, I> {

    protected AbstractProductVariantViewModelFactory() {
    }

    @Override
    protected ProductVariantViewModel getViewModelInstance(final I input) {
        return new ProductVariantViewModel();
    }

    @Override
    protected void initialize(final ProductVariantViewModel viewModel, final I input) {
        fillSku(viewModel, input);
        fillName(viewModel, input);
        fillUrl(viewModel, input);
        fillImage(viewModel, input);
        fillPrice(viewModel, input);
        fillPriceOld(viewModel, input);
    }

    protected abstract void fillSku(final ProductVariantViewModel viewModel, final I input);

    protected abstract void fillName(final ProductVariantViewModel viewModel, final I input);

    protected abstract void fillUrl(final ProductVariantViewModel viewModel, final I input);

    protected abstract void fillImage(final ProductVariantViewModel viewModel, final I input);

    protected abstract void fillPrice(final ProductVariantViewModel viewModel, final I input);

    protected abstract void fillPriceOld(final ProductVariantViewModel viewModel, final I input);

    protected String findSku(final ProductVariant variant) {
        return variant.getSku();
    }

    protected Optional<String> findImageUrl(final ProductVariant variant) {
        return variant.getImages().stream()
                .findFirst()
                .map(Image::getUrl);
    }
}