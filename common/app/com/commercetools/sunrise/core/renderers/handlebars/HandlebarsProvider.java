package com.commercetools.sunrise.core.renderers.handlebars;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.cache.HighConcurrencyTemplateCache;
import com.github.jknack.handlebars.helper.StringHelpers;
import com.github.jknack.handlebars.io.TemplateLoader;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.List;

public final class HandlebarsProvider implements Provider<Handlebars> {

    private final HandlebarsSettings handlebarsSettings;
    private final HandlebarsHelperSource handlebarsHelperSource;
    private final ProductHelperSource productHelperSource;

    @Inject
    HandlebarsProvider(final HandlebarsSettings handlebarsSettings, final HandlebarsHelperSource handlebarsHelperSource,
                       final ProductHelperSource productHelperSource) {
        this.handlebarsSettings = handlebarsSettings;
        this.handlebarsHelperSource = handlebarsHelperSource;
        this.productHelperSource = productHelperSource;
    }

    @Override
    public Handlebars get() {
        final List<TemplateLoader> templateLoaders = handlebarsSettings.templateLoaders();
        final TemplateLoader[] loaders = templateLoaders.toArray(new TemplateLoader[templateLoaders.size()]);
        final Handlebars handlebars = new Handlebars()
                .with(loaders)
                .with(new HighConcurrencyTemplateCache())
                .infiniteLoops(true)
                .registerHelpers(StringHelpers.class)
                .registerHelpers(handlebarsHelperSource)
                .registerHelpers(productHelperSource);
        StringHelpers.slugify.registerHelper(handlebars);
        return handlebars;
    }
}