package com.commercetools.sunrise.myaccount.authentication.login;

import com.commercetools.sunrise.hooks.HookContext;
import com.commercetools.sunrise.hooks.events.CustomerSignInResultLoadedHook;
import com.commercetools.sunrise.shoppingcart.CartInSession;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.customers.CustomerSignInResult;
import io.sphere.sdk.customers.commands.CustomerSignInCommand;
import play.libs.concurrent.HttpExecution;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class DefaultLogInFunction implements LogInFunction {

    private final CartInSession cartInSession;
    private final SphereClient sphereClient;
    private final HookContext hookContext;

    @Inject
    protected DefaultLogInFunction(final CartInSession cartInSession, final SphereClient sphereClient, final HookContext hookContext) {
        this.cartInSession = cartInSession;
        this.sphereClient = sphereClient;
        this.hookContext = hookContext;
    }

    @Override
    public CompletionStage<CustomerSignInResult> apply(final LogInFormData formData) {
        final CustomerSignInCommand signInCommand = buildRequest(formData);
        return sphereClient.execute(signInCommand)
                .thenApplyAsync(result -> {
                    runHookOnSignedIn(result);
                    return result;
                }, HttpExecution.defaultContext());
    }

    protected CustomerSignInCommand buildRequest(final LogInFormData formData) {
        final String cartId = cartInSession.findCartId().orElse(null);
        return CustomerSignInCommand.of(formData.getUsername(), formData.getPassword(), cartId);
    }

    private void runHookOnSignedIn(final CustomerSignInResult result) {
        CustomerSignInResultLoadedHook.runHook(hookContext, result);
    }
}